package com.oceansoft.osga.mvp.presenter.impl;

import android.content.Context;

import com.oceansoft.osga.mvp.model.IMvpModel;
import com.oceansoft.osga.mvp.presenter.IMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by TempCw on 2017/1/19.
 * 弱引用
 * 用弱引用去持有对象，当这个对象一旦被释放（或者置为空），它就会自动释放
 */

public abstract class AbsNewMvpPresenter<M extends IMvpModel,V extends IMvpView> implements IMvpPresenter<V> {
    private V proxy;
    private WeakReference<Context> weakContext;
    private WeakReference<V> weakView;

    public AbsNewMvpPresenter(Context context){
        this.weakContext=new WeakReference<Context>(context);
    }
    public Context getContext(){
        return weakContext.get();
    }
    private M model;
    public M bindModel(){
        return model;
    }

    public M getModel() {
        this.model=bindModel();
        return model;
    }

    public V getView(){
        //这个时候应该返回代理对象
        return proxy;

//        if (this.weakContext==null){
//            return null;
//        }
//        return this.weakView.get();
    }

    /**
     * 用于检测View是否为空对象
     * @return
     */
    public boolean isAttachView(){
        if(this.weakView!=null&&this.weakView.get()!=null){

            return true;
        }
        return false;
    }


    @Override
    public void attachView(V view) {
        this.weakView=new WeakReference<V>(view);
        MvpViewInvocationHandler handler=new MvpViewInvocationHandler(
                this.weakView.get());
        //在这里采用动态代理
         proxy=(V)Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(),
                handler);



    }

    @Override
    public void detachView() {
        if (this.weakView!=null){
            this.weakView.clear();
            this.weakView=null;
        }
    }
    private class MvpViewInvocationHandler implements InvocationHandler{

        //目标对象
        private IMvpView mvpView;
        public MvpViewInvocationHandler(IMvpView mvpView){
            this.mvpView=mvpView;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects)
                throws Throwable {
            if (isAttachView()){
                return method.invoke(mvpView,objects);
            }

            return null;
        }
    }



}
