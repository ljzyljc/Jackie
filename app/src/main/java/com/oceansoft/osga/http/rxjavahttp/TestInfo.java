package com.oceansoft.osga.http.rxjavahttp;

/**
 * Created by TempCw on 2017/1/12.
 */

public class TestInfo {

    /**
     * succ : false
     * statusCode : 200
     * msg : 当前已是最新版本
     * data : null
     * time : 1484182179846
     * desc : null
     * hasNext : false
     */

    private boolean succ;
    private int statusCode;
    private String msg;
    private Object data;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
}
