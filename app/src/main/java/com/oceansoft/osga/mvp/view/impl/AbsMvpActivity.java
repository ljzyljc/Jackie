package com.oceansoft.osga.mvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oceansoft.osga.mvp.view.BaseActivity;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;


/**
 * Created by TempCw on 2017/1/10.
 * 如果一个Activity中只有一个Presenter就继承该类
 */

public abstract class AbsMvpActivity<P extends AbsMvpPresenter,V extends IMvpView>extends BaseActivity {
    private P presenter;
    private V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter==null){
            presenter=bindPresenter();
        }
        if (view==null){
            this.view=bindView();
            this.presenter.attachView(this.view);
        }



    }

    public abstract P bindPresenter();

    public abstract V bindView();

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.presenter!=null){
            this.presenter.detachView();
            this.presenter=null;
        }

    }
}
