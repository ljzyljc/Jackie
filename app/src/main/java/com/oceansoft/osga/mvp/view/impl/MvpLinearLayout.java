package com.oceansoft.osga.mvp.view.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.oceansoft.osga.mvp.presenter.IMvpPresenter;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/19.
 * 有时候在View层也要访问网络（比如Volley在ImageView中访问网络）
 */

public abstract class MvpLinearLayout<P extends IMvpPresenter<V>,V extends IMvpView> extends LinearLayout {
    private P presenter;
    private V view;


    public MvpLinearLayout(Context context) {
        super(context);
        init();
    }

    public P getPresenter() {
        return presenter;
    }
    //没必要，因为用的AbsNewMvpPresenter中有了
//    public V getView() {
//        return view;
//    }

    public MvpLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化
        init();
    }
    private void init(){
        if (this.presenter==null){
            this.presenter=bindPresenter();
        }
        if (this.view==null){
            this.view=bindView();
            this.presenter.attachView(view);
        }



    }
    public abstract P bindPresenter();
    public abstract V bindView();

    //View 的销毁
//    当remove一个view时,这个view是如何销毁的 ? 会执行自身相关的方法吗 ?
//    最佳答案: remove后会执行onDetachedFromWindow(),一般都在这个方法类做一些操作。
//    另外还有些别的情况,但一般都不常用之后如果没有引用就会被销毁。

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.presenter!=null){
            this.presenter.detachView();
            this.presenter=null;
        }


    }
}
