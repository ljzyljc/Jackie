package com.oceansoft.osga.moudle.base.view.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.base.presenter.BasePresenter;
import com.oceansoft.osga.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osga.mvp.view.IMvpView;
import com.oceansoft.osga.mvp.view.impl.AbsMvpActivity;


/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsBaseActivity<P extends BasePresenter,V extends IMvpView>extends AbsMvpActivity<P,V> {

    @Override
    public P bindPresenter() {
        return null;
    }

    @Override
    public V bindView() {
        return null;
    }
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

    private View contentView;

    private boolean isInit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
