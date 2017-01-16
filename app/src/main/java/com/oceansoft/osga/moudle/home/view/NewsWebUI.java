package com.oceansoft.osga.moudle.home.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.oceansoft.osga.R;
import com.oceansoft.osga.WebViewUI;
import com.oceansoft.osga.mvp.view.impl.AbsMvpMapActivity;
import com.oceansoft.osga.utils.DialogUtil;

/**
 * Created by TempCw on 2017/1/14.
 */

public class NewsWebUI extends FragmentActivity {
    private WebView webView;
    private String loadUrl;
    private String title;
    private String type;
    private String summary;
    private Boolean shouldLoadDialog = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl=getIntent().getStringExtra("url");
        Log.i("jc",loadUrl);
        title=getIntent().getStringExtra("title");
        type=getIntent().getStringExtra("type");
        summary=getIntent().getStringExtra("summary");

        setContentView(R.layout.acitivty_newsdetail_web);
        setupView();
    }
    private void setupView() {
        webView = (WebView) findViewById(R.id.news_detail);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(getResources().getColor(R.color.webview_theme_normal));
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setDatabaseEnabled(true);
        if (type.equals("1")) {
            webView.loadUrl("file:///android_asset/view/news-detail_v2.html");
        } else {
            webView.loadUrl("file:///android_asset/view/news-detail.html");
            Log.i("jc","type=0");
        }
        webView.addJavascriptInterface(new JavaScriptClass(NewsWebUI.this), "pap");

//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
//                new AlertDialog.Builder(NewsWebUI.this)
//                        .setTitle("javaScript dialog")
//                        .setMessage(message)
//                        .setPositiveButton(android.R.string.ok,
//                                new AlertDialog.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        result.confirm();
//                                    }
//                                })
//                        .setCancelable(false)
//                        .create()
//                        .show();
//
//                return true;
//            }
//
//        });

            webView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }


                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    String loadImage="Y";
                    String textsize="small";
                    webView.loadUrl("javascript:loadPage('" + loadUrl + "','" + textsize + "','" + loadImage + "')");

                    Log.i("jc","javascript:loadPage('" + loadUrl + "','" + textsize + "','" + loadImage + "')");
                    }

//                @Override
//                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                    if (null != description && description.contains("ERR_ADDRESS_UNREACHABLE")) {
//                        webView.loadUrl("file:///android_asset/view/network_error.html");
//                    } else {
//                        webView.loadUrl("file:///android_asset/view/404.html");
//                    }
//
//                }

//                @Override
//                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                    handler.proceed();//接受证书
//                }
            });
        }
    class JavaScriptClass {
        Context mContext;

        JavaScriptClass(Context mContext) {
            this.mContext = mContext;
        }

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public String getUrl() {
            return loadUrl;
        }

        @JavascriptInterface
        public void setTextSize(String textsize) {
            // small big
        }


    }

}
