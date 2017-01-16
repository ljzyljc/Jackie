package com.oceansoft.osga.moudle.home.view;

import com.oceansoft.osga.moudle.base.view.views.BaseView;
import com.oceansoft.osga.moudle.home.bean.GridItem;
import com.oceansoft.osga.moudle.home.bean.NewsBean;
import com.oceansoft.osga.mvp.view.IMvpView;

/**
 * Created by TempCw on 2017/1/13.
 */

public interface IHomeView extends BaseView{
    void onLoadViewPagerSuccess(NewsBean newsBean);

    void onLoadViewPagerFailed(String s);

    void onLoadGridViewSuccess(GridItem gridItem);

    void onLoadGridViewFailed(String s);


}
