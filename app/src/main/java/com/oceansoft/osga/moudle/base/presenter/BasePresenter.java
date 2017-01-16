package com.oceansoft.osga.moudle.base.presenter;

import android.content.Context;

import com.oceansoft.osga.moudle.base.model.AbsBaseModel;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;


/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class BasePresenter<M extends AbsBaseModel,V extends IMvpView> extends AbsMvpPresenter<V> {
    private Context context;

    private M baseModel;

    public M bindModel(){
        return baseModel;
    }

    public M getModel() {
        if (this.baseModel==null){
            this.baseModel=bindModel();
        }

        return baseModel;
    }

    public BasePresenter(Context context){
        this.context=context;

    }

    public Context getContext() {
        return context;
    }

    public interface OnUIThreadListener<T>{
        public void onResult(T result,String errorMessage);
    }

    @Override
    public void onUnsubsrible() {

    }
}
