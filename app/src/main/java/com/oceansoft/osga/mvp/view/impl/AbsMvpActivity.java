package com.oceansoft.osga.mvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oceansoft.osga.BaseActivity;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;


/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsMvpActivity<P extends AbsMvpPresenter,V extends IMvpView>extends BaseActivity {
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
