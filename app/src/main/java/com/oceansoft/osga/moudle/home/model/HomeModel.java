package com.oceansoft.osga.moudle.home.model;

import android.content.Context;

import com.oceansoft.osga.http.rxjavahttp.ApiManager;
import com.oceansoft.osga.moudle.base.model.AbsBaseModel;
import com.oceansoft.osga.moudle.home.bean.GridItem;
import com.oceansoft.osga.moudle.home.bean.NewsBean;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by TempCw on 2017/1/13.
 */

public class HomeModel extends AbsBaseModel {
    private CompositeSubscription mCompositeSub=new CompositeSubscription();

    public HomeModel(Context context) {
        super(context);
    }
    //获取ViewPager信息
    public void getViewPagerInfo(final OnViewPagerListener onViewPagerListener){
        Subscription subscription= ApiManager.getInstance().getMainService()
                .getViewPagerInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onViewPagerListener.onViewPagerFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        onViewPagerListener.onViewPagerSuccess(newsBean);
                    }
                });
        mCompositeSub.add(subscription);

    }
    //获取GridView信息
    public void getGridViewInfo(final OnGridViewResultListener onGridViewResultListener) {
        Subscription subscription = ApiManager.getInstance().getMainService()
                .getGridViewInof()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GridItem>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onGridViewResultListener.onGridViewFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(GridItem gridItem) {
                        onGridViewResultListener.onGridViewSuccess(gridItem);
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
    public interface OnViewPagerListener{
        void onViewPagerSuccess(NewsBean newsBean);

        void onViewPagerFailed(String errorMessage);

    }
    public interface OnGridViewResultListener{
        void onGridViewSuccess(GridItem gridItem);

        void onGridViewFailed(String s);

    }





}
