package com.oceansoft.osga.moudle.base.view.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oceansoft.osga.moudle.base.presenter.BasePresenter;
import com.oceansoft.osga.mvp.view.IMvpView;
import com.oceansoft.osga.mvp.view.impl.AbsMvpFragment;


/**
 * Created by Jackie on 2017/1/10.
 * 当一个Fragment中只有一个Presenter
 */

public abstract class AbsBaseFragment<P extends BasePresenter,V extends IMvpView> extends AbsMvpFragment<P,V> {

    @Override
    public P bindPresenter() {
        return null;
    }

    @Override
    public V bindView() {
        return null;
    }

    private View contentView;

    private boolean isInit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView==null){
            contentView=inflater.inflate(bindLayoutId(),container,false);
            initContentView(contentView);
        }
        ViewGroup parent= (ViewGroup) contentView.getParent();
        if (parent!=null){
            parent.removeView(contentView);
        }
        return contentView;
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    public View getContentView() {
        return contentView;
    }


    @Override
    public P getPresenter() {
        return super.getPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInit){
            this.isInit=true;
            initData();
        }
    }
    protected void resetContentView(View contentView){
        ViewGroup viewGroup= (ViewGroup) contentView;
        viewGroup.removeAllViews();

    }
    protected void loadLayout(int layoutId,View v){
        ViewGroup viewGroup= (ViewGroup) v;
        View view=LayoutInflater.from(getContext()).inflate(layoutId,viewGroup,false);
        ViewGroup parent= (ViewGroup) view.getParent();
        if (parent!=null){
            parent.removeView(view);
        }
        viewGroup.addView(view);
    }


    public abstract int bindLayoutId();

    public void initData(){

    }
    public abstract void initContentView(View contentView);
}
