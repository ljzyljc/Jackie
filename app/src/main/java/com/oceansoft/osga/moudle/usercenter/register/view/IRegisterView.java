package com.oceansoft.osga.moudle.usercenter.register.view;

import com.oceansoft.osga.moudle.base.view.views.BaseView;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterAutoCodeInfo;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterResultInfo;

/**
 * Created by TempCw on 2017/1/12.
 */

public interface IRegisterView extends BaseView{

    void onStartLoading();

    void registerSuccess(RegisterResultInfo appUser);

    void registerFaild(String errrorMessage);

    void getAutoCode(RegisterAutoCodeInfo s);

    void getAutoCodeFaild(String error);

    void onFinish();

}
