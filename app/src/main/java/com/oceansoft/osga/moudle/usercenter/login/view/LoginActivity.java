package com.oceansoft.osga.moudle.usercenter.login.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osga.moudle.download.DownloadService;
import com.oceansoft.osga.moudle.usercenter.login.bean.LoginInfo;
import com.oceansoft.osga.moudle.usercenter.login.presenter.LoginPresenter;
import com.oceansoft.osga.moudle.usercenter.register.view.RegisterUI;
import com.oceansoft.osga.mvp.view.impl.AbsMvpActivity;
import com.oceansoft.osga.mvp.view.impl.AbsMvpMapActivity;
import com.oceansoft.osga.utils.DialogUtil;
import com.oceansoft.osga.utils.SystemBarTintManager;
import com.oceansoft.osga.utils.ToastUtil;


/**
 * Created by TempCw on 2017/1/11.
 */

public class LoginActivity extends AbsMvpActivity<LoginPresenter,ILoginView> implements ILoginView, View.OnClickListener{
    private LoginPresenter loginPresenter;
    private EditText username;
    private EditText password;
    private Button btn_login;
    private Button btn_register;
    private String url="http://gaapi.jl.gov.cn:80/econsole/upload/app/ea46e738fddca12282db7c51f36448f1.apk";

    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder= (DownloadService.DownloadBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        bindNavagation(R.id.root,R.string.user_login,LoginActivity.this);
        Intent intent=new Intent(this,DownloadService.class);
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
        Toast.makeText(this,"启动服务",Toast.LENGTH_SHORT).show();

//                Subscription subscription= ApiManager.getInstance().getMainService()
//                        .getMessage()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<DateInfo>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.i("jc",e.getMessage());
//                            }
//
//                            @Override
//                            public void onNext(DateInfo s) {
//                                Log.i("jc",s.getStatusCode()+"结果");
//                            }
//                        });

                //版本检测
//                Subscription subscription=ApiManager.getInstance().getMainService()
//                        .getVersion()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<TestInfo>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.i("jc",e.getMessage());
//                            }
//
//                            @Override
//                            public void onNext(TestInfo testInfo) {
//                                Log.i("jc",testInfo.getStatusCode()+"testInfo");
//                            }
//                        });
                //登录
//                Requestbean_login requestbeanLogin=new Requestbean_login();
//                requestbeanLogin.setLoginId("17715369021");
//                requestbeanLogin.setPassword("password");
//                requestbeanLogin.setPt("A");
//                requestbeanLogin.setUserSrc(1);
//                Gson gson=new Gson();
//                String loginjson=gson.toJson(requestbeanLogin);
//
//                RequestBody requestBody=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),loginjson);
//
//
//                Subscription subscription=ApiManager.getInstance().getMainService()
//                        .login(requestBody)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<LoginInfo>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.i("jc",e.getMessage());
//                            }
//
//                            @Override
//                            public void onNext(LoginInfo loginInfo) {
//                                Log.i("jc",loginInfo.getStatusCode()+loginInfo.getData().getLoginId()+"loginljc");
//                            }
//                        });


    }


    @Override
    public LoginPresenter bindPresenter() {
        return new LoginPresenter(LoginActivity.this);
    }

    @Override
    public ILoginView bindView() {
        return this;
    }
    @Override
    public void LoginSuccess(LoginInfo result) {
        ToastUtil.showToast(LoginActivity.this,"登录成功");
        LoginActivity.this.finish();
    }

    @Override
    public void LoginFail(String error) {
        ToastUtil.showToast(LoginActivity.this,error);
    }


    //使用该方法(同时继承AbsMVPMapActivity)，每个Activity都得创建一个Map(感觉不够好啊)
    //    @Override
//    public void bindPresenter() {
//        loginPresenter=new LoginPresenter(this);
//        putPresenter(loginPresenter, new ILoginView() {
//
//            @Override
//            public void LoginSuccess(LoginInfo result) {
//                ToastUtil.showToast(LoginActivity.this,"登录成功");
////                DialogUtil.showLoadDialog(LoginActivity.this,"登录成功");
//                LoginActivity.this.finish();
//            }
//
//            @Override
//            public void LoginFail(String error) {
//                ToastUtil.showToast(LoginActivity.this,error);
//            }
//        });
//
//    }
    private void initView(){
        username= (EditText) findViewById(R.id.txt_name);
        password= (EditText) findViewById(R.id.txt_pwd);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_register= (Button) findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
//                getPresenter().login(username.getText().toString(),password.getText().toString());
                if (downloadBinder==null){
                    Log.i("jc","downLoad为空");
                }else{
                    downloadBinder.startDownLoad(url);
                }
                break;
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this, RegisterUI.class));
                break;
            default:
                break;

        }
    }


}
