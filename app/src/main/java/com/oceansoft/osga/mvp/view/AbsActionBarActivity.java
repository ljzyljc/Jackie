package com.oceansoft.osga.mvp.view;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.oceansoft.osga.R;
import com.oceansoft.osga.mvp.view.impl.AbsMvpMapActivity;
import com.oceansoft.osga.utils.StatusColorUtil;

/**
 * Created by TempCw on 2017/1/17.
 *
 */

public class AbsActionBarActivity extends FragmentActivity {
    protected TextView title;
//	protected SystemBarTintManager tintManager;

    public void setTitle(CharSequence text) {
        title.setText(text);
    }

    public void setTitle(int resid) {
        title.setText(resid);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View viewTitleBar = getLayoutInflater().inflate(R.layout.actionbar_title, null);
        title = (TextView) viewTitleBar.findViewById(R.id.actionbar_title_tv);
        getActionBar().setTitle(" ");
        getActionBar().setCustomView(viewTitleBar,
                new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER));
        getActionBar().setDisplayShowCustomEnabled(true);
        // 显示返回按钮
        getActionBar().setDisplayHomeAsUpEnabled(true);
        // 隐藏app图标
        getActionBar().setDisplayShowHomeEnabled(false);
        StatusColorUtil.applyKitKatTranslucency(AbsActionBarActivity.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //回退
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
