package com.oceansoft.osga.moudle.usercenter.login.model;

import android.content.Context;
import android.util.Log;

import com.oceansoft.osga.config.AccountModule;
import com.oceansoft.osga.http.rxjavahttp.ApiManager;
import com.oceansoft.osga.moudle.base.model.AbsBaseModel;
import com.oceansoft.osga.moudle.usercenter.login.bean.LoginInfo;
import com.oceansoft.osga.moudle.usercenter.login.bean.Requestbean_login;

import com.google.gson.Gson;

import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by TempCw on 2017/1/11.
 */

public class LoginModel extends AbsBaseModel {
    private CompositeSubscription mCompositeSubscription=new CompositeSubscription();
    private AccountModule module= AccountModule.getModule();
    public LoginModel(Context context) {
        super(context);
    }
    public void login(String username, String password, final OnLoginListener onLoginListener){
        Requestbean_login requestbeanLogin=new Requestbean_login();
        requestbeanLogin.setLoginId("17715369021");
        requestbeanLogin.setPassword("password");
        requestbeanLogin.setPt("A");
        requestbeanLogin.setUserSrc(1);
        Gson gson=new Gson();
        String loginjson=gson.toJson(requestbeanLogin);

        RequestBody requestBody=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),loginjson);
        Subscription subscription= ApiManager.getInstance().getMainService()
                .login(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginInfo>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        onLoginListener.onLoginFail("登录失败");
//                        Log.i("jc","model----"+e.getMessage());
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo.isSucc()){
//                            Log.i("jc","model"+loginInfo.getStatusCode());
                            onLoginListener.onLoginSuccess(loginInfo);
                            //存储登录信息
                            module.setLoginSuccess(loginInfo.getData());

                        }else {
                            onLoginListener.onLoginFail(String.valueOf(loginInfo.getStatusCode()));
                        }
                    }
                });
                mCompositeSubscription.add(subscription);
    }



    @Override
    public void unsubscribe() {
        if (this.mCompositeSubscription!=null){
            this.mCompositeSubscription.unsubscribe();
        }

    }
    public interface OnLoginListener{
        void onLoginSuccess(LoginInfo code);
        void onLoginFail(String result);

    }







}
