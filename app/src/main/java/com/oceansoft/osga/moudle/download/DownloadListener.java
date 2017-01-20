package com.oceansoft.osga.moudle.download;

/**
 * Created by TempCw on 2017/1/18.
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPause();

    void onCancel();


}
