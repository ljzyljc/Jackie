package com.oceansoft.osga.config;

import android.util.Log;

import com.oceansoft.osga.moudle.usercenter.login.bean.LoginInfo;


/**
 * Created by TempCw on 2017/1/11.
 */

public class AccountModule {
    private static AccountModule module=null;
    public static AccountModule getModule(){
        if (module==null){
            synchronized (AccountModule.class){
                if (module==null){
                    module=new AccountModule();
                }
            }
        }
        return module;
    }
    private SharePreferenceManager sharePrefer=SharePreferenceManager.getSharp();
    public boolean isLogin(){
        return sharePrefer.getLoginStatus();
    }
    public void setLoginSuccess(LoginInfo.DataBean dataBean){
        sharePrefer.setAddress(dataBean.getAddress());
        sharePrefer.setLoginId(dataBean.getLoginId());
        sharePrefer.setGuid(dataBean.getGuid());
        Log.i("jc","==="+dataBean.getLoginId()+dataBean.getGuid());

        sharePrefer.setLoginStatus(true);


//        Log.i("jc",SharePreferenceManager.getSharp().getLoginId()+"--账号--");

    }




}
