package com.oceansoft.osga.moudle.download;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TempCw on 2017/1/18.
 */

public class DownLoadTask extends AsyncTask<String,Integer,Integer> {
    public static final int TYPE_SUCCESS = 0;  //下载成功
    public static final int TYPE_FAILED = 1;   //下载失败
    public static final int TYPE_PAUSED = 2;   //暂停下载
    public static final int TYPE_CANCELED = 3; //取消下载
    private boolean isCancaled=false;
    private boolean isPaused=false;
    private int lastProgress;
    private DownloadListener downloadListener;

    public DownLoadTask(DownloadListener listener){
        this.downloadListener=listener;
    }

    //下载进度
    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream=null;
        RandomAccessFile savaFile=null;
        File file=null;
        try {
            long downloadLength = 0;      //记录已下载的文件长度
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if (file.exists()) {
                downloadLength = file.length();
            }
            //获取要下载的长度
            long contentLength = getContentLength(downloadUrl);
            if (contentLength==0){                      //如果要下载的长度为0，说明下载失败
                return TYPE_FAILED;
            }else if (contentLength==downloadLength){      //已下载字节和文件总字节相等，说明已经下载完成了
                return TYPE_SUCCESS;
            }
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    //断点下载，指定从哪个字节开始下载
                    .addHeader("RANGE","bytes="+downloadLength+"-")
                    .url(downloadUrl)
                    .build();
            Response response=client.newCall(request).execute();
            if (response!=null){
                inputStream=response.body().byteStream();
                savaFile=new RandomAccessFile(file,"rw");
                savaFile.seek(downloadLength); //跳过已下载的字节
                byte[] b=new byte[1024];
                int total=0;
                int len=0;
                while ((len=inputStream.read(b))!=-1){
                    if (isCancaled){
                        return TYPE_CANCELED;
                    }else if(isPaused){
                        return TYPE_PAUSED;
                    }else {
                        total+=len;
                        savaFile.write(b,0,len);
                        //计算已下载的百分比
                        int progress=(int)((total+downloadLength)*100/contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (inputStream!=null){
                    inputStream.close();
                }
                if (savaFile!=null){
                    savaFile.close();
                }
                if (isCancaled&&file!=null){
                    file.delete();
                }

            }catch (Exception e){

            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                downloadListener.onFailed();
                break;
            case TYPE_PAUSED:
                downloadListener.onPause();
                break;
            case TYPE_CANCELED:
                downloadListener.onCancel();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress=values[0];
        if (progress>lastProgress){
            downloadListener.onProgress(progress);     //监听下载进度
            lastProgress=progress;
        }
    }

    //获取文件的总长度
    private long getContentLength(String downloadUrl) throws IOException{
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response=client.newCall(request).execute();
        if (response!=null&&response.isSuccessful()){
            //文件的总长度
            long contentLength=response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
    //暂停下载
    public void pauseDownload(){
        isPaused=true;
    }
    //取消下载
    public void cancelDownload(){
        isCancaled=true;
    }


}
