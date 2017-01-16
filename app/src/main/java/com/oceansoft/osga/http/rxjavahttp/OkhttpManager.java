package com.oceansoft.osga.http.rxjavahttp;



import com.oceansoft.osga.config.BaseApplication;
import com.oceansoft.osga.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by TempCw on 2017/1/11.
 */

public class OkhttpManager {

    private File httpCacheDirectory = new File(BaseApplication.getInstance().getCacheDir(), "httpcache");
    private int CacheSize=10*1024*1024;
    private Cache cache=new Cache(httpCacheDirectory,CacheSize);
    private class MyHostnameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            // TODO Auto-generated method stub
            return true;
        }
    }
    private  class TrustAllManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
    public OkHttpClient getOkhttpClient() {
        try {

//            // 设置 https支持
//            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//                @Override
//                public void checkClientTrusted(
//                        java.security.cert.X509Certificate[] chain,
//                        String authType) throws CertificateException {
//                }
//
//                @Override
//                public void checkServerTrusted(
//                        java.security.cert.X509Certificate[] chain,
//                        String authType) throws CertificateException {
//                }
//
//                @Override
//                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//            }};

            SSLContext sslContext=SSLContext.getInstance("SSL");
            sslContext.init(null,new TrustManager[]{new TrustAllManager()},new SecureRandom());
            javax.net.ssl.SSLSocketFactory sslSocketFactory=sslContext.getSocketFactory();



//                TrustManagerFactory trustManagerFactory=
//                        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//                trustManagerFactory.init(trustStore);

           OkHttpClient client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .cache(cache)
                    .sslSocketFactory(sslSocketFactory)
                   .hostnameVerifier(new MyHostnameVerifier())
                    .build();

            return client;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(BaseApplication.getInstance())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

}
