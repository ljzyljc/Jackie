package com.oceansoft.osga.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TempCw on 2017/1/11.
 */

public class SharePreferenceManager {
    private static SharePreferenceManager sharePreferenceManager=null;

    private SharedPreferences account;
    private static SharedPreferences setting;



    public static final String loginId = "loginId";
    public static final String password = "password";
    public static final String realName = "realName";
    public static final String oauthToken = "oauthToken";
    public static final String email = "email";
    public static final String gender = "gender";
    public static final String address = "address";
    public static final String mobile = "mobile";
    public static final String idCard = "idCard";
    public static final String status="status";
    public static final String userSrc = "userSrc";
    public static final String time = "time";
    public static final String guid="guid";
    public static final String loginTime = "loginTime";
    public static final String SETTING_LOGIN_STATUS = "setting_login_status"; //登录状态


    public static SharePreferenceManager getSharp(){
        if (sharePreferenceManager==null){
            synchronized (SharePreferenceManager.class){
                if (sharePreferenceManager==null){
                    sharePreferenceManager=new SharePreferenceManager();
                }
            }
        }
        return sharePreferenceManager;
    }
    private SharePreferenceManager(){
        Context context=BaseApplication.getInstance();
        account=context.getSharedPreferences("account_shared",context.MODE_PRIVATE);
        setting=context.getSharedPreferences("system_setting",context.MODE_PRIVATE);

    }
    //====================setting===================================


    public static long getRefreshTime(int listViewId ) {
        return getSharp().setting.getLong(String.format("LIST_VIEW_%1$d_REFRESH_TIME", listViewId), -1);
    }

    public static void setRefreshTime(int listViewId) {
        getSharp().setting.edit().putLong(String.format("LIST_VIEW_%1$d_REFRESH_TIME", listViewId), System.currentTimeMillis()).commit();
    }

    //=====================account===================================
    public void setAddress(String s){
        account.edit().putString(address,s).commit();
    }

    public  String getAddress() {
        return address;
    }


    public  String getEmail() {
        return email;
    }

    public  String getGender() {
        return gender;
    }

    public  String getGuid() {
        return account.getString(guid,"");
    }
    public void setGuid(String s){
        account.edit().putString(guid,s).commit();
    }

    public  String getIdCard() {
        return idCard;
    }
    public void setLoginId(String s){
        account.edit().putString(loginId,s).commit();
    }

    public  String getLoginId() {
        return account.getString(loginId,"");
    }

    public  String getLoginTime() {
        return loginTime;
    }

    public  String getMobile() {
        return mobile;
    }

    public  String getOauthToken() {
        return oauthToken;
    }

    public  String getPassword() {
        return password;
    }

    public  String getRealName() {
        return realName;
    }

    public  SharePreferenceManager getSharePreferenceManager() {
        return sharePreferenceManager;
    }

    public  void setSharePreferenceManager(SharePreferenceManager sharePreferenceManager) {
        SharePreferenceManager.sharePreferenceManager = sharePreferenceManager;
    }

    public  String getStatus() {
        return status;
    }

    public  String getTime() {
        return time;
    }

    public  String getUserSrc() {
        return userSrc;
    }
    public boolean getLoginStatus(){
        return account.getBoolean(SETTING_LOGIN_STATUS,false);
    }
    public void setLoginStatus(boolean loginStatus){
        account.edit().putBoolean(SETTING_LOGIN_STATUS,loginStatus).commit();
    }



    //用户注销登录
    public void logout(){
        account.edit().clear().commit();

    }

}
