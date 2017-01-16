package com.oceansoft.osga.moudle.usercenter.login.view;


import com.oceansoft.osga.moudle.base.view.views.BaseView;
import com.oceansoft.osga.moudle.usercenter.login.bean.LoginInfo;

/**
 * Created by TempCw on 2017/1/11.
 */

public interface ILoginView extends BaseView {



    void LoginSuccess(LoginInfo result);

    void LoginFail(String error);
}
