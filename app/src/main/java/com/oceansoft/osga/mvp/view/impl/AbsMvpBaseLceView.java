package com.oceansoft.osga.mvp.view.impl;


import com.oceansoft.osga.mvp.view.IMvpLceView;

/**
 * Created by TempCw on 2017/1/10.
 */
//用抽象类去实现
//实现类：类似适配器模式
public abstract class AbsMvpBaseLceView<M> implements IMvpLceView<M> {
    @Override
    public void showData(M data) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showLoading(boolean isPullToRefresh) {

    }

    @Override
    public void showError(Exception e, boolean isPullToRefresh) {

    }
}
