package com.oceansoft.osga.widget.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oceansoft.osga.R;


/**
 * Created by xm on 2014/9/29.
 */
public class EmptyView extends LinearLayout {

    private TextView tv_empty;

    public EmptyView(Context context) {
        super(context);
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        View view = LayoutInflater.from(context).inflate(R.layout.emptyviewlayout, null);

        tv_empty = (TextView) view.findViewById(R.id.tv_empty);
        addView(view, params);
    }


    public void setEmptyText(String emptyText) {
        tv_empty.setText(emptyText);
    }

}
