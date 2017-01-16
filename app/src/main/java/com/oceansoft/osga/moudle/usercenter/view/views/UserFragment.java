package com.oceansoft.osga.moudle.usercenter.view.views;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oceansoft.osga.R;
import com.oceansoft.osga.config.AccountModule;
import com.oceansoft.osga.config.SharePreferenceManager;
import com.oceansoft.osga.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osga.moudle.base.view.views.AbsBaseMapFragment;
import com.oceansoft.osga.moudle.usercenter.login.view.LoginActivity;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;


/**
 * Created by TempCw on 2017/1/10.
 */

public class UserFragment extends AbsBaseMapFragment {

    RelativeLayout view_profile;   //登录
    private TextView mLoginName;
    private TextView mCurrVersion;
    private TextView bindMobile;
    private TextView userConfirm;

    @Override
    public int bindLayoutId() {
        return R.layout.fragment_personal_center;
    }

    @Override
    public void initContentView(View contentView) {
        Log.i("jc","initContentView");
        initNavigation(contentView);
        initMainView(contentView);
    }

    private void initMainView(View contentView) {
        view_profile= (RelativeLayout) contentView.findViewById(R.id.view_profile);
        mLoginName = (TextView) contentView.findViewById(R.id.txt_login_id);
        mCurrVersion = (TextView) contentView.findViewById(R.id.txt_curr_version);
        bindMobile = (TextView) contentView.findViewById(R.id.bind_mobile);
        userConfirm = (TextView) contentView.findViewById(R.id.txt_user_confirm);
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });

    }

    private void initNavigation(View contentView) {
        DefaultNavigation.Builder builder=new DefaultNavigation.Builder(getContext(), (ViewGroup) contentView);
        builder.setCenterText(R.string.user).create();

    }

    @Override
    public void bindPresenter() {
    }




    @Override
    public void onResume() {
        super.onResume();
        if (AccountModule.getModule().isLogin()){
            Log.i("jc","登录号码"+SharePreferenceManager.getSharp().getLoginId());
            mLoginName.setText(SharePreferenceManager.getSharp().getLoginId());
        }

    }
}
