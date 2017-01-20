package com.oceansoft.osga.moudle.base.model;

import android.content.Context;

import com.oceansoft.osga.mvp.model.impl.MvpBaseModel;

import rx.subscriptions.CompositeSubscription;


/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsBaseModel extends MvpBaseModel {
    public AbsBaseModel(Context context) {
        super(context);
    }

    public void unsubscribe() {   //RxJava+retrofit取消订阅

    }
}
