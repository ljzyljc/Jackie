package com.oceansoft.osga.mvp.view;

import com.oceansoft.osga.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/10.
 */

public interface IMvpLceView<M> extends IMvpView {
    //例如：加载
    //返回结果数据
    //显示View
    //加载错误
    public void showLoading(boolean isPullToRefresh);

    //显示视图
    public void showContent();

    //处理异常接口方法
    public void showError(Exception e,boolean isPullToRefresh);

    //绑定数据
    public void showData(M data);



}
