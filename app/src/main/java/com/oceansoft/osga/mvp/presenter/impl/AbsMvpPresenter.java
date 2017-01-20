package com.oceansoft.osga.mvp.presenter.impl;


import android.content.Context;

import com.oceansoft.osga.mvp.model.IMvpModel;
import com.oceansoft.osga.mvp.presenter.IMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;

/**
 * Created by Jackie on 2017/1/10.
 *
 */

//public abstract class AbsMvpPresenter<V extends IMvpView> implements IMvpPresenter<V> {  进一步优化，同时绑定Model层
public abstract class AbsMvpPresenter<M extends IMvpModel, V extends IMvpView> implements IMvpPresenter<V> {
    private Context context;
    public Context getContext() {
        return context;
    }
    private M model;
    private V view;

    public M bindModel(){
        return model;
    }
    public AbsMvpPresenter(Context context){
        this.context=context;
    }

    public M getModel() {
        if (this.model==null){
            //绑定View
            this.model=bindModel();
        }
        return model;
    }

    @Override
    public void attachView(V view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        if(view!=null){
            view=null;
        }
    }

    public V getView() {
        return view;
    }


//    @Override
//    public void onUnsubsrible() {
//
//    }
}
