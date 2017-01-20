package com.oceansoft.osga.moudle.base.view.views;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oceansoft.osga.mvp.view.impl.AbsMvpActivity;
import com.oceansoft.osga.mvp.view.impl.AbsMvpMapActivity;

/**
 * Created by TempCw on 2017/1/17.
 */

public abstract class AbsBaseMapActivity extends AbsMvpMapActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
