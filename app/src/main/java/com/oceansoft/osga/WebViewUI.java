package com.oceansoft.osga;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.oceansoft.osga.config.SharePreferenceManager;
import com.oceansoft.osga.moudle.base.view.views.AbsBaseActivity;
import com.oceansoft.osga.mvp.view.impl.AbsMvpActivity;
import com.oceansoft.osga.mvp.view.impl.AbsMvpMapActivity;
import com.oceansoft.osga.utils.DialogUtil;
import com.oceansoft.osga.utils.NetWorkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * WebView加载页面
 *
 * @author: dlm
 * @time: 14-7-3 上午10:16
 */
public class WebViewUI extends AbsMvpMapActivity {

    private static final int TIME_OUT_FLAG = 1;
    public static final int POST = 0x101;
    public static final int GET = 0x102;

    private WebView webView;
    private String title, url;
    private Boolean shouldLoadDialog = false;
    private Timer timer;
    private int time_out = 1500;
    private int urlType;
    private String PostParams = "";
    private ValueCallback<Uri> mUploadMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        time_out = getIntent().getIntExtra("timeout", 1500);
        shouldLoadDialog = getIntent().getBooleanExtra("load", false);
        urlType = getIntent().getIntExtra("type", GET);
        PostParams = getIntent().getStringExtra("postParams");
        setContentView(R.layout.acitivty_newsdetail_web);
        //FIXME:待解决问题：点击深层的webView则报错(解决办法：用Fragment(未测试))
        bindNavagation(R.id.lt,R.string.matter,WebViewUI.this);


        setupView();
    }

    @Override
    public void bindPresenter() {

    }

    private void setupView() {
        webView = (WebView) findViewById(R.id.news_detail);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
                new AlertDialog.Builder(WebViewUI.this)
                        .setTitle("javaScript dialog")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();

                return true;
            }

            ;
        });
        webView.setWebChromeClient(new WebChromeClient() {

            //            // For Android 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                // For Android 3.0
                mUploadMessage = uploadMsg;
               // startActivityForResult(new Intent(WebViewUI.this, WebViewFileChooserUI.class), 1);
            }

            // For Android 3.0+
            public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                openFileChooser(uploadMsg);
            }

            //For Android 4.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooser(uploadMsg);
            }

        });
        if (!title.equals("理赔点查询")) {
            webView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    if (shouldLoadDialog)
                        DialogUtil.showLoadDialog(WebViewUI.this);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if (shouldLoadDialog)
                        DialogUtil.closeLoadDialog();
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    if (null != description && description.contains("ERR_ADDRESS_UNREACHABLE")) {
                        webView.loadUrl("file:///android_asset/view/network_error.html");
                    } else {
                        webView.loadUrl("file:///android_asset/view/404.html");
                    }
                }

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();//接受证书
                }
            });
        }
        webView.addJavascriptInterface(new JavaScriptClass(), "webdemo");
        if (!TextUtils.isEmpty(url)) {
            if (urlType == GET)
                webView.loadUrl(url);
            if (urlType == POST && !TextUtils.isEmpty(PostParams))
                webView.postUrl(url, PostParams.getBytes());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (!TextUtils.isEmpty(title))
          //  StatService.onPageStart(this, title);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (!TextUtils.isEmpty(title))
          //  StatService.onPageEnd(this, title);
    }

    private void goBack() {
        if (webView.getUrl() == null || webView.getUrl().contains("404.html") || webView.getUrl().contains("network_error")) {
            finish();
            return;
        }
        if (webView.canGoBack() && NetWorkUtil.isNetWorkAvailable(WebViewUI.this)) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri result = data.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        } else
            mUploadMessage.onReceiveValue(null);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            goBack();
        }
        return false;
    }

    class JavaScriptClass {
        private Map<String, String> params = new HashMap<String, String>();

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(WebViewUI.this, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public String getparam(String key) {
            return params.get(key);
        }

        @JavascriptInterface
        public void setParams(String key, String value) {
            params.put(key, value);
        }

        @JavascriptInterface
        public String getLoginUid() {
            return SharePreferenceManager.getSharp().getGuid();
        }

        @JavascriptInterface
        public void log(String msg) {
            Log.e("weblog", msg);
        }

//        @JavascriptInterface
//        public String getUserInfo() {
//            JSONObject authData = new JSONObject();
//            JSONObject jsonObject = new JSONObject();
//            try {
//                if (SharePrefManager.isLogin()) {
//                    authData.put("isLogin", true);
//                    AppUser account = AccountModule.getModule().getAccount();
//                    jsonObject.put("uid", account.getId());
//                    jsonObject.put("loginId", account.getLoginId());
//                    jsonObject.put("name", account.getRealName());
//                    jsonObject.put("type", account.getType());
//                    jsonObject.put("idCard", account.getIdCard());
//                    jsonObject.put("mobile", account.getMobile());
//                    authData.put("account", jsonObject);
//                } else {
//                    authData.put("isLogin", false);
//                    authData.put("account", "");
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return authData.toString();
//        }
    }
}
