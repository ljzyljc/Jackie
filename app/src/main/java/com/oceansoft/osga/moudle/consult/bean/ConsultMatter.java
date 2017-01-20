package com.oceansoft.osga.moudle.consult.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: dlm
 * @time: 14-6-19 下午1:31
 */
public class ConsultMatter {

    /**
     * succ : true
     * statusCode : 200
     * msg : null
     * data : {"totalPages":10483,"totals":104824,"list":[{"advisoryTop":"吉林省通化市 驾考 预约不上","isComment":"0","isReply":"1","isPublic":"0","commentStr":"待评价","advisoryNo":"P050221170115230001","evaluationContent":null,"IsNiMing":"0","FreeStr1":null,"QuestionId":null,"FreeStr2":null,"LocalIP":null,"CreateTime":"2017/01/15","context":"吉林省通化市 驾考 预约不上 手机号码是老婆的身份证 怎么改成自己的身份证啊急  急    急！！！！！！！！！谢谢","CreateUserID":"97e012c9-8e6a-48e5-9c48-ed25c3393bcb","Row":"1","Guid":"73d229e2-d9d5-4bbe-88fb-b1dc3c2412ff","IsChecked":null},{"advisoryTop":"车牌","isComment":"0","isReply":"1","isPublic":"0","commentStr":"待评价","advisoryNo":"A220063170113230058","evaluationContent":null,"IsNiMing":"1","FreeStr1":null,"QuestionId":null,"FreeStr2":null,"LocalIP":null,"CreateTime":"2017/01/13","context":"车牌没下来   临牌到期了    怎么办 \n","CreateUserID":"fd78165f-a69c-4d5f-b4c9-7b89a2782079","Row":"9","Guid":"72132c50-3b6d-47ec-b07a-2e1ac128587b","IsChecked":null},{"advisoryTop":"身份证","isComment":"1","isReply":"1","isPublic":"0","commentStr":"满意","advisoryNo":"W810093170113230057","evaluationContent":"满意","IsNiMing":"1","FreeStr1":null,"QuestionId":null,"FreeStr2":null,"LocalIP":null,"CreateTime":"2017/01/13","context":"我想问一下我1月4号办的身份证办的邮件大约那天能到","CreateUserID":"89b53e12-9887-4c5d-830b-0d35a1e23ce2","Row":"10","Guid":"d302001c-db13-4094-b99a-43b90910fee1","IsChecked":null}]}
     * time : 1484487656824
     * desc : null
     * hasNext : false
     */

    private boolean succ;
    private int statusCode;
    private Object msg;
    /**
     * totalPages : 10483
     * totals : 104824
     * list : [{"advisoryTop":"吉林省通化市 驾考 预约不上","isComment":"0","isReply":"1","isPublic":"0","commentStr":"待评价","advisoryNo":"P050221170115230001","evaluationContent":null,"IsNiMing":"0","FreeStr1":null,"QuestionId":null,"FreeStr2":null,"LocalIP":null,"CreateTime":"2017/01/15","context":"吉林省通化市 驾考 预约不上 手机号码是老婆的身份证 怎么改成自己的身份证啊急  急    急！！！！！！！！！谢谢","CreateUserID":"97e012c9-8e6a-48e5-9c48-ed25c3393bcb","Row":"1","Guid":"73d229e2-d9d5-4bbe-88fb-b1dc3c2412ff","IsChecked":null},{"advisoryTop":"车牌","isComment":"0","isReply":"1","isPublic":"0","commentStr":"待评价","advisoryNo":"A220063170113230058","evaluationContent":null,"IsNiMing":"1","FreeStr1":null,"QuestionId":null,"FreeStr2":null,"LocalIP":null,"CreateTime":"2017/01/13","context":"车牌没下来   临牌到期了    怎么办 \n","CreateUserID":"fd78165f-a69c-4d5f-b4c9-7b89a2782079","Row":"9","Guid":"72132c50-3b6d-47ec-b07a-2e1ac128587b","IsChecked":null},{"advisoryTop":"身份证","isComment":"1","isReply":"1","isPublic":"0","commentStr":"满意","advisoryNo":"W810093170113230057","evaluationContent":"满意","IsNiMing":"1","FreeStr1":null,"QuestionId":null,"FreeStr2":null,"LocalIP":null,"CreateTime":"2017/01/13","context":"我想问一下我1月4号办的身份证办的邮件大约那天能到","CreateUserID":"89b53e12-9887-4c5d-830b-0d35a1e23ce2","Row":"10","Guid":"d302001c-db13-4094-b99a-43b90910fee1","IsChecked":null}]
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
        private int totalPages;
        private int totals;
        /**
         * advisoryTop : 吉林省通化市 驾考 预约不上
         * isComment : 0
         * isReply : 1
         * isPublic : 0
         * commentStr : 待评价
         * advisoryNo : P050221170115230001
         * evaluationContent : null
         * IsNiMing : 0
         * FreeStr1 : null
         * QuestionId : null
         * FreeStr2 : null
         * LocalIP : null
         * CreateTime : 2017/01/15
         * context : 吉林省通化市 驾考 预约不上 手机号码是老婆的身份证 怎么改成自己的身份证啊急  急    急！！！！！！！！！谢谢
         * CreateUserID : 97e012c9-8e6a-48e5-9c48-ed25c3393bcb
         * Row : 1
         * Guid : 73d229e2-d9d5-4bbe-88fb-b1dc3c2412ff
         * IsChecked : null
         */

