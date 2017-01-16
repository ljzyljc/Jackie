package com.oceansoft.osga.mvp.presenter;


import com.oceansoft.osga.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/10.
 */

public interface IMvpPresenter<V extends IMvpView> {
    public void attachView(V view);

    public void detachView();

    void onUnsubsrible();

}
