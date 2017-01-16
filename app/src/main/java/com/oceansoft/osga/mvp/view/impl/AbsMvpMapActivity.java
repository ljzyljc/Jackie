package com.oceansoft.osga.mvp.view.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.oceansoft.osga.BaseActivity;
import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;
import com.oceansoft.osga.utils.StatusColorUtil;
import com.oceansoft.osga.utils.SystemBarTintManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsMvpMapActivity extends BaseActivity {
    private Map<AbsMvpPresenter,IMvpView> mvpMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mvpMap=new HashMap<>();
        bindPresenter();
        StatusColorUtil.applyKitKatTranslucency(AbsMvpMapActivity.this);
    }
    public void putPresenter(AbsMvpPresenter mvpPresenter,IMvpView mvpView){
        mvpPresenter.attachView(mvpView);
        mvpMap.put(mvpPresenter,mvpView);
    }
    public abstract void bindPresenter();


    public void bindNavagation(int id, int text, final Activity activity){
        DefaultNavigation.Builder builder=new
                DefaultNavigation.Builder(activity, (ViewGroup) findViewById(id));
        builder.setCenterText(text)
                .setLeftImg(R.drawable.btn_node_pre_xml)
                .setLeftImgOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.finish();
                    }
                })
                .create();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpMap!=null){
            for(AbsMvpPresenter presenter:mvpMap.keySet()){
                presenter.detachView();
            }
        }
        mvpMap=null;
    }
}
