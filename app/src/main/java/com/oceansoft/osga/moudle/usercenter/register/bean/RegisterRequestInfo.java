package com.oceansoft.osga.moudle.usercenter.register.bean;

/**
 * Created by TempCw on 2017/1/12.
 */

public class RegisterRequestInfo {

    /**
     * mobile : 13182676063
     * password : 123456
     * pt : A
     * unit : 1
     * userSrc : 4
     * validateCode : 526555
     */

    private String mobile;
    private String password;
    private String pt;
    private String unit;
    private String userSrc;
    private String validateCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUserSrc() {
        return userSrc;
    }

    public void setUserSrc(String userSrc) {
        this.userSrc = userSrc;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
