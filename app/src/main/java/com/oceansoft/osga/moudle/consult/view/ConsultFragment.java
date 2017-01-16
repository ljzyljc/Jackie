package com.oceansoft.osga.moudle.consult.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osga.moudle.base.view.views.AbsBaseMapFragment;
import com.oceansoft.osga.moudle.consult.adapter.ConsultAdapter;
import com.oceansoft.osga.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osga.moudle.consult.presenter.ConsultPresenter;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.qrefreshlayout.QRefreshLayout;

/**
 * Created by TempCw on 2017/1/10.
 */

public class ConsultFragment extends AbsBaseMapFragment {
    private QRefreshLayout qRefreshLayout;
    private RotateLoading loading;
    private ConsultPresenter consultPresenter;
    private ConsultAdapter consultAdapter;
    private RecyclerView recycleView;
    private ArrayList<ConsultMatter.DataBean.ListBean> consultList;

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
        qRefreshLayout= (QRefreshLayout) contentView.findViewById(R.id.refreshlayout);
        recycleView= (RecyclerView) contentView.findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setHasFixedSize(true);
        consultPresenter.getConsultList();
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
                    consultList= (ArrayList<ConsultMatter.DataBean.ListBean>) consultMatter.getData().getList();
                    consultAdapter=new ConsultAdapter(consultList,getActivity());
                    recycleView.setAdapter(consultAdapter);
                    Log.i("jc","倒是加载啊");

                }
            }

            @Override
            public void loadFail() {
                Log.i("jc","加载出错了");
            }
        });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
