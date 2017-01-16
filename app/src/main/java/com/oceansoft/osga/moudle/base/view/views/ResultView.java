package com.oceansoft.osga.moudle.base.view.views;


import com.oceansoft.osga.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/10.
 */

public interface ResultView<M> extends IMvpView {
    public void onResult(M data,String errorMessage);
}
