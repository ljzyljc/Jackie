package com.oceansoft.osga.moudle.base.view.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oceansoft.osga.mvp.view.impl.AbsMvpFragment;
import com.oceansoft.osga.mvp.view.impl.AbsMvpMapFragment;


/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsBaseMapFragment extends AbsMvpMapFragment {
    private View contentView;

    private boolean isInit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         if (contentView==null){
             contentView=inflater.inflate(bindLayoutId(),container,false);
             initContentView(contentView);
         }
        ViewGroup parent= (ViewGroup) contentView;
        if (parent!=null){
            parent.removeView(contentView);
        }
        return contentView;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isInit){
            this.isInit=true;
            initData();
        }

    }

    public abstract int bindLayoutId();

    public void initData(){

    }

    public abstract void initContentView(View contentView);
}
