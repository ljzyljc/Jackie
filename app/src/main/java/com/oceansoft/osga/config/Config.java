package com.oceansoft.osga.config;

/**
 * Created by TempCw on 2017/1/11.
 */

public class Config {
    public static String HOST="https://gaapi.jl.gov.cn:443/";
    public static String HOSTHttp="http://gaapi.jl.gov.cn:80/";


    public static int serverVersionCode=1;  //服务端版本
    public static int localVersionCode=1;   //本地版本

    public static int getLocalVersionCode() {
        return localVersionCode;
    }

    public static void setLocalVersionCode(int localVersionCode) {
        Config.localVersionCode = localVersionCode;
    }

    public static int getServerVersionCode() {
        return serverVersionCode;
    }

    public static void setServerVersionCode(int serverVersionCode) {
        Config.serverVersionCode = serverVersionCode;
    }
}
