package com.oceansoft.osga.moudle.download;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.oceansoft.osga.config.Config;
import com.oceansoft.osga.http.rxjavahttp.ApiManager;
import com.oceansoft.osga.utils.NetUtil;
import com.oceansoft.osga.utils.NetWorkUtil;
import com.oceansoft.osga.utils.ToastUtil;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TempCw on 2017/1/18.
 */

public class CheckForUpdateService extends Service {
    private static final String TAG = "CheckUpgradeService";
    public static final String ACTION_CHECK_APP_VERSION = "android.intent.action.CHECK_APP_UPGRADE";
    public static final String EXTRA_SHOW_TOAST = "android.intent.extra.showtoast";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (null!=intent&&ACTION_CHECK_APP_VERSION.equals(intent.getAction())){
//            CheckUpgrade(intent.getBooleanExtra(EXTRA_SHOW_TOAST,false));
//        }
        CheckUpgrade(true);
        // START_STICKY告诉操作系统重新创建服务后，它有足够的内存和调用onStartCommand（）再次空意图。
        // START_NOT_STICKY告诉操作系统懒得再重新创建该服务。
        // 还有第三个code START_REDELIVER_INTENT告诉操作系统来重新创建服务及返还相同的意向onStartCommand（）。
        return Service.START_NOT_STICKY;
    }

    private void CheckUpgrade(boolean booleanExtra) {
        if (!NetUtil.isAvailable()){
            ToastUtil.showToast(this,"网络不可用");
            return;
        }else{
            Subscription subscription= ApiManager.getInstance().getMainService()
                    .getVersionMessage("https://gaapi.jl.gov.cn:443/econsole/api/version/1/"+ Config.getLocalVersionCode())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DownLoadMessage>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("jc",e.getMessage()+"错误");
                        }

                        @Override
                        public void onNext(DownLoadMessage downLoadMessage) {
                            Log.i("jc",downLoadMessage.getStatusCode()+"-版本-");
                            if (downLoadMessage.isSucc()){
                                showUpgradeDialog(downLoadMessage.getData());
                            }
                        }
                    });
        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void showUpgradeDialog(DownLoadMessage.DataBean updateInfo){
        Intent intent=new Intent(getApplicationContext(),UpgradeDialog.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("updateinfo",updateInfo); //updateInfo实现Seriable接口
        startActivity(intent);


    }

}
