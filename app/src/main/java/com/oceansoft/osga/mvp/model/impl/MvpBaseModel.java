package com.oceansoft.osga.mvp.model.impl;


import android.content.Context;

import com.oceansoft.osga.mvp.model.IMvpModel;

/**
 * Created by TempCw on 2017/1/10.
 */

public abstract class MvpBaseModel implements IMvpModel {
        private Context context;

    public MvpBaseModel(Context context){
        this.context=context;
    }

    public Context getContext() {
        return context;
    }

}
