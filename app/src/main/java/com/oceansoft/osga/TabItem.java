package com.oceansoft.osga;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by TempCw on 2017/1/10.
 */

public class TabItem {
    private LayoutInflater inflater;
    private Context context;
    private int imageNormal;

    private int imagePress;

    private int tabTextRes;

    private Class<? extends Fragment> fragmentClazz;

    private Bundle bundle;

    private View viewIndicator;

    private ImageView iv_tab;

    private TextView tv_tab;

    public TabItem(Context context, Class<? extends Fragment> fragmentClazz, int imageNormal, int imagePress, int tabTextRes) {
        inflater = LayoutInflater.from(context);
        this.fragmentClazz = fragmentClazz;
        this.imageNormal = imageNormal;
        this.imagePress = imagePress;
        this.tabTextRes = tabTextRes;
        this.context = context;
    }

    public Class<? extends Fragment> getFragmentClazz() {
        return fragmentClazz;
    }

    public void setFragmentClazz(Class<? extends Fragment> fragmentClazz) {
        this.fragmentClazz = fragmentClazz;
    }

    public int getImageNormal() {
        return imageNormal;
    }

    public void setImageNormal(int imageNormal) {
        this.imageNormal = imageNormal;
    }

    public int getImagePress() {
        return imagePress;
    }

    public void setImagePress(int imagePress) {
        this.imagePress = imagePress;
    }

    public ImageView getIv_tab() {
        return iv_tab;
    }

    public void setIv_tab(ImageView iv_tab) {
        this.iv_tab = iv_tab;
    }

    public String getTabTextRes() {
        return context.getResources().getString(tabTextRes);
    }

    public void setTabTextRes(int tabTextRes) {
        this.tabTextRes = tabTextRes;
    }

    public TextView getTv_tab() {
        return tv_tab;
    }

    public void setTv_tab(TextView tv_tab) {
        this.tv_tab = tv_tab;
    }

    public View getViewIndicator() {
        return viewIndicator;
    }

    public void setViewIndicator(View viewIndicator) {
        this.viewIndicator = viewIndicator;
    }

    public Bundle getBundle() {
        if (bundle == null) {
            bundle = new Bundle();
            bundle.putInt("tabRes",tabTextRes);
        }
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public void setChecked(boolean isChecked) {
        if (imageView != null) {
            if (isChecked) {
                imageView.setImageResource(imagePress);
            } else {
                imageView.setImageResource(imageNormal);
            }
        }
        if (textView != null) {
            if (isChecked) {
                textView.setTextColor(context.getResources().getColor(R.color.base_color1));
            } else {
                textView.setTextColor(context.getResources().getColor(R.color.black));
            }
        }
    }
    ImageView imageView;
    TextView textView;
    public View getTabIndicator() {

        if (viewIndicator == null) {
//            inflater = LayoutInflater.from(context);
            viewIndicator=inflater.inflate(R.layout.tabbar_indicator,null);
            imageView= (ImageView) viewIndicator.findViewById(R.id.iv_tab);
            textView= (TextView) viewIndicator.findViewById(R.id.tv_tab);
            imageView.setImageResource(imageNormal);
            textView.setText(getTabTextRes());
        }
        return viewIndicator;
    }



}
