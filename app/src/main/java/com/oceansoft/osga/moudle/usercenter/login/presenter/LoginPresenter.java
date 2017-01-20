package com.oceansoft.osga.moudle.usercenter.login.presenter;

import android.content.Context;
import android.util.Log;

import com.oceansoft.osga.moudle.base.presenter.BasePresenter;
import com.oceansoft.osga.moudle.usercenter.login.bean.LoginInfo;
import com.oceansoft.osga.moudle.usercenter.login.model.LoginModel;
import com.oceansoft.osga.moudle.usercenter.login.view.ILoginView;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.presenter.impl.AbsNewMvpPresenter;


/**
 * Created by TempCw on 2017/1/11.
 */
//旧的presenter
public class LoginPresenter extends BasePresenter<LoginModel,ILoginView> {
//public class LoginPresenter extends AbsNewMvpPresenter<LoginModel,ILoginView> {         //使用该类LoginActivity也要进行处理一下
//    private LoginModel loginModel;


    public LoginPresenter(Context context) {
        super(context);

    }

    @Override
    public LoginModel bindModel() {
        return new LoginModel(getContext());
    }

    public void login(String username, String password){

        getModel().login(username, password, new LoginModel.OnLoginListener() {
            @Override
            public void onLoginSuccess(LoginInfo code) {
                //新的添加的代码   问题来了，每一次在Presenter中调用方法都要出来空判断   解决办法：动态代理模式（ＡＯＰ－－－动态代理实现）
                //每当调用这个方法的时候我就要去监听
//                if (isAttachView()) {
//
//                    getView().LoginSuccess(code);
//                }
                getView().LoginSuccess(code);         //因为使用的是代理对象，代理对象不等于空，就不用进行判断了 ，
                                                    // 如果使用目标对象就要进行判断，目标对象有可能为空
            }

            @Override
            public void onLoginFail(String result) {
                getView().LoginFail(result);
//                Log.i("jc","连接失败");
            }
        });

    }

    @Override
    public void Onunsubscribe() {
        getModel().unsubscribe();
    }
}
