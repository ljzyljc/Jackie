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
    private RegisterModel registerModel;

    public RegisterPresenter(Context context) {
        super(context);
        registerModel=new RegisterModel(context);
    }

    public void getAuthCode(String tel,String type){
        this.registerModel.getAuthCode(tel, type, new RegisterModel.OnGetAutoResultListener() {
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
        this.registerModel.userRegister(username, password, authcode, new RegisterModel.OnReregisterResultListener() {
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



}
