package com.oceansoft.osga.moudle.matters.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.oceansoft.osga.R;
import com.oceansoft.osga.WebViewUI;
import com.oceansoft.osga.config.SharePreferenceManager;
import com.oceansoft.osga.moudle.base.view.navigation.impl.DefaultNavigation;
import com.oceansoft.osga.moudle.base.view.views.AbsBaseMapFragment;
import com.oceansoft.osga.mvp.presenter.impl.AbsMvpPresenter;
import com.oceansoft.osga.mvp.view.IMvpView;


/**
 * Created by TempCw on 2017/1/10.
 */

public class MattersFragment extends AbsBaseMapFragment {
    private WebView webView;

    @Override
    public int bindLayoutId() {
        return R.layout.acitivty_newsdetail_web;
    }

    @Override
    public void initContentView(View contentView) {
        initNavigation(contentView);
        setupView(contentView);
        if(webView.getUrl()==null) {
            if (SharePreferenceManager.getSharp().getLoginStatus()) {
                String s = SharePreferenceManager.getSharp().getGuid();
                Log.i("jc","guid的值"+s);
                webView.loadUrl("https://gaapi.jl.gov.cn/econsole/api/v2/case/caseCenter?pt=A&userGuid=" + s);
            } else {
                webView.loadUrl("https://gaapi.jl.gov.cn/econsole/api/v2/case/caseCenter?pt=A");
            }

            webView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if(!url.contains("https://gaapi.jl.gov.cn/econsole/api/v2/case/caseCenter?pt=A")){
                        Intent intent=new Intent(getActivity(),WebViewUI.class);
                        intent.putExtra("url",url);
                        intent.putExtra("title","事项中心");
                        startActivity(intent);
                        return true;
                    }
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
    }

    @Override
    public void bindPresenter() {
    }



    private void setupView(View v) {
        webView = (WebView)v.findViewById(R.id.news_detail);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);

    }
    public void initNavigation(View contentView){
        DefaultNavigation.Builder builder=new DefaultNavigation.Builder(getContext(), (ViewGroup) contentView);
        builder.setCenterText(R.string.matter)
                .create();
    }
}
