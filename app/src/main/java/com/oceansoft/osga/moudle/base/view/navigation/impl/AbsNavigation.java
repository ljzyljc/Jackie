package com.oceansoft.osga.moudle.base.view.navigation.impl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.oceansoft.osga.moudle.base.view.navigation.INavigation;


/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsNavigation<P extends AbsNavigation.Builder.NavigationParams> implements INavigation {
    private P params;
    private View contentView;

    public AbsNavigation(P params){
        this.params=params;
    }

    public P getParams() {
        return params;
    }

    @Override
    public void createAndBind() {
        contentView= LayoutInflater.from(params.context).inflate(getLayoutId(),params.parent,false);
        ViewParent viewParent=contentView.getParent();
        if (viewParent!=null){
            ViewGroup viewGroup= (ViewGroup) viewParent;
            viewGroup.removeView(contentView);
        }
        params.parent.addView(contentView,0);
    }

    public View findViewById(int id){
        return params.parent.findViewById(id);
    }
    public String getString(int id){
        return params.context.getResources().getString(id);
    }
    public int getColor(int id){
        return params.context.getResources().getColor(id);
    }



    public static abstract class Builder{
        public abstract AbsNavigation create();
        //定义参数类
        public static class NavigationParams{
            //两个必备参数
            public Context context;
            public ViewGroup parent;
            public NavigationParams(Context context,ViewGroup parent){
                this.context=context;
                this.parent=parent;
            }

        }

    }



}
