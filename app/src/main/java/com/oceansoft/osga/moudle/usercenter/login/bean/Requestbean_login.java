package com.oceansoft.osga.moudle.usercenter.login.bean;

/**
 * Created by TempCw on 2017/1/12.
 */

public class Requestbean_login {

    /**
     * loginId : 17715369021
     * password : password
     * pt : A
     * userSrc : 1
     */

    private String loginId;
    private String password;
    private String pt;
    private int userSrc;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public int getUserSrc() {
        return userSrc;
    }

    public void setUserSrc(int userSrc) {
        this.userSrc = userSrc;
    }
}
