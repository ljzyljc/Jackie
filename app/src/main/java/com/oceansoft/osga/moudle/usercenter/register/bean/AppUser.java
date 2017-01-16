package com.oceansoft.osga.moudle.usercenter.register.bean;

import java.util.Date;

/**
 * 客户端用户实体类
 *
 */
public class AppUser {
    //判断用户人脸识别成功
    private boolean isByFace;
    //咨询时间（一天只能咨询一次）
    private long consultTime;

    public long getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(long consultTime) {
        this.consultTime = consultTime;
    }

    public boolean isByFace() {
        return isByFace;
    }

    public void setByFace(boolean byFace) {
        isByFace = byFace;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    private int id;
    private Object openId;
    private String loginId;
    private String password;
    private String realName;
    private Object oauthToken;
    private String email;
    private int gender;
    private String address;
    private String mobile;
    private int status;
    private Date regTime;
    private Date birthday;
    private String tel;
    private String idCard;
    private int type;
    private String validateCode;
    private int sort;
    private String guid;
    private Date loginTime;
    private int userSrc;
    private String passport;
    private String uidTw;
    private String orgId;
    private String corporate;
    private String corporateIdCard;
    private String contacts;
    private String lpn;
    private String vin;
    private String drivingLic;
    private String fileId;
    private int unit;
    private boolean isConfirm;
    private int identifystatus;

    public void setId(int id) {
        this.id = id;
    }

    public void setOpenId(Object openId) {
        this.openId = openId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setOauthToken(Object oauthToken) {
        this.oauthToken = oauthToken;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public void setUserSrc(int userSrc) {
        this.userSrc = userSrc;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setUidTw(String uidTw) {
        this.uidTw = uidTw;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public void setCorporateIdCard(String corporateIdCard) {
        this.corporateIdCard = corporateIdCard;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public void setLpn(String lpn) {
        this.lpn = lpn;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setDrivingLic(String drivingLic) {
        this.drivingLic = drivingLic;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setIsConfirm(boolean isConfirm){
        this.isConfirm = isConfirm;
    }

    public int getId() {
        return id;
    }

    public Object getOpenId() {
        return openId;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public Object getOauthToken() {
        return oauthToken;
    }

    public String getEmail() {
        return email;
    }

    public int getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public int getStatus() {
        return status;
    }

    public Date getRegTime() {
        return regTime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getTel() {
        return tel;
    }

    public String getIdCard() {
        return idCard;
    }

    public int getType() {
        return type;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public int getSort() {
        return sort;
    }

    public String getGuid() {
        return guid;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public int getUserSrc() {
        return userSrc;
    }

    public String getPassport() {
        return passport;
    }

    public String getUidTw() {
        return uidTw;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getCorporate() {
        return corporate;
    }

    public String getCorporateIdCard() {
        return corporateIdCard;
    }

    public String getContacts() {
        return contacts;
    }

    public String getLpn() {
        return lpn;
    }

    public String getVin() {
        return vin;
    }

    public String getDrivingLic() {
        return drivingLic;
    }

    public String getFileId() {
        return fileId;
    }

    public int getUnit() {
        return unit;
    }

    public boolean isConfirm(){return isConfirm;}

    public int getIdentifystatus() {
        return identifystatus;
    }

    public void setIdentifystatus(int identifystatus) {
        this.identifystatus = identifystatus;
    }
}