        private ArrayList<ListBean> list;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotals() {
            return totals;
        }

        public void setTotals(int totals) {
            this.totals = totals;
        }

        public ArrayList<ListBean> getList() {
            return list;
        }

        public void setList(ArrayList<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String advisoryTop;
            private String isComment;
            private String isReply;
            private String isPublic;
            private String commentStr;
            private String advisoryNo;
            private Object evaluationContent;
            private String IsNiMing;
            private Object FreeStr1;
            private Object QuestionId;
            private Object FreeStr2;
            private Object LocalIP;
            private String CreateTime;
            private String context;
            private String CreateUserID;
            private String Row;
            private String Guid;
            private Object IsChecked;

            public String getAdvisoryTop() {
                return advisoryTop;
            }

            public void setAdvisoryTop(String advisoryTop) {
                this.advisoryTop = advisoryTop;
            }

            public String getIsComment() {
                return isComment;
            }

            public void setIsComment(String isComment) {
                this.isComment = isComment;
            }

            public String getIsReply() {
                return isReply;
            }

            public void setIsReply(String isReply) {
                this.isReply = isReply;
            }

            public String getIsPublic() {
                return isPublic;
            }

            public void setIsPublic(String isPublic) {
                this.isPublic = isPublic;
            }

            public String getCommentStr() {
                return commentStr;
            }

            public void setCommentStr(String commentStr) {
                this.commentStr = commentStr;
            }

            public String getAdvisoryNo() {
                return advisoryNo;
            }

            public void setAdvisoryNo(String advisoryNo) {
                this.advisoryNo = advisoryNo;
            }

            public Object getEvaluationContent() {
                return evaluationContent;
            }

            public void setEvaluationContent(Object evaluationContent) {
                this.evaluationContent = evaluationContent;
            }

            public String getIsNiMing() {
                return IsNiMing;
            }

            public void setIsNiMing(String IsNiMing) {
                this.IsNiMing = IsNiMing;
            }

            public Object getFreeStr1() {
                return FreeStr1;
            }

            public void setFreeStr1(Object FreeStr1) {
                this.FreeStr1 = FreeStr1;
            }

            public Object getQuestionId() {
                return QuestionId;
            }

            public void setQuestionId(Object QuestionId) {
                this.QuestionId = QuestionId;
            }

            public Object getFreeStr2() {
                return FreeStr2;
            }

            public void setFreeStr2(Object FreeStr2) {
                this.FreeStr2 = FreeStr2;
            }

            public Object getLocalIP() {
                return LocalIP;
            }

            public void setLocalIP(Object LocalIP) {
                this.LocalIP = LocalIP;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getCreateUserID() {
                return CreateUserID;
            }

            public void setCreateUserID(String CreateUserID) {
                this.CreateUserID = CreateUserID;
            }

            public String getRow() {
                return Row;
            }

            public void setRow(String Row) {
                this.Row = Row;
            }

            public String getGuid() {
                return Guid;
            }

            public void setGuid(String Guid) {
                this.Guid = Guid;
            }

            public Object getIsChecked() {
                return IsChecked;
            }

            public void setIsChecked(Object IsChecked) {
                this.IsChecked = IsChecked;
            }
        }
    }
}
