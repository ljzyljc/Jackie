/**
 * @file PullRefreshListView.java
 * @package me.maxwin.view
 * @create Mar 18, 2012 6:28:41 PM
 * @author Maxwin
 * @description An ListView support (a) Pull down to refresh, (b) Pull up to load more.
 * Implement PullListener, and see stopRefresh() / stopLoadMore().
 */
package com.oceansoft.osga.widget.listview;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.oceansoft.osga.R;
import com.oceansoft.osga.config.SharePreferenceManager;


/**
 * 自定义下拉刷新列表
 */
public class PullRefreshListView extends ListView implements OnScrollListener {

    private int mScrollBack;
    private float mLastY = -1; // save event y
    private Scroller mScroller; // used for scroll back
    private OnScrollListener mScrollListener; // user's scroll listener

    private final static int SCROLLBACK_HEADER = 0;
    private final static int SCROLLBACK_FOOTER = 1;

    //头部或底部回滚持续时长（400ms)
    private final static int SCROLL_DURATION = 400;

    //下载头部最大垂直距离（50px)
    private final static int PULL_LOAD_MORE_DELTA = 50;
    private final static float OFFSET_RADIO = 1.0f; // support iOS like pull

    // 刷新或加载更多回调接口监听
    private PullListener mListViewListener;

    // 顶部视图参数
    private ListViewHeader mHeaderView;
    //顶部视图内容视图，用于计算头部高度及刷新结束时隐藏头部内容
    private RelativeLayout mHeaderViewContent;
    private TextView mHeaderTimeView;
    private int mHeaderViewHeight; // header view's height
    private boolean mEnablePullRefresh = true;
    private boolean mPullRefreshing = false; // is refreashing.
    private boolean mLoadFull = false;//已加载全部数据

    //底部视图参数
    private ListViewFooter mFooterView;
    private boolean mEnablePullLoad = true;//打开上拉加载
    private boolean mPullLoading;
    private boolean mIsFooterReady = false;

