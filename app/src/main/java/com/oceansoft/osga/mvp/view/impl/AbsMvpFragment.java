package com.oceansoft.osga.mvp.view.impl;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.oceansoft.osga.R;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;
import com.oceansoft.osga.utils.StatusColorUtil;
import com.oceansoft.osga.utils.SystemBarTintManager;


/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsMvpFragment<P extends AbsMvpPresenter,V extends IMvpView>extends Fragment{
    private P presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.presenter=bindPresenter();
        //设置状态栏的颜色和标题栏一致
        StatusColorUtil.applyKitKatTranslucency(getActivity());
    }


    public abstract P bindPresenter();

    public abstract V bindView();

    public P getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.presenter!=null){
            this.presenter.detachView();
            this.presenter=null;
        }
    }
}
