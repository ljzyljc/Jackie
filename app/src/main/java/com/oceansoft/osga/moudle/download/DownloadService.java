package com.oceansoft.osga.moudle.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.oceansoft.osga.MainActivity;
import com.oceansoft.osga.R;
import com.oceansoft.osga.utils.Md5Util;
import com.oceansoft.osga.utils.ToastUtil;

import java.io.File;

/**
 * Created by TempCw on 2017/1/18.
 */

public class DownloadService extends Service{
    private DownLoadTask downLoadTask;
    private String downloadUrl;
    private DownloadBinder mBinder=new DownloadBinder();
    private int mProgress;  //进度
    private DownLoadMessage.DataBean updateInfo;   //更新的信息



    private DownloadListener listener=new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            //通知栏更新进度
            getNotificationManager().notify(1,getNotification("下载...",progress));
            mProgress=progress;
        }

        @Override
        public void onSuccess() {
            downLoadTask=null;
            //下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",-1));
            Toast.makeText(DownloadService.this,"下载成功",Toast.LENGTH_SHORT).show();
            //下载成功后进行安装
            new AsyncTask<Void,Void,Boolean>(){
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory + fileName);
                @Override
                protected Boolean doInBackground(Void... voids) {
                    Log.i("jc","客户端加密的MD5"+Md5Util.md5sum(file)+"---服务器加密的MD5"+updateInfo.getMd5());
                    return Md5Util.md5sum(file).equalsIgnoreCase(updateInfo.getMd5());
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    super.onPostExecute(aBoolean);
                    //如果两个MD5不一致，不允许升级
                    if (!aBoolean) {
                        ToastUtil.showToast(DownloadService.this,"升级包损坏");
                        if (!file.delete()){
                            file.deleteOnExit();  // http://blog.sina.com.cn/s/blog_ad071a8101015wqf.html  delete和deleteOnExit的区别
                        }

                        return;
                    }
                    if (file.exists()&&file.length()>0) {
                        Log.i("jc","文件存在----");
                        //-----------------------------安装下载的APP--------------------------------
                        Uri uri=Uri.fromFile(file);
                        Intent installIntent=new Intent(Intent.ACTION_VIEW);
                        installIntent.setDataAndType(uri,"application/vnd.android.package-archive");
//                        解决在非Activity中使用startActivity
                        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(installIntent);
                    }
                }
            }.execute();



        }

        @Override
        public void onFailed() {
            downLoadTask=null;
            //下载失败时将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            Toast.makeText(DownloadService.this,"下载失败",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPause() {
            downLoadTask=null;
            Toast.makeText(DownloadService.this,"暂停下载",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            downLoadTask=null;
            stopForeground(true);
            Toast.makeText(DownloadService.this,"取消下载",Toast.LENGTH_SHORT).show();
        }
    };

//    onBind方法了，从字面可以理解到这个方法是把Activity和Service绑定了，那样就可以通过Activity控制Service了
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //接收传递过来的更新消息
        updateInfo= (DownLoadMessage.DataBean) intent.getSerializableExtra("updateinfo");
        Log.i("jc","updateinfo"+updateInfo.getMd5());
        return super.onStartCommand(intent, flags, startId);
    }

    public class DownloadBinder extends Binder{
       private boolean startDownload=false;
        public void startDownLoad(String url){
            if (downLoadTask==null){
                downloadUrl=url;
                startDownload=true;
                downLoadTask=new DownLoadTask(listener);
                downLoadTask.execute(downloadUrl);
                startForeground(1,getNotification("Download...",0));
                ToastUtil.showToast(DownloadService.this,"开始下载...");
            }
        }
        public void pauseDownload(){
            if (downLoadTask!=null){
                downLoadTask.pauseDownload();
            }
        }
        public void cancelDownload() {
            if (downLoadTask != null) {
                downLoadTask.cancelDownload();
            } else {
                if (downloadUrl != null) {
                    //取消下载是需将文件删除，并将通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();

                }
            }

        }
       //提供一个方法
       //返回更新的进度
       public int getUpdeteProgress(){
           return mProgress;
       }
       public boolean isStartDownload(){
           return startDownload;
       }

    }
    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


    //显示通知栏（下载进度）
    private Notification getNotification(String title, int progress){
        Intent intent=new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
                builder.setContentIntent(pi);
                builder.setContentTitle(title);
        if (progress>0){
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        //设置通知栏的是否消失
        Notification notification=builder.build();
        //当下载进度到100时或者下载失败时，设置通知栏自动消失,否则通知栏不自动消失
        if (progress==-1) {
            Log.i("jc","进入了notification"+progress);
            notification.flags = Notification.FLAG_AUTO_CANCEL;
        }else {

        }
        return notification;

    }





}
