package com.oceansoft.osga.moudle.download;

import java.io.Serializable;

/**
 * Created by TempCw on 2017/1/18.
 * 版本更新信息
 */

public class DownLoadMessage implements Serializable{
    /**
     * succ : true
     * statusCode : 200
     * msg : null
     * data : {"id":1241,"appId":1,"verName":"1.0.25","verCode":26,"pubTime":1482394788000,"appContent":null,"status":1,"url":"http://gaapi.jl.gov.cn:80/econsole/upload/app/ea46e738fddca12282db7c51f36448f1.apk","size":13436593,"md5":"ea46e738fddca12282db7c51f36448f1","remark":"刷脸登录修改"}
     * time : 1484742024454
     * desc : null
     * hasNext : false
     */

    private boolean succ;
    private int statusCode;
    private Object msg;
    /**
     * id : 1241
     * appId : 1
     * verName : 1.0.25
     * verCode : 26
     * pubTime : 1482394788000
     * appContent : null
     * status : 1
     * url : http://gaapi.jl.gov.cn:80/econsole/upload/app/ea46e738fddca12282db7c51f36448f1.apk
     * size : 13436593
     * md5 : ea46e738fddca12282db7c51f36448f1
     * remark : 刷脸登录修改
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

    public static class DataBean implements Serializable{
        private int id;
        private int appId;
        private String verName;
        private int verCode;
        private long pubTime;
        private Object appContent;
        private int status;
        private String url;
        private int size;
        private String md5;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
        }

        public String getVerName() {
            return verName;
        }

        public void setVerName(String verName) {
            this.verName = verName;
        }

        public int getVerCode() {
            return verCode;
        }

        public void setVerCode(int verCode) {
            this.verCode = verCode;
        }

        public long getPubTime() {
            return pubTime;
        }

        public void setPubTime(long pubTime) {
            this.pubTime = pubTime;
        }

        public Object getAppContent() {
            return appContent;
        }

        public void setAppContent(Object appContent) {
            this.appContent = appContent;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
