package com.oceansoft.osga.widget.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.oceansoft.osga.R;


/**
 * Created by xm on 2014/9/29.
 */
public class ReloadView extends LinearLayout {

    public ReloadView(Context context) {
        super(context);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        View view = LayoutInflater.from(context).inflate(R.layout.reloadviewlayout, null);
        addView(view, params);
    }


}
