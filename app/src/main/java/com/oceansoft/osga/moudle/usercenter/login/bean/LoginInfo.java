package com.oceansoft.osga.moudle.usercenter.login.bean;

/**
 * Created by TempCw on 2017/1/11.
 */

public class LoginInfo {

    /**
     * succ : true
     * statusCode : 200
     * msg : null
     * data : {"id":195865,"openId":null,"loginId":"17715369021","password":"","realName":"曹大为","oauthToken":null,"email":"1445@qq.com","gender":1,"address":"吉林省-吉林市-昌邑区-吉林省公安厅","mobile":"17715369021","status":1,"regTime":1459864811000,"birthday":1484104399288,"tel":"","idCard":"220322198602037890","type":0,"validateCode":null,"sort":0,"guid":"9659027b-1713-4f42-9c14-cc442646025a","loginTime":1484104399288,"userSrc":1,"passport":"","uidTw":"","orgId":"","corporate":"","corporateIdCard":"","contacts":"","lpn":"","vin":"","drivingLic":"","fileId":"","unit":1,"isPolice":0,"identifystatus":"1","certificate":null,"csny":null,"temp1":null}
     * time : 1484104399295
     * desc : null
     * hasNext : false
     */

    private boolean succ;
    private int statusCode;
    private Object msg;
    /**
     * id : 195865
     * openId : null
     * loginId : 17715369021
     * password :
     * realName : 曹大为
     * oauthToken : null
     * email : 1445@qq.com
     * gender : 1
     * address : 吉林省-吉林市-昌邑区-吉林省公安厅
     * mobile : 17715369021
     * status : 1
     * regTime : 1459864811000
     * birthday : 1484104399288
     * tel :
     * idCard : 220322198602037890
     * type : 0
     * validateCode : null
     * sort : 0
     * guid : 9659027b-1713-4f42-9c14-cc442646025a
     * loginTime : 1484104399288
     * userSrc : 1
     * passport :
     * uidTw :
     * orgId :
     * corporate :
     * corporateIdCard :
     * contacts :
     * lpn :
     * vin :
     * drivingLic :
     * fileId :
     * unit : 1
     * isPolice : 0
     * identifystatus : 1
     * certificate : null
     * csny : null
     * temp1 : null
     */

    private DataBean data;
    private long time;
    private Object desc;
    private boolean hasNext;

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public static class DataBean {
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
        private long regTime;
        private long birthday;
        private String tel;
        private String idCard;
        private int type;
        private Object validateCode;
        private int sort;
        private String guid;
        private long loginTime;
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
        private int isPolice;
        private String identifystatus;
        private Object certificate;
        private Object csny;
        private Object temp1;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
        }

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

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Object getOauthToken() {
            return oauthToken;
        }

        public void setOauthToken(Object oauthToken) {
            this.oauthToken = oauthToken;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getRegTime() {
            return regTime;
        }

        public void setRegTime(long regTime) {
            this.regTime = regTime;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getValidateCode() {
            return validateCode;
        }

        public void setValidateCode(Object validateCode) {
            this.validateCode = validateCode;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public long getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(long loginTime) {
            this.loginTime = loginTime;
        }

        public int getUserSrc() {
            return userSrc;
        }

        public void setUserSrc(int userSrc) {
            this.userSrc = userSrc;
        }

        public String getPassport() {
            return passport;
        }

        public void setPassport(String passport) {
            this.passport = passport;
        }

        public String getUidTw() {
            return uidTw;
        }

        public void setUidTw(String uidTw) {
            this.uidTw = uidTw;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getCorporate() {
            return corporate;
        }

        public void setCorporate(String corporate) {
            this.corporate = corporate;
        }

        public String getCorporateIdCard() {
            return corporateIdCard;
        }

        public void setCorporateIdCard(String corporateIdCard) {
            this.corporateIdCard = corporateIdCard;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getLpn() {
            return lpn;
        }

        public void setLpn(String lpn) {
            this.lpn = lpn;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getDrivingLic() {
            return drivingLic;
        }

        public void setDrivingLic(String drivingLic) {
            this.drivingLic = drivingLic;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public int getUnit() {
            return unit;
        }

        public void setUnit(int unit) {
            this.unit = unit;
        }

        public int getIsPolice() {
            return isPolice;
        }

        public void setIsPolice(int isPolice) {
            this.isPolice = isPolice;
        }

        public String getIdentifystatus() {
            return identifystatus;
        }

        public void setIdentifystatus(String identifystatus) {
            this.identifystatus = identifystatus;
        }

        public Object getCertificate() {
            return certificate;
        }

        public void setCertificate(Object certificate) {
            this.certificate = certificate;
        }

        public Object getCsny() {
            return csny;
        }

        public void setCsny(Object csny) {
            this.csny = csny;
        }

        public Object getTemp1() {
            return temp1;
        }

        public void setTemp1(Object temp1) {
            this.temp1 = temp1;
        }
    }
}
