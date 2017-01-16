package com.oceansoft.osga.moudle.home.bean;

import java.util.List;

/**
 * Created by TempCw on 2017/1/13.
 */

public class NewsBean {

    /**
     * succ : true
     * statusCode : 200
     * msg : null
     * data : [{"id":5247,"channelId":0,"title":"我省公安机关共破假币犯罪案件56起 缴获假人民币568万元","author":"","pubDept":"0","imgUrl":"http://gaapi.jl.gov.cn:80/econsole/upload/news/954e71decaf545e50b1342c695a74968.jpg","summary":"","content":"","pubTime":null,"createTime":1484317917275,"orginalUrl":"","imgNews":1,"topNews":0,"pubStr":""},{"id":5053,"channelId":0,"title":"去年吉林省110报警服务台接警482万余起","author":"","pubDept":"0","imgUrl":"http://gaapi.jl.gov.cn:80/econsole/upload/news/78cd3135bd36718e169e689dacb0e005.jpg","summary":"","content":"","pubTime":null,"createTime":1484317917275,"orginalUrl":"","imgNews":1,"topNews":0,"pubStr":""},{"id":5207,"channelId":0,"title":"吉林市交警脱大衣为车祸伤者保暖","author":"","pubDept":"0","imgUrl":"http://gaapi.jl.gov.cn:80/econsole/upload/news/cb939e0b2b405adb5f64e767a1fa92e2.jpg","summary":"","content":"","pubTime":null,"createTime":1484317917275,"orginalUrl":"","imgNews":1,"topNews":0,"pubStr":""}]
     * time : 1484317917276
     * desc : null
     * hasNext : false
     */

    private boolean succ;
    private int statusCode;
    private Object msg;
    private long time;
    private Object desc;
    private boolean hasNext;
    /**
     * id : 5247
     * channelId : 0
     * title : 我省公安机关共破假币犯罪案件56起 缴获假人民币568万元
     * author :
     * pubDept : 0
     * imgUrl : http://gaapi.jl.gov.cn:80/econsole/upload/news/954e71decaf545e50b1342c695a74968.jpg
     * summary :
     * content :
     * pubTime : null
     * createTime : 1484317917275
     * orginalUrl :
     * imgNews : 1
     * topNews : 0
     * pubStr :
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private int channelId;
        private String title;
        private String author;
        private String pubDept;
        private String imgUrl;
        private String summary;
        private String content;
        private Object pubTime;
        private long createTime;
        private String orginalUrl;
        private int imgNews;
        private int topNews;
        private String pubStr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPubDept() {
            return pubDept;
        }

        public void setPubDept(String pubDept) {
            this.pubDept = pubDept;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getPubTime() {
            return pubTime;
        }

        public void setPubTime(Object pubTime) {
            this.pubTime = pubTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getOrginalUrl() {
            return orginalUrl;
        }

        public void setOrginalUrl(String orginalUrl) {
            this.orginalUrl = orginalUrl;
        }

        public int getImgNews() {
            return imgNews;
        }

        public void setImgNews(int imgNews) {
            this.imgNews = imgNews;
        }

        public int getTopNews() {
            return topNews;
        }

        public void setTopNews(int topNews) {
            this.topNews = topNews;
        }

        public String getPubStr() {
            return pubStr;
        }

        public void setPubStr(String pubStr) {
            this.pubStr = pubStr;
        }
    }
}
