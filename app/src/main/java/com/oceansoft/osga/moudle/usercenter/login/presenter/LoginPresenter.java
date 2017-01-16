package com.oceansoft.osga.moudle.usercenter.login.presenter;

import android.content.Context;
import android.util.Log;

import com.oceansoft.osga.moudle.base.presenter.BasePresenter;
import com.oceansoft.osga.moudle.usercenter.login.bean.LoginInfo;
import com.oceansoft.osga.moudle.usercenter.login.model.LoginModel;
import com.oceansoft.osga.moudle.usercenter.login.view.ILoginView;


/**
 * Created by TempCw on 2017/1/11.
 */

public class LoginPresenter extends BasePresenter<LoginModel,ILoginView> {
    private LoginModel loginModel;

    public LoginPresenter(Context context) {
        super(context);
        loginModel=new LoginModel(context);
    }
    public void login(String username,String password){
        this.loginModel.login(username, password, new LoginModel.OnLoginListener() {
            @Override
            public void onLoginSuccess(LoginInfo code) {
//                Log.i("jc","loginpresenter"+code.getData().getGender());

                getView().LoginSuccess(code);
            }

            @Override
            public void onLoginFail(String result) {
                getView().LoginFail(result);
//                Log.i("jc","连接失败");
            }
        });

    }


}
