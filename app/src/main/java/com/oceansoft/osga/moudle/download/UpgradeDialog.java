package com.oceansoft.osga.moudle.download;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.oceansoft.osga.R;
import com.oceansoft.osga.mvp.view.BaseActivity;
import com.oceansoft.osga.utils.ToastUtil;

/**
 * Created by TempCw on 2017/1/18.
 * 是否更新界面
 */

public class UpgradeDialog extends FragmentActivity implements View.OnClickListener{
    private DownLoadMessage.DataBean updateInfo;
    private TextView mDownload, mSkip;
    private TextView mUpgrade, mTxtConfirm;
    private TextView servletVersion;
    private DownloadService.DownloadBinder binder;
    private String url="http://gaapi.jl.gov.cn:80/econsole/upload/app/ea46e738fddca12282db7c51f36448f1.apk";
    private boolean isBinded=false;
    private Handler mHanler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x123:
                    servletVersion.setText("进度"+binder.getUpdeteProgress()+"%");
                    if (binder.getUpdeteProgress()>=100){
                        removeMessages(0x123);
                        finish();
                        break;
                    }
                    Log.i("jc","进度显示"+binder.getUpdeteProgress());
                    removeMessages(0x123);
                    sendEmptyMessageDelayed(0x123,200);
                    break;
            }
        }
    };

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder= (DownloadService.DownloadBinder) iBinder;
            isBinded=true;
//            binder.startDownLoad(url);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install_or_update_dialog);
        mDownload = (TextView) findViewById(R.id.btn_logout);
        mSkip = (TextView) findViewById(R.id.btn_logout_cancel);
        mDownload.setOnClickListener(this);
        mSkip.setOnClickListener(this);
        updateInfo= (DownLoadMessage.DataBean) getIntent().getSerializableExtra("updateinfo");
        Log.i("jc",updateInfo.getMd5()+"从服务器获取的MD5");
        servletVersion= (TextView) findViewById(R.id.txt_upgrade_info);
        servletVersion.setText(updateInfo.getVerCode()+"---");
        Intent intent=new Intent(this,DownloadService.class);
        intent.putExtra("updateinfo",updateInfo);
        startService(intent);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:   //更新     //如果点击更新后按钮就变成“后台运行”
                //后台运行（操作处理）
                if (binder.isStartDownload()&&mDownload.getText().toString().equals("后台运行")){
                    ToastUtil.showToast(UpgradeDialog.this,"升级程序正在后台运行...");
                    finish();
                    return;
                }
                //更新（操作处理）
                if (isBinded){
                    Log.i("jc","开始更新");
                    binder.startDownLoad(url);
                    mHanler.removeMessages(0x123);
                    mHanler.sendEmptyMessageDelayed(0x123,1000);
                    mDownload.setText("后台运行");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("jc","执行了onDestroy方法");
        if (isBinded){
            unbindService(connection);
        }
        if (mHanler!=null){
            Log.i("jc","handler置空");
            mHanler.removeCallbacksAndMessages(null);
        }
    }
}
