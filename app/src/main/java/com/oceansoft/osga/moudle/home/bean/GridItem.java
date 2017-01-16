package com.oceansoft.osga.moudle.home.bean;

import java.util.List;

/**
 * Created by TempCw on 2017/1/15.
 */

public class GridItem {

    /**
     * succ : true
     * statusCode : 200
     * msg : null
     * data : [{"id":527,"title":"保安员查询","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/7d02d5823fe85a84679a4d54262825ec.png","url":"","desc":"保安员查询","appType":"1","startPage":"https://gaapi.jl.gov.cn/econsole/weixin/bm/baycx","appSize":0},{"id":529,"title":"违章缴费","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/6c8fc0d95e5a769c8ef92db8613dc052.png","url":"","desc":"违章缴费","appType":"1","startPage":"https://gaapi.jl.gov.cn/econsole/weixin/wzjf/wzjfIndex","appSize":0},{"id":367,"title":"违法查询","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/52eab32d9cae2831896ab54115b181c8.png","url":"","desc":"机动车违法查询","appType":"1","startPage":"http://gaapi.jl.gov.cn/econsole/weixin/bm/wfcx/jdcwf","appSize":0},{"id":365,"title":"记分查询","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/21ee9cce1f5cdfc825d69461dfa2dd2f.png","url":"","desc":"驾驶证记分查询","appType":"1","startPage":"http://gaapi.jl.gov.cn/econsole/weixin/bm/wfcx/jszjf","appSize":0},{"id":323,"title":"办理进度查询","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/home_sfzbljdcx.png","url":"","desc":"身份证办理进度查询","appType":"1","startPage":"http://gaapi.jl.gov.cn/econsole/resource/wx/html/sfzbl.html","appSize":0},{"id":285,"title":"办件进度查询","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/6297e7a946722baee138fb7d16c9ad9d.png","url":"plugins/c72d198e7d579bd0c94b263f11651592.apk","desc":"出入境办件进度查询","appType":"1","startPage":"http://gaapi.jl.gov.cn/econsole/resource/wx/html/crjbj.html","appSize":0},{"id":283,"title":"办件查询","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/8584ae44742c466e6bd4e488ff475aca.png","url":"","desc":"服务平台办件查询","appType":"1","startPage":"http://gaapi.jl.gov.cn/econsole/resource/wx/html/bjcx.html","appSize":0},{"id":335,"title":"执法公开","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/8245f5da8a97edf6274d5113fbbdaa8f.png","url":"","desc":"执法公开信息查询","appType":"1","startPage":"http://221.8.52.232:8080/japdplat-case/f/index.html","appSize":0},{"id":337,"title":"路况信息","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/87f8161a9915b0c79fa7f276b8d0867b.png","url":"","desc":"路况信息","appType":"1","startPage":"http://gaapi.jl.gov.cn/econsole/weixin/bm/jtlkmap","appSize":0},{"id":333,"title":"高速路况","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/421f241ec453f9825875a4aedc280aab.png","url":"","desc":"高速路况播报","appType":"1","startPage":"http://gaapi.jl.gov.cn/econsole/api/highspeed/index?pageindex=1","appSize":0},{"id":369,"title":"失物招领","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/48d5e51abb1cbb461b3fc5340e5d9e1e.png","url":"","desc":"失物招领","appType":"1","startPage":"http://gaapi.jl.gov.cn/econsole/api/lost/index","appSize":0},{"id":281,"title":"重名查询","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/2a0a614ee301ab43c966d272e2356ef2.png","url":"","desc":"重名查询","appType":"1","startPage":"http://gaapi.jl.gov.cn/econsole/weixin/bm/cmcx","appSize":0},{"id":445,"title":"印章查询","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/724f289f0087ad8e47274ccfa1c79c8a.png","url":"","desc":"根据印章编号、密码查询印章是否存在","appType":"1","startPage":"https://gaapi.jl.gov.cn/econsole/weixin/seal/sealIndex","appSize":0},{"id":525,"title":"EMS查询","icon":"http://gaapi.jl.gov.cn:80/econsole/upload/icon/5f20d2eeaabe2ab707af6735089ab084.png","url":"","desc":"EMS查询","appType":"1","startPage":"https://gaapi.jl.gov.cn/econsole/weixin/ems/emsIndex","appSize":0}]
     * time : 1484450973500
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
     * id : 527
     * title : 保安员查询
     * icon : http://gaapi.jl.gov.cn:80/econsole/upload/icon/7d02d5823fe85a84679a4d54262825ec.png
     * url :
     * desc : 保安员查询
     * appType : 1
     * startPage : https://gaapi.jl.gov.cn/econsole/weixin/bm/baycx
     * appSize : 0
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
        private String title;
        private String icon;
        private String url;
        private String desc;
        private String appType;
        private String startPage;
        private int appSize;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getStartPage() {
            return startPage;
        }

        public void setStartPage(String startPage) {
            this.startPage = startPage;
        }

        public int getAppSize() {
            return appSize;
        }

        public void setAppSize(int appSize) {
            this.appSize = appSize;
        }
    }
}
