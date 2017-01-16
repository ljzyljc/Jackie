package com.oceansoft.osga.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oceansoft.osga.R;


/**
 * 列表底部视图
 */
public class ListViewFooter extends LinearLayout {

    public final static int STATE_NORMAL = 0;
    public final static int STATE_RELEASE_TO_LOAD = 1;//松开加载更多
    public final static int STATE_LOADING = 2;
    public final static int STATE_FULL = 3;//已加载全部

    private View mContentView;
    private View mProgressBar;
    private TextView mHintView;

    public ListViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public ListViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setState(int state) {
        mHintView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mHintView.setVisibility(View.INVISIBLE);
        if (state == STATE_RELEASE_TO_LOAD) {//松开加载更多
            mHintView.setVisibility(View.VISIBLE);
            mHintView.setText(R.string.release_load_more);
        } else if (state == STATE_LOADING) {//正在加载
            mProgressBar.setVisibility(View.VISIBLE);
        } else if (state == STATE_FULL) {
            mHintView.setVisibility(View.VISIBLE);
            mHintView.setText(R.string.load_full_data);
        } else {
            mHintView.setVisibility(View.VISIBLE);//已加载完成或正常状态
            mHintView.setText(R.string.click_load_more);
        }
    }

    /**
     * 设置底部MarginBottom值大小
     *
     * @param height int 底部MarginBotton值
     */
    public void setBottomMargin(int height) {
        if (height < 0)
            return;
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.bottomMargin = height;
        mContentView.setLayoutParams(lp);
    }

    /**
     * 获取底部高度
     *
     * @return int 底部MarginBotton值
     */
    public int getBottomMargin() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        return lp.bottomMargin;
    }

    /**
     * 底部显示正常状态
     */
    public void normal() {
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * 底部显示正在加载
     */
    public void loading() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏或关闭列表底部加载更多区域
     */
    public void hide() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.height = 0;
        mContentView.setLayoutParams(lp);
    }

    /**
     * 显示底部加载栏
     */
    public void show() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        mContentView.setLayoutParams(lp);
    }

    private void initView(Context context) {
        LinearLayout moreView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.listview_footer, null);
        addView(moreView);
        moreView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        mContentView = moreView.findViewById(R.id.listview_footer_content);
        mProgressBar = moreView.findViewById(R.id.listview_footer_progressbar);
        mHintView = (TextView) moreView.findViewById(R.id.listview_footer_hint_textview);
    }
}
