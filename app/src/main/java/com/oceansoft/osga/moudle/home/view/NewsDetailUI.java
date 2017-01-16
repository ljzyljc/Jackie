package com.oceansoft.osga.moudle.home.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.oceansoft.osga.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 新闻详细内容. User: chenw Date: 13-9-24 Time: 上午9:20
 */
@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
public class NewsDetailUI extends FragmentActivity {

    private WebView mWebView;
    private ProgressBar mProBar;
    private long id;
    private String url;
    private String murl;
    private String title = "", sumary = "";
    private String imageUrl = "";
    private String type = "0";

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getLongExtra("id", -1);
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imgUrl");
        title = getIntent().getStringExtra("title");
        sumary = getIntent().getStringExtra("summary");
        type = getIntent().getStringExtra("type");
        murl = "https://gaapi.jl.gov.cn:443/econsole/api/news/5247";
        setContentView(R.layout.acitivty_newsdetail_web);

        setupViews();
        setTitle(R.string.police_zixun);
    }

    private void setupViews() {
        Intent intent = getIntent();
        mWebView = (WebView) findViewById(R.id.news_detail);
        mWebView.getSettings().setJavaScriptEnabled(true);



        mWebView.setBackgroundColor(getResources().getColor(R.color.webview_theme_normal));

        // FIXME:此处html地址改成常量
        if (type.equals("1")) {
            mWebView.loadUrl("file:///android_asset/view/news-detail_v2.html");
        } else {
            mWebView.loadUrl("file:///android_asset/view/news-detail.html");
        }
        mWebView.addJavascriptInterface(new JavaScriptClass(NewsDetailUI.this), "pap");
        mWebView.setWebViewClient(new WebViewClient() {
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
//                mWebView.loadUrl("javascript:loadPage('https://gaapi.jl.gov.cn:443/econsole/api/news/5247','small','Y')");
//                mWebView.loadUrl("javascript:loadPage('" + murl +  "')");
                mWebView.loadUrl("javascript:loadPage('" + murl + "','" + textsize + "','" + loadImage + "')");
                      Log.i("jc","javascript:loadPage('" + murl + "','" + textsize + "','" + loadImage + "')");
            }
        });
    }

    private static final int MENUITEM_ID_SHARE = 0x001;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENUITEM_ID_SHARE, Menu.NONE, R.string.share).setTitle(R.string.share).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == MENUITEM_ID_SHARE) {
////            Intent intent = new Intent(NewsDetailUI.this, ShareNewsUI.class);
////            intent.putExtra("url", UrlUtil.http(Config.HOST, Config.PORT, String.format("econsole/api/viewNews/%1$s", id)));
////            intent.putExtra("imgUrl", imageUrl);
////            intent.putExtra("title", title);
////            intent.putExtra("summary", sumary);
////            startActivity(intent);
//            imageUrl = UrlUtil.http(Config.HOST, 8190, "econsole/resource/images/app_logo.png");
//            share.share(sumary, title, UrlUtil.http(Config.HOST, Config.PORT, String.format("econsole/api/viewNews/%1$s", id)), imageUrl, false);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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
            return murl;
        }

        @JavascriptInterface
        public void setTextSize(String textsize) {
            // small big
//            SharePrefManager.setTextSize(textsize);
        }

//        @JavascriptInterface
//        public void setWebviewTheme(String theme) {
//            // nomal night
//            if (theme.equals("night")) {
//                SharePrefManager.setWebviewTheme(getResources().getColor(R.color.webview_theme_normal));
//            } else {
//                SharePrefManager.setWebviewTheme(getResources().getColor(R.color.webview_theme_night));
//            }
//            mWebView.setBackgroundColor(SharePrefManager.getWebviewThemeColor());
//        }
    }
}
