package com.oceansoft.osga.moudle.base.model;

import android.content.Context;

import com.oceansoft.osga.mvp.model.impl.MvpBaseModel;


/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class AbsBaseModel extends MvpBaseModel {
    private Context context;

    public AbsBaseModel(Context context){
        this.context=context;
    }

    public Context getContext() {
        return context;
    }



}
