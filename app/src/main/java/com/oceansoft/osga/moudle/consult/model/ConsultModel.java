package com.oceansoft.osga.moudle.consult.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.oceansoft.osga.http.rxjavahttp.ApiManager;
import com.oceansoft.osga.moudle.base.model.AbsBaseModel;
import com.oceansoft.osga.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osga.moudle.consult.bean.ConsultRequestbean;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterRequestInfo;

import java.util.List;

import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by TempCw on 2017/1/15.
 */

public class ConsultModel extends AbsBaseModel {
    private CompositeSubscription mCompositeSub=new CompositeSubscription();

    public ConsultModel(Context context) {
        super(context);
    }

    public void getMatterList(int pageNum,final OnLoadLister onLoadLister){
        ConsultRequestbean consultRequestbean=new ConsultRequestbean();
        consultRequestbean.setNumPerPage(10);
        consultRequestbean.setKeywords("");
        consultRequestbean.setPt("A");
        consultRequestbean.setPageNum(pageNum);
        Gson gson=new Gson();
        String consultJson=gson.toJson(consultRequestbean);
        RequestBody requestBody=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),consultJson);

        Subscription subscription= ApiManager.getInstance().getMainService()
                .getMatterList(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConsultMatter>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onLoadLister.onFailor(e.getMessage());
                        Log.i("jc","-consult"+e.getMessage());
                    }

                    @Override
                    public void onNext(ConsultMatter consultMatter) {
                        onLoadLister.onSuccess(consultMatter);
                        Log.i("jc","-consult"+consultMatter.isSucc());
                    }
                });
        mCompositeSub.add(subscription);
    }
    @Override
    public void unsubscribe(){
        if (mCompositeSub!=null){
            mCompositeSub=null;
        }
    }



    public interface OnLoadLister{
        void onSuccess(ConsultMatter consultMatter);

        void onFailor(String errorMessage);

    }





}
