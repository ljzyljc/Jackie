package com.oceansoft.osga.mvp.presenter.impl;


import com.oceansoft.osga.mvp.presenter.IMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsMvpPresenter<V extends IMvpView> implements IMvpPresenter<V> {
    private V view;

    @Override
    public void attachView(V view) {
        this.view=view;
    }

    @Override
    public void detachView() {

    }

    public V getView() {
        return view;
    }
}
