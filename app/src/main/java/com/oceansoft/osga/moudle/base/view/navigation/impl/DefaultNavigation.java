package com.oceansoft.osga.moudle.base.view.navigation.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oceansoft.osga.R;


/**
 * Created by TempCw on 2017/1/10.
 */

public class DefaultNavigation extends AbsNavigation<DefaultNavigation.Builder.DefaultNavigationParams> {
    public DefaultNavigation(Builder.DefaultNavigationParams params) {
        super(params);
    }

    @Override
    public void createAndBind() {
        super.createAndBind();
        //设置左边的图片的点击事件
//        setText(R.id.iv_left,getParams().leftText,getParams().leftOnClickListener);
        setText(R.id.tv_center,getParams().centerText,getParams().centerOnClickListener);
        //给我们的TextView绑定文本和监听
//        setText(R.id.iv_right,getParams().rightTextColor,getParams().rightText,getParams().rightOnClickListener);
        setImg(R.id.iv_left,getParams().leftImg,getParams().leftOnClickListener);
        setImg(R.id.iv_right,getParams().rightImg,getParams().rightOnClickListener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.navigation_default;
    }
    //设置样式,绑定监听
    protected void setText(int viewId, int text, View.OnClickListener onClickListener){
        setText(viewId,0,getString(text),onClickListener);
    }

    //设置样式,绑定监听
    protected void setText(int viewId, String text, View.OnClickListener onClickListener){
        setText(viewId,0,text,onClickListener);
    }

    //设置样式,绑定监听
    protected void setText(int viewId,int textColor, int text, View.OnClickListener onClickListener){
        setText(viewId,textColor,getString(text),onClickListener);
    }

    //设置样式,绑定监听
    protected void setText(int viewId,int textColor, String text, View.OnClickListener onClickListener){
        View view = findViewById(viewId);
        if (view == null && !(view instanceof TextView)){
            return;
        }
        TextView textView = (TextView) view;
        if (TextUtils.isEmpty(text)){
            textView.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
            textView.setOnClickListener(onClickListener);

            if (textColor != 0){
                textView.setTextColor(getColor(textColor));
            }
        }

    }
    //图片绑定监听
    protected void setImg(int viewId,int imgRes,View.OnClickListener onClickListener){
        Log.i("jc","setImg"+imgRes);
        View view=findViewById(viewId);
        if (view==null&&!(view instanceof ImageView)){
            return;
        }
        ImageView imageView= (ImageView) view;
        if (imgRes==0){
            imageView.setVisibility(View.GONE);
        }else{
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(imgRes);
            imageView.setOnClickListener(onClickListener);
        }

    }


    public static class Builder extends AbsNavigation.Builder {
        protected DefaultNavigationParams params;

        public Builder(Context context,ViewGroup parent){
            this.params=new DefaultNavigationParams(context,parent);
        }
        //设置左右边的图片
        public DefaultNavigation.Builder setLeftImg(int leftImg){
            this.params.leftImg=leftImg;
            return this;
        }
        //设置右边的图片
        public DefaultNavigation.Builder setRightImg(int rightImg){
            this.params.rightImg=rightImg;
            Log.i("jc",rightImg+"----");
            return this;
        }
        public DefaultNavigation.Builder setLeftText(int leftText){
            this.params.leftText = this.params.getString(leftText);
            return this;
        }

        public DefaultNavigation.Builder setLeftText(String leftText){
            this.params.leftText = leftText;
            return this;
        }

        public DefaultNavigation.Builder setRightText(String rightText){
            this.params.rightText = rightText;
            return this;
        }

        public Builder setCenterText(int centerText){
            this.params.centerText = this.params.getString(centerText);
            return this;
        }

        public Builder setRightText(int rightText){
            this.params.rightText = this.params.getString(rightText);
            return this;
        }

        public Builder setRightTextColor(int rightTextColor){
            this.params.rightTextColor = rightTextColor;
            return this;
        }

        public Builder setLeftTextColor(int leftTextColor){
            this.params.leftTextColor = leftTextColor;
            return this;
        }

        public Builder setLeftOnClickListener(View.OnClickListener leftOnClickListener){
            this.params.leftOnClickListener = leftOnClickListener;
            return this;
        }

        public Builder setCenterOnClickListener(View.OnClickListener centerOnClickListener){
            this.params.centerOnClickListener = centerOnClickListener;
            return this;
        }

        public Builder setRightOnClickListener(View.OnClickListener rightOnClickListener){
            this.params.rightOnClickListener = rightOnClickListener;
            return this;
        }
        //左右图片点击事件
        public Builder setLeftImgOnClickListener(View.OnClickListener leftImgOnClickListener){
            this.params.leftOnClickListener=leftImgOnClickListener;
            return this;
        }
        public Builder setRightImgOnClickListener(View.OnClickListener rightImgOnClickListener){
            this.params.rightOnClickListener=rightImgOnClickListener;
            return this;
        }


        @Override
        public DefaultNavigation create() {
            DefaultNavigation defaultNavigation=new DefaultNavigation(params);
            defaultNavigation.createAndBind();
            return defaultNavigation;
        }

        public static class DefaultNavigationParams extends Builder.NavigationParams {

            //扩展属性
            //左边图片
            public int leftImg=0;
            //右边图片
            public int rightImg=0;
            //左边文本
            public String leftText;
            //中间的文本
            public String centerText;
            //右边的文本
            public String rightText;
            //左边字体颜色
            public int leftTextColor;
            //右边字体颜色
            public int rightTextColor;
            //左边的视图点击事件
            public View.OnClickListener leftOnClickListener;
            //中间的视图点击事件
            public View.OnClickListener centerOnClickListener;
            //右边的视图点击事件
            public View.OnClickListener rightOnClickListener;

            public DefaultNavigationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }

            public String getString(int id) {
                return context.getResources().getString(id);
            }

        }
    }
}