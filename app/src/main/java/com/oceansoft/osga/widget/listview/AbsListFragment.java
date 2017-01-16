package com.oceansoft.osga.widget.listview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.base.adapter.AbsAdapter;
import com.oceansoft.osga.utils.NetUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xm on 2014/9/28.
 */
public abstract class AbsListFragment<T> extends Fragment implements AdapterView.OnItemClickListener {
    /**
     * 当前查询页
     */
    protected int mPageIndex = 1;

    /**
     * 总页数
     */
    protected int mTotal;

    /**
     * 每页的查询条数
     */
    public static int mPageSize = 10;

    protected PullToRefreashLoadingListView<T> mListView;

    protected AbsAdapter<T> adapter = null;

    /**
     * 是否每次进入该页面都要刷新
     */
    protected boolean mREFREASHONRESUME = false;

    protected View view;

    protected List<T> mList = new ArrayList<T>();

    protected int mLayout = R.layout.list_ui_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(mLayout, container, false);

        mListView = (PullToRefreashLoadingListView) view.findViewById(R.id.listview);

        adapter = getAdapter();
        adapter.setmList(mList);

        mListView.setAdapter(adapter);

        if (!mREFREASHONRESUME) {
            if (adapter == null || mList.size() <= 0) {
                sendRequest();
            }
        }

        mListView.setLoadingListener(new PullToRefreashLoadingListView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                sendRequest();
            }

            @Override
            public void onMore() {
                if (NetUtil.isAvailable()) {
                    ++mPageIndex;
                    sendRequest();
                } else
                    Toast.makeText(getActivity(), "网络不可用", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void reLoad() {
                mPageIndex = 1;
                sendRequest();
            }
        });

        mListView.setOnItemClickListener(this);

        return view;
    }

    protected abstract AbsAdapter<T> getAdapter();

    protected abstract void sendRequest();

//    public abstract class BaseResultHandler extends ResultHandler {
//        protected void onTimeOut() {
//            super.onTimeOut();
//            mListView.notifyDateSet(0, mPageIndex, new ArrayList<T>(), false);
//        }
//
//        protected void onNetUnavailable() {
//            mListView.notifyDateSet(0, mPageIndex, new ArrayList<T>(), false);
//        }
//
//        protected void onFailure(String message) {
//            mListView.notifyDateSet(0, mPageIndex, new ArrayList<T>(), false);
//        }
//    }
}
