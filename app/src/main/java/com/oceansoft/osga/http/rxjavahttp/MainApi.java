package com.oceansoft.osga.http.rxjavahttp;


import com.oceansoft.osga.config.Config;
import com.oceansoft.osga.config.SharePreferenceManager;
import com.oceansoft.osga.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osga.moudle.download.DownLoadMessage;
import com.oceansoft.osga.moudle.home.bean.GridItem;
import com.oceansoft.osga.moudle.home.bean.NewsBean;
import com.oceansoft.osga.moudle.usercenter.login.bean.LoginInfo;
import com.oceansoft.osga.moudle.usercenter.register.bean.AppUser;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterAutoCodeInfo;
import com.oceansoft.osga.moudle.usercenter.register.bean.RegisterResultInfo;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by TempCw on 2017/1/11.
 */

public interface MainApi {

    public static final String version=String.valueOf(Config.getLocalVersionCode());

    //=====================主页==========================
    //viewpager
    @GET("econsole/api/news/top/3")
    Observable<NewsBean> getViewPagerInfo();
    //获取GirdView信息
    @GET("econsole/api/app/v2/15/1")
    Observable<GridItem> getGridViewInof();
    //检测APP版本   动态Url
    @GET
    Observable<DownLoadMessage>getVersionMessage(@Url String url);




    //====================咨询中心=========================
    //咨询中心内容
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("econsole/api/matters/page/consult")
    Observable<ConsultMatter> getMatterList(@Body RequestBody requestBody);




//    @FormUrlEncoded
//    @POST("https://gaapi.jl.gov.cn:443/econsole/api/profile/auth")
//    Observable<LoginInfo> login(@Field("loginId") String loginId,
//                                @Field("password") String pwd,
//                                @Field("userSrc") int num,
//                                @Field("pt") String pt);
    //测试
    @GET("http://app.cyga.gov.cn/econsole/api/query/police-catalogs/")
    Observable<DateInfo> getMessage();

    //获取版本
    @GET("https://gaapi.jl.gov.cn:443/econsole/api/version/1/26")
    Observable<TestInfo> getVersion();

    //json格式请求数据   登录
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("econsole/api/profile/auth")
    Observable<LoginInfo> login(@Body RequestBody body);
    //注册
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("econsole/api/profile/register")
    Observable<RegisterResultInfo> register(@Body RequestBody body);


    //注册获取验证码
    @FormUrlEncoded
    @POST("econsole/api/sms/send")
    Observable<RegisterAutoCodeInfo> getAuthCode(@Field("expire") String expire,
                                                 @Field("tel") String tel,
                                                 @Field("type") String type,
                                                 @Field("content")String content);




}