    //列表项全部加载项数量，用户判断ListView是否已经到达底部
    private int mTotalItemCount;
    private int mVisibleItemCount;
    //更新时间
    /**
     * 一分钟的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MINUTE = 60 * 1000;

    /**
     * 一小时的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    /**
     * 一天的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * 一月的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MONTH = 30 * ONE_DAY;

    /**
     * 一年的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_YEAR = 12 * ONE_MONTH;

    private long lastRefreshTime;

    /**
     * 默认构造
     *
     * @param context 上下文
     */
    public PullRefreshListView(Context context) {
        super(context);
        initWithContext(context);
    }

    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithContext(context);
    }

    public PullRefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWithContext(context);
    }

    private void initWithContext(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());
        // PullRefreshListView need the scroll event, and it will dispatch the event to
        // user's listener (as a proxy).
        super.setOnScrollListener(this);
        // init header view
        mHeaderView = new ListViewHeader(context);
        mHeaderViewContent = (RelativeLayout) mHeaderView.findViewById(R.id.header_content);
        mHeaderTimeView = (TextView) mHeaderView.findViewById(R.id.refresh_time);
        addHeaderView(mHeaderView);
        // init footer view
        mFooterView = new ListViewFooter(context);
        // init header height
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeaderViewHeight = mHeaderViewContent.getHeight();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= 9) {
            this.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
    }

    @Override
    public void setAdapter(final ListAdapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).registerDataSetObserver(new DataSetObserver() {

                @Override
                public void onChanged() {
                    super.onChanged();
                    adjustFooter(adapter);
                }
            });
        }
    }

    private void adjustFooter(ListAdapter adapter) {
        if (adapter == null) return;
        if (adapter.getCount() > 0) {
            if (getFirstVisiblePosition() <= getHeaderViewsCount() && (getFirstVisiblePosition() + mVisibleItemCount >= mTotalItemCount)) {
                if (mIsFooterReady) {
                    mIsFooterReady = false;
                    removeFooterView(mFooterView);
                }
            } else {
                if (!mIsFooterReady) {
                    mIsFooterReady = true;
                    addFooterView(mFooterView);
                }
            }
        } else {
            if (mIsFooterReady) {
                mIsFooterReady = false;
                removeFooterView(mFooterView);
            }
        }
    }

    /**
     * 开启或关闭下拉刷新
     *
     * @param enable true打开false关闭
     */
    public void setPullRefreshEnable(boolean enable) {
        mEnablePullRefresh = enable;
        if (!mEnablePullRefresh) { // disable, hide the content
            mHeaderViewContent.setVisibility(View.INVISIBLE);
        } else {
            mHeaderViewContent.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 打开或关闭上拉加载更多
     *
     * @param enable 开启下拉加载
     */
    public void setPullLoadEnable(boolean enable) {
        mEnablePullLoad = enable;
        if (!mEnablePullLoad) {
            mFooterView.hide();
            mFooterView.setOnClickListener(null);
        } else {
            mPullLoading = false;
            mFooterView.show();
            mFooterView.setState(ListViewFooter.STATE_NORMAL);
            //单击同样也会调用加载更多
            mFooterView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mLoadFull)
                        startLoadMore();
                }
            });
        }
    }

    public boolean isRefreshing() {
        return mPullRefreshing;
    }

    public boolean isLoadingMore() {
        return mPullLoading;
    }

    /**
     * 停止刷新，重置头部视图
     */
    public void stopRefresh() {
        while (System.currentTimeMillis() - lastRefreshTime < 500) {
            continue;
        }
        if (mPullRefreshing) {
            mPullRefreshing = false;
            resetHeaderHeight();
        }
    }

    /**
     * stop load more, reset footer view.
     */
    public void stopLoadMore() {
        if (mPullLoading) {
            mPullLoading = false;
            if (!mLoadFull) {
                mFooterView.setState(ListViewFooter.STATE_NORMAL);
            } else {
                mFooterView.setState(ListViewFooter.STATE_FULL);//已加载全部
            }
        }
    }

    public void loadFull(Boolean isLoadFull) {
        mLoadFull = isLoadFull;
        if (!mLoadFull) {
            mFooterView.setState(ListViewFooter.STATE_NORMAL);
        } else {
            mFooterView.setState(ListViewFooter.STATE_FULL);//已加载全部
        }
    }

    /**
     * 设置最后一次刷新时间
     *
     * @param time 最后一次刷新时间字符串
     */
    public void updateRefreshTime(String time) {
        mHeaderTimeView.setText(time);
    }

    public void updateRefreshTime() {
        mHeaderTimeView.setText(getRefreshTime());
        SharePreferenceManager.setRefreshTime(getId());
    }

    private String getRefreshTime() {
        long lastUpdateTime = SharePreferenceManager.getRefreshTime(getId());
        long timePassed = System.currentTimeMillis() - SharePreferenceManager.getRefreshTime(getId());
        if (lastUpdateTime == -1) {
            return getResources().getString(R.string.not_updated_yet);
        } else if (timePassed < 0) {
            return getResources().getString(R.string.time_error);
        } else if (timePassed < ONE_MINUTE) {
            return getResources().getString(R.string.updated_just_now);
        } else if (timePassed < ONE_HOUR) {
            return getResources().getString(R.string.updated_at, String.format("%d分钟", timePassed / ONE_MINUTE));
        } else if (timePassed < ONE_DAY) {
            return getResources().getString(R.string.updated_at, String.format("%d小时", timePassed / ONE_HOUR));
        } else if (timePassed < ONE_MONTH) {
            return getResources().getString(R.string.updated_at, String.format("%d天", timePassed / ONE_DAY));
        } else if (timePassed < ONE_YEAR) {
            return getResources().getString(R.string.updated_at, String.format("%d个月", timePassed / ONE_MONTH));
        } else {
            return getResources().getString(R.string.updated_at, String.format("%d年", timePassed / ONE_YEAR));
        }
    }

    private void invokeOnScrolling() {
        if (mScrollListener instanceof OnListViewScrollListener) {
            OnListViewScrollListener l = (OnListViewScrollListener) mScrollListener;
            l.onScrolling(this);
        }
    }

    private void updateHeaderHeight(float delta) {
        if (delta > 0) {
            if (mHeaderView.getVisiableHeight() < mHeaderViewHeight) {
                if ((int) delta + mHeaderView.getVisiableHeight() < mHeaderViewHeight) {
                    mHeaderView.setVisiableHeight((int) delta + mHeaderView.getVisiableHeight());
                } else {
                    mHeaderView.setVisiableHeight(mHeaderViewHeight);
                }
            }
        } else {
            if (delta + mHeaderView.getVisiableHeight() > 0) {
                mHeaderView.setVisiableHeight((int) (delta + mHeaderView.getVisiableHeight()));
            }
        }
        if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
            if (mHeaderView.getVisiableHeight() >= mHeaderViewHeight) {
                mHeaderView.setState(ListViewHeader.STATE_READY);
            } else {
                if (mHeaderViewHeight - mHeaderView.getVisiableHeight() > (mHeaderViewHeight / 5)) {
                    mHeaderView.setState(ListViewHeader.STATE_NORMAL);
                }
            }
        }
        setSelection(0); // scroll to top each time
    }

    /**
     * 重置头部视图高度
     */
    private void resetHeaderHeight() {
        int height = mHeaderView.getVisiableHeight();
        if (height == 0) // 不可见状态.
            return;
        // refreshing and header isn't shown fully. do nothing.
        if (mPullRefreshing && height <= mHeaderViewHeight) {//正在刷新并且头部显示不完全则停止重置
            return;
        }
        int finalHeight = 0; // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (mPullRefreshing && height > mHeaderViewHeight) {
            finalHeight = mHeaderViewHeight;
        }
        mScrollBack = SCROLLBACK_HEADER;
        mScroller.startScroll(0, height, 0, finalHeight - height, SCROLL_DURATION);
        invalidate();
    }

    private void updateFooterHeight(float delta) {
        int height = mFooterView.getBottomMargin() + (int) delta;
        if (mEnablePullLoad && !mPullLoading) {
            if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
                // more.
                mFooterView.setState(ListViewFooter.STATE_RELEASE_TO_LOAD);
            } else {
                if (!mLoadFull) {
                    mFooterView.setState(ListViewFooter.STATE_NORMAL);
                } else {
                    mFooterView.setState(ListViewFooter.STATE_FULL);//已加载全部
                }
            }
        }
        if (mFooterView.getBottomMargin() < 2 * PULL_LOAD_MORE_DELTA) {
            mFooterView.setBottomMargin(height);
        }
    }

    private void resetFooterHeight() {
        int bottomMargin = mFooterView.getBottomMargin();
        if (bottomMargin > 0) {
            mScrollBack = SCROLLBACK_FOOTER;
            mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
            invalidate();
        }
    }

    private void startLoadMore() {
        mPullLoading = true;
        mFooterView.setState(ListViewFooter.STATE_LOADING);
        if (mListViewListener != null) {
            mListViewListener.onMore();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {//最后一次下拉触摸点Y坐标值
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (getFirstVisiblePosition() == 0 && (mHeaderView.getVisiableHeight() >= 0 || deltaY > 0)) {//正在进行下拉
                    // the first item is showing, header has shown or pull down.
                    if (mHeaderView.getVisiableHeight() <= 0 && deltaY < 0) {
                        break;
                    }
                    updateHeaderHeight(deltaY / OFFSET_RADIO);
                    invokeOnScrolling();
                } else if (getLastVisiblePosition() == mTotalItemCount - 1 && (mFooterView.getBottomMargin() > 0 || deltaY < 0) && !mLoadFull) {//正在进行上拉
                    // last item, already pulled up or want to pull up.
                    updateFooterHeight(-deltaY / OFFSET_RADIO);
                }
                break;
            default:
                mLastY = -1; // reset
                if (getFirstVisiblePosition() == 0) {
                    // invoke refresh
                    if (mEnablePullRefresh && mHeaderView.getmState() == ListViewHeader.STATE_READY && ev.getAction() == MotionEvent.ACTION_UP) {
                        mPullRefreshing = true;
                        mHeaderView.setState(ListViewHeader.STATE_REFRESHING);
                        if (mListViewListener != null) {
                            mListViewListener.onRefresh();
                            lastRefreshTime = System.currentTimeMillis();
                            loadFull(false);
                            if (!mLoadFull) {
                                mFooterView.setState(ListViewFooter.STATE_NORMAL);
                            } else {
                                mFooterView.setState(ListViewFooter.STATE_FULL);//已加载全部
                            }
                        }
                    }
                    resetHeaderHeight();
                } else if (getLastVisiblePosition() == mTotalItemCount - 1 && !mLoadFull) {
                    // invoke load more.
//                    if (mEnablePullLoad && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA) {
                    startLoadMore();
//                    }
                    resetFooterHeight();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (mScrollBack == SCROLLBACK_HEADER) {
                mHeaderView.setVisiableHeight(mScroller.getCurrY());
            } else {
                mFooterView.setBottomMargin(mScroller.getCurrY());
            }
            postInvalidate();
            invokeOnScrolling();
        }
        super.computeScroll();
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mScrollListener = l;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mScrollListener != null) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // send to user's listener
        mTotalItemCount = totalItemCount;
        if (mScrollListener != null) {
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
        mVisibleItemCount = visibleItemCount;
        if (null != mFooterView) {
            adjustFooter(getAdapter());
        }
    }

    public void setPullListener(PullListener l) {
        mListViewListener = l;
    }

    /**
     * you can listen ListView.OnScrollListener or this one. it will invoke
     * onScrolling when header/footer scroll back.
     */
    public interface OnListViewScrollListener extends OnScrollListener {
        public void onScrolling(View view);
    }

    /**
     * implements this interface to get refresh/load more event.
     */
    public interface PullListener {
        /**
         * 在列表刷新时回调此接口
         */
        public void onRefresh();

        /**
         * 在上拉加载更多时回调此接口
         */
        public void onMore();
    }
}
