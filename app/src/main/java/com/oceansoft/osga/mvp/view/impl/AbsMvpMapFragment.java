package com.oceansoft.osga.mvp.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;
import com.oceansoft.osga.utils.StatusColorUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsMvpMapFragment extends Fragment{
    private Map<AbsMvpPresenter,IMvpView> mvpMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpMap=new HashMap<>();
        bindPresenter();
        StatusColorUtil.applyKitKatTranslucency(getActivity());
    }
    public abstract void bindPresenter();

    public void putPresenter(AbsMvpPresenter mvpPresenter,IMvpView mvpView){
        mvpPresenter.attachView(mvpView);
        mvpMap.put(mvpPresenter,mvpView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mvpMap!=null){
            for(AbsMvpPresenter presenter: mvpMap.keySet()){
                presenter.detachView();
            }
        }
        mvpMap=null;
    }
}
