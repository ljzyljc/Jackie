package com.oceansoft.osga.moudle.usercenter.register.model;

import android.content.Context;

import com.google.gson.Gson;
import com.oceansoft.osga.http.rxjavahttp.ApiManager;
import com.oceansoft.osga.moudle.base.model.AbsBaseModel;
import com.oceansoft.osga.moudle.usercenter.register.bean.AppUser;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterAutoCodeInfo;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterRequestInfo;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterResultInfo;
import com.oceansoft.osga.utils.DialogUtil;

import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by TempCw on 2017/1/12.
 */

public class RegisterModel extends AbsBaseModel{
    private CompositeSubscription mCompositeSub=new CompositeSubscription();

    public RegisterModel(Context context) {
        super(context);
    }
    public void userRegister(String username, String password, String authCode, final OnReregisterResultListener onReregisterResultListener){
        RegisterRequestInfo requestInfo=new RegisterRequestInfo();
        requestInfo.setMobile(username);
        requestInfo.setPassword(password);
        requestInfo.setValidateCode(authCode);
        requestInfo.setPt("A");
        requestInfo.setUnit("1");
        requestInfo.setUserSrc("4");
        Gson gson=new Gson();
        String registerJson=gson.toJson(requestInfo);
        RequestBody requestBody=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),registerJson);
        Subscription subscription=ApiManager.getInstance().getMainService()
                .register(requestBody)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                       onReregisterResultListener.onRegisterStart();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterResultInfo>() {
                    @Override
                    public void onCompleted() {
                        onReregisterResultListener.onRegisterFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onReregisterResultListener.onRegisterFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(RegisterResultInfo appUser) {
                        if (appUser.isSucc()) {
                            onReregisterResultListener.onRegisterSuccess(appUser);
                        }else{
                            onReregisterResultListener.onRegisterFailed(appUser.getMsg());
                        }
                    }
                });
        mCompositeSub.add(subscription);

    }


    public void getAuthCode(String tel, String type , final OnGetAutoResultListener onGetAutoListener){
        Subscription subscription= ApiManager.getInstance().getMainService()
                .getAuthCode("180",tel,"2","")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterAutoCodeInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onGetAutoListener.onGetAuthCodeFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(RegisterAutoCodeInfo registerAutoCodeInfo) {
                        if (registerAutoCodeInfo.isSucc()) {
                            onGetAutoListener.onGetAuthCodeSuccess(registerAutoCodeInfo);
                        }else{
                            onGetAutoListener.onGetAuthCodeFailed(registerAutoCodeInfo.getMsg());
                        }
                    }
                });
        mCompositeSub.add(subscription);

    }




    @Override
    public void unsubscribe() {
        if (this.mCompositeSub!=null){
            this.mCompositeSub.unsubscribe();
        }
    }

    public interface OnReregisterResultListener{

        void onRegisterStart();

        void onRegisterSuccess( RegisterResultInfo appUser);

        void onRegisterFailed(String s);

        void onRegisterFinish();


    }

    public interface OnGetAutoResultListener{
        void onGetAuthCodeSuccess(RegisterAutoCodeInfo autoCodeInfo);

        void onGetAuthCodeFailed(String error);

    }

}
