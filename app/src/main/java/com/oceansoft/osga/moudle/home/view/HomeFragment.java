package com.oceansoft.osga.moudle.home.view;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osga.moudle.base.view.views.AbsBaseMapFragment;
import com.oceansoft.osga.moudle.home.adapter.HomeGridAdapter;
import com.oceansoft.osga.moudle.home.adapter.ViewPagerAdapter;
import com.oceansoft.osga.moudle.home.bean.GridItem;
import com.oceansoft.osga.moudle.home.bean.NewsBean;
import com.oceansoft.osga.moudle.home.presenter.HomePresenter;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;
import com.oceansoft.osga.mvp.view.impl.AbsMvpMapFragment;
import com.oceansoft.osga.utils.BaseTools;
import com.oceansoft.osga.widget.OceanDragGridView;
import com.oceansoft.osga.widget.pageindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jackie on 2017/1/10.
 * 主页
 */


public class HomeFragment extends AbsBaseMapFragment {
    private ViewPager vp_top;
    private HomePresenter homePresenter;
    private ViewPagerAdapter viewPagerAdapter;
    private CirclePageIndicator pageIndicator;
    private ArrayList<NewsBean.DataBean> newsList;
    private OceanDragGridView oceanDragGridView;
    private HomeGridAdapter homeGridAdapter;
    private ArrayList<GridItem.DataBean> gridList;


    @Override
    public void bindPresenter() {
        homePresenter=new HomePresenter(getContext());
        putPresenter(homePresenter, new IHomeView() {


            @Override
            public void onLoadViewPagerSuccess(NewsBean newsBean) {
                if (newsBean.isSucc()) {
                    newsList = (ArrayList<NewsBean.DataBean>) newsBean.getData();
                    viewPagerAdapter = new ViewPagerAdapter(getActivity(), newsList);
                    vp_top.setAdapter(viewPagerAdapter);
                    pageIndicator.setViewPager(vp_top);
                    pageIndicator.setCurrentItem(0);
                }
            }

            @Override
            public void onLoadViewPagerFailed(String s) {
                Log.i("jc",s+"出错啦");
            }

            @Override
            public void onLoadGridViewSuccess(GridItem gridItem) {
                if (gridItem.isSucc()){
                    homeGridAdapter=new HomeGridAdapter(getActivity());
                    homeGridAdapter.setmList(gridItem.getData());
                    //设置每个Item的高度
                    homeGridAdapter.setRowHeight((int) (BaseTools.getWindowsWidth(getActivity())/4*0.8));
                    oceanDragGridView.setAdapter(homeGridAdapter);

                }
            }

            @Override
            public void onLoadGridViewFailed(String s) {
                Log.i("jc",s+"出错啦");
            }
        });
    }


    @Override
    public int bindLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initContentView(View contentView) {
        initView(contentView);
        initNavigation(contentView);
        initViewPager();
        initGridView();

    }

    private void initView(View contentView) {
        vp_top= (ViewPager) contentView.findViewById(R.id.vp_top);
        pageIndicator= (CirclePageIndicator) contentView.findViewById(R.id.indicator_top);
        oceanDragGridView= (OceanDragGridView) contentView.findViewById(R.id.grid);
    }
    //获取ViewPager内容
    private void initViewPager() {
        homePresenter.getViewPagerInfo();
    }
    private void initGridView(){
        homePresenter.getGridViewInfo();
    }






    public void initNavigation(View contentView){
        DefaultNavigation.Builder builder=new
                DefaultNavigation.Builder(getContext(), (ViewGroup) contentView);
        builder.setRightImg(R.drawable.icon_message)
                .setCenterText(R.string.osga)
                .setRightImgOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"点击了消息",Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }


}
