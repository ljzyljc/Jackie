package com.oceansoft.osga.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;


import com.oceansoft.osga.moudle.base.adapter.AbsAdapter;
import com.oceansoft.osga.utils.NetUtil;

import java.util.List;

/**
 * 下拉刷新，上拉加载更多
 * Created by xm on 2014/9/29.
 */
public class PullToRefreashLoadingListView<T> extends LinearLayout {

    private PullRefreshListView mListView = null;

    private AbsAdapter<T> adapter = null;
    private List<T> mList = null;

    private EmptyView emptyView = null;
    private ReloadView reloadView = null;
    private LoadingView loadingView = null;

    private LinearLayout emptylayout;

    private int currentMode = MODE_AIDL;

    public void reset() {
        mList.clear();
        if (adapter != null)
            adapter.notifyDataSetChanged();
        emptyView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        reloadView.setVisibility(GONE);
        mListView.loadFull(false);
        currentMode = MODE_AIDL;
    }

    public PullToRefreashLoadingListView(Context context) {
        super(context);
        setOrientation(VERTICAL);

        initView(context);
    }

    public void setEmptyText(String emptyText) {
        if (emptyView != null)
            emptyView.setEmptyText(emptyText);

    }

    public PullToRefreashLoadingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        initView(context);
    }

    /**
     * 设置适配器 必须调用
     *
     * @param adapter
     */
    public void setAdapter(AbsAdapter adapter) {
        this.adapter = adapter;
        mListView.setAdapter(this.adapter);
        this.mList = this.adapter.getmList();
    }

    private void initView(Context context) {
        mListView = new PullRefreshListView(context);
        mListView.setPullLoadEnable(true);
        mListView.setPullRefreshEnable(true);
        emptylayout = new LinearLayout(getContext());
        emptylayout.setOrientation(VERTICAL);

        emptyView = new EmptyView(context);
        reloadView = new ReloadView(context);
        loadingView = new LoadingView(context);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        addView(mListView, params);
        addView(emptylayout, params);
        mListView.setEmptyView(emptylayout);
        emptylayout.addView(emptyView, params);
        emptylayout.addView(reloadView, params);
        emptylayout.addView(loadingView, params);

        emptyView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        reloadView.setVisibility(GONE);

        mListView.setPullListener(new PullRefreshListView.PullListener() {
            @Override
            public void onRefresh() {
                currentMode = MODE_REFREASH;
                loadingListener.onRefresh();
            }

            @Override
            public void onMore() {
                currentMode = MODE_LOADMORE;
                loadingListener.onMore();
            }
        });

        emptyView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emptyView != null) {
                    reloadView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    loadingView.setVisibility(View.VISIBLE);
                    currentMode = MODE_AIDL;
                    loadingListener.reLoad();
                }
            }
        });

        reloadView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reloadView != null) {
                    reloadView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    loadingView.setVisibility(View.VISIBLE);
                    currentMode = MODE_AIDL;
                    loadingListener.reLoad();
                }
            }
        });
    }

    public void notifyDateSet(int totalpages, int pageIndex, List<T> list, boolean succ) {
        if (!NetUtil.isAvailable()) {
            if (mList.size() == 0) {
                //reload();
                empty();
            }
        } else {
            if (!succ) {//请求失败
                if (mList.size() <= 0) {//列表为空
                    //reload();
                    empty();
                }
            } else {//请求成功
                if (totalpages <= 0 && list.size() <= 0) {//无数据
                    empty();
                    mListView.stopRefresh();
                    mListView.stopLoadMore();
                    mListView.updateRefreshTime();
                    adapter.notifyDataSetChanged();
                    return;
                }
                switch (currentMode) {
                    case MODE_AIDL:
                    case MODE_LOADMORE:
                    case MODE_RELOAD:
                        mList.addAll(list);
                        break;
                    case MODE_REFREASH:
                        mList.clear();
                        mList.addAll(list);
                }
            }
            adapter.notifyDataSetChanged();

            if (succ && pageIndex * AbsListFragment.mPageSize >= totalpages) {//全部加载完成
                mListView.loadFull(true);
                currentMode = MODE_LOADFULL;
                loadingView.setVisibility(GONE);
            }
        }
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.updateRefreshTime();
    }

    /**
     * 没有数据显示的view
     */
    protected void empty() {
        emptyView.setVisibility(View.VISIBLE);
        reloadView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
    }

    /**
     * 重新加载
     */
    protected void reload() {
        currentMode = MODE_RELOAD;
        emptyView.setVisibility(View.GONE);
        reloadView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }

    /**
     * 刷新
     */
    public void refresh() {
        currentMode = MODE_REFREASH;
        emptyView.setVisibility(View.GONE);
        reloadView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    /**
     * 停止加载
     */
    public void stopRefresh(){
        mListView.stopRefresh();
    }

    private LoadingListener loadingListener;

    public static interface LoadingListener {
        public void onRefresh();

        public void onMore();

        public void reLoad();
    }

    public void setLoadingListener(LoadingListener listener) {
        loadingListener = listener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListView.setOnItemClickListener(listener);
    }

    /**
     * listview初始状态
     */
    public static final int MODE_AIDL = -1;
    /**
     * listview重新加载
     */
    public static final int MODE_REFREASH = 0;
    /**
     * listview加载更多
     */
    public static final int MODE_LOADMORE = 1;
    /**
     * listview全部加载完成
     */
    public static final int MODE_LOADFULL = 2;

    /**
     * listview加载出错，重新加载
     */
    public static final int MODE_RELOAD = 3;
}
