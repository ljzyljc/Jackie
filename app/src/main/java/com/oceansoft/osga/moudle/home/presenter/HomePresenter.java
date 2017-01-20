package com.oceansoft.osga.moudle.home.presenter;

import android.content.Context;

import com.oceansoft.osga.moudle.base.presenter.BasePresenter;
import com.oceansoft.osga.moudle.home.bean.GridItem;
import com.oceansoft.osga.moudle.home.bean.NewsBean;
import com.oceansoft.osga.moudle.home.model.HomeModel;
import com.oceansoft.osga.moudle.home.view.IHomeView;

/**
 * Created by TempCw on 2017/1/13.
 */

public class HomePresenter extends BasePresenter<HomeModel,IHomeView> {
//    private HomeModel homeModel;

    public HomePresenter(Context context) {
        super(context);
//        homeModel=new HomeModel(context);
    }

    @Override
    public HomeModel bindModel() {
        return new HomeModel(getContext());
    }

    public void getViewPagerInfo(){
        getModel().getViewPagerInfo(new HomeModel.OnViewPagerListener() {
            @Override
            public void onViewPagerSuccess(NewsBean newsBean) {
                getView().onLoadViewPagerSuccess(newsBean);
            }

            @Override
            public void onViewPagerFailed(String errorMessage) {
                getView().onLoadViewPagerFailed(errorMessage);
            }
        });

    }
    public void getGridViewInfo(){
        getModel().getGridViewInfo(new HomeModel.OnGridViewResultListener() {
            @Override
            public void onGridViewSuccess(GridItem gridItem) {
                getView().onLoadGridViewSuccess(gridItem);
            }

            @Override
            public void onGridViewFailed(String s) {
                getView().onLoadGridViewFailed(s);
            }
        });

    }

    @Override
    public void Onunsubscribe() {
        getModel().unsubscribe();
    }
}
