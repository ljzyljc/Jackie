package com.oceansoft.osga.moudle.consult.view;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osga.moudle.base.view.views.AbsBaseMapFragment;
import com.oceansoft.osga.moudle.consult.adapter.ConsultAdapter;
import com.oceansoft.osga.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osga.moudle.consult.presenter.ConsultPresenter;
import com.oceansoft.osga.widget.DividerItemDecoration;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;




/**
 * Created by TempCw on 2017/1/10.
 */

public class ConsultFragment extends AbsBaseMapFragment {
    private XRefreshView xRefreshView;
    private boolean mIsRefreshing=false;  //刷新
    private boolean mIsLoading=false;    //加载


    private RotateLoading loading;
    private ConsultPresenter consultPresenter;
    private ConsultAdapter consultAdapter;
    private RecyclerView recycleView;
    private int pageNum=1;   //加载的页数
    private ArrayList<ConsultMatter.DataBean.ListBean> consultList=new ArrayList<>();
    private Handler mHandler=new Handler();

    @Override
    public int bindLayoutId() {
        return R.layout.fragment_consult;
    }

    @Override
    public void initContentView(View contentView) {
        initNavigation(contentView);
        initView(contentView);

    }

    private void initView(View contentView) {
        xRefreshView= (XRefreshView) contentView.findViewById(R.id.refreshlayout);
        xRefreshView.setPullRefreshEnable(true); //下拉刷新
        xRefreshView.setPullLoadEnable(true);   //上拉加载
        xRefreshView.setPinnedTime(1000);      //设置刷新完后headView固定的时间
        xRefreshView.setAutoLoadMore(true);
        recycleView= (RecyclerView) contentView.findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        consultAdapter=new ConsultAdapter(consultList,getActivity());
        recycleView.setAdapter(consultAdapter);
        //设置这个才有上拉加载的加载组件
        consultAdapter.setCustomLoadMoreView(new XRefreshViewFooter(getContext()));
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        recycleView.addItemDecoration(new DividerItemDecoration(10));
        //静默加载模式不能设置footerView
        //设置自动刷新
//        xRefreshView.setAutoLoadMore(true);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh() {
                super.onRefresh();
                if (mIsLoading){
                    xRefreshView.stopRefresh();
                    return;
                }

                pageNum=1;
              //  consultList.clear();   //如果在这里添加了clear，就会报数组越界异常（解决）
                consultPresenter.getConsultList(pageNum);
                mIsRefreshing=true;
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                Log.i("jc","onLoadMore");
                if (mIsRefreshing){
                    xRefreshView.stopLoadMore();
                    return;
                }
                pageNum++;
                consultPresenter.getConsultList(pageNum);
                mIsLoading=true;
            }
        });

//        consultList=new ArrayList<>();
//        consultAdapter=new ConsultAdapter(consultList,getActivity());
//        recycleView.setAdapter(consultAdapter);
//        //设置这个才有上拉加载的加载组件
//        consultAdapter.setCustomLoadMoreView(new XRefreshViewFooter(getContext()));
        //请求数据
        consultPresenter.getConsultList(pageNum);

    }



    private void initNavigation(View contentView) {
        DefaultNavigation.Builder builder=new DefaultNavigation.Builder(getContext(), (ViewGroup) contentView);
        builder.setCenterText(R.string.consult)
                .setRightImg(R.drawable.icon_ask)
                .setRightImgOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"点击了咨询",Toast.LENGTH_SHORT).show();
                    }
                }).create();

    }

    @Override
    public void bindPresenter() {
        consultPresenter=new ConsultPresenter(getContext());
        putPresenter(consultPresenter, new IConsultView() {
            @Override
            public void hideLoading() {

            }

            @Override
            public void showLoading() {

            }

            @Override
            public void update() {

            }

            @Override
            public void loadMore() {

            }

            @Override
            public void showError() {

            }

            @Override
            public void load(ConsultMatter consultMatter) {
                if (consultMatter.isSucc()){
                    //关闭控件
                    closeLoadingOrRefreshing();

                    consultList.addAll(consultMatter.getData().getList());
                    consultAdapter.notifyDataSetChanged();
//                    consultList= (ArrayList<ConsultMatter.DataBean.ListBean>) consultMatter.getData().getList();
                    Log.i("jc",consultList.size()+"----");
                    Log.i("jc","倒是加载啊"+consultList.size());

                }
            }

            @Override
            public void loadFail() {
                //关闭控件
               closeLoadingOrRefreshing();
            }
        });
    }
    //加载成功时或者失败时----关闭控件
    public void closeLoadingOrRefreshing(){
        if (mIsRefreshing){
            xRefreshView.stopRefresh();
            consultList.clear();         //在这里设置当下拉刷新的时候，立马上拉加载不会出错（解决）
            mIsRefreshing=false;
        }
        if (mIsLoading){
            Log.i("jc","加载更多");
//          xRefreshView.setLoadComplete(true);
            xRefreshView.stopLoadMore(false);
            mIsLoading=false;
        }
    }
    //下拉加载时不允许上拉刷新，上拉刷新时不允许下拉加载
    public void NotAllowLoadingOrRefreshing(){
        if (mIsLoading){

            return;
        }
        if (mIsRefreshing){
            return;
        }

    }





    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
