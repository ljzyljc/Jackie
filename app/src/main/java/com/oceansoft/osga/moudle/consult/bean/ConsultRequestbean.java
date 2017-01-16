package com.oceansoft.osga.moudle.consult.bean;

/**
 * Created by TempCw on 2017/1/15.
 */

public class ConsultRequestbean {
    /**
     * numPerPage : 10
     * pageNum : 1
     * keywords :
     * pt : A
     */

    private int numPerPage;
    private int pageNum;
    private String keywords;
    private String pt;

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }
}
