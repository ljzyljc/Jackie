package com.oceansoft.osga.moudle.base.presenter;

import android.content.Context;

import com.oceansoft.osga.moudle.base.model.AbsBaseModel;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;


/**
 * Created by Jackie on 2017/1/10.
 * BasePresenter绑定View和Model
 */

public abstract class BasePresenter<M extends AbsBaseModel,V extends IMvpView> extends AbsMvpPresenter<M,V> {


    public BasePresenter(Context context) {
        super(context);
    }
    public interface OnUIThreadListener<T>{
        public void onResult(T result,String errorMessage);
    }

    public void Onunsubscribe() {   //RxJava+retrofit取消订阅

    }

}
