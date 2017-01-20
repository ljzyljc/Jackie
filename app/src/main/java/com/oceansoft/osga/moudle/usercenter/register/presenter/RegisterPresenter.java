package com.oceansoft.osga.moudle.usercenter.register.presenter;

import android.content.Context;

import com.oceansoft.osga.moudle.base.presenter.BasePresenter;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterAutoCodeInfo;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterResultInfo;
import com.oceansoft.osga.moudle.usercenter.register.model.RegisterModel;
import com.oceansoft.osga.moudle.usercenter.register.view.IRegisterView;

/**
 * Created by TempCw on 2017/1/12.
 */

public class RegisterPresenter extends BasePresenter<RegisterModel,IRegisterView> {

    public RegisterPresenter(Context context) {
        super(context);
    }

    @Override
    public RegisterModel bindModel() {
        return new RegisterModel(getContext());
    }

    public void getAuthCode(String tel, String type){
        getModel().getAuthCode(tel, type, new RegisterModel.OnGetAutoResultListener() {
            @Override
            public void onGetAuthCodeSuccess(RegisterAutoCodeInfo autoCodeInfo) {
                getView().getAutoCode(autoCodeInfo);
            }

            @Override
            public void onGetAuthCodeFailed(String error) {
                getView().getAutoCodeFaild(error);
            }

        });

    }
    public void register(String username,String password,String authcode){
        getModel().userRegister(username, password, authcode, new RegisterModel.OnReregisterResultListener() {
            @Override
            public void onRegisterStart() {
                getView().onStartLoading();
            }

            @Override
            public void onRegisterSuccess(RegisterResultInfo resultInfo) {
                getView().registerSuccess(resultInfo);
            }

            @Override
            public void onRegisterFailed(String s) {
                getView().registerFaild(s);
            }

            @Override
            public void onRegisterFinish() {
                getView().onFinish();
            }
        });





    }

    @Override
    public void Onunsubscribe() {
        getModel().unsubscribe();
    }
}
