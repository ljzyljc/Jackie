package com.oceansoft.osga.config;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.oceansoft.osga.moudle.download.CheckForUpdateService;
import com.oceansoft.osga.utils.NetUtil;

/**
 * Created by TempCw on 2017/1/11.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance=null;
    private static Context context;

    public static BaseApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        instance=this;
        initApplication();
    }

    //引用系统初始化
    public void initApplication(){
        //设置本地版本号
        try {
            PackageInfo pkgInfo=getPackageManager().getPackageInfo(getPackageName(),0);
            Config.setLocalVersionCode(pkgInfo.versionCode);
            Config.setServerVersionCode(pkgInfo.versionCode);
            Log.i("jc","本地版本"+Config.getLocalVersionCode()+"远程版本"+Config.getServerVersionCode());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void checkUpgrade(boolean showtoast,boolean notAutoUpdate){
        if (NetUtil.isAvailable()){
            Intent intent=new Intent();
            intent.setClass(getApplicationContext(), CheckForUpdateService.class);
            startService(intent);


        }

    }


}
