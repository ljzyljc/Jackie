package com.oceansoft.osga.config;

/**
 * 系统常量
 */
public class Constants {

    /**
     * 个人
     */
    public static final int ACCOUNT_PERSONAL = 1;
    /**
     * 企业
     */
    public static final int ACCOUNT_COMPANY = 2;
    /**
     * 民警
     */
    public static final int ACCOUNT_POLICE = 3;


    /**
     * 登录后自动下载未完成任务
     */
    public static final String AUTODOWNLOADUNCOMPLETED = "autodownloaduncompleted";

    /**
     * 请求状态-失败
     */
    public static final int REQUEST_FAILED = -1;

    /**
     * 请求状态-无网络，网络错误
     */
    public static final int REQUEST_NONETWORK = -2;

    /**
     * 请求状态-获取数据成功
     */
    public static final int REQUEST_SUCCESS = 0;

    /**
     * 请求状态-添加成功
     */
    public static final int REQUEST_ADD = 1;

    /**
     * 请求状态-删除成功
     */
    public static final int REQUEST_DELETE = 2;

    /**
     * 请求状态-更新成功
     */
    public static final int REQUEST_UPDATE = 3;

    /**
     * 搜索排序-正序
     */
    public static final int SORTORDER_AES = 1;

    /**
     * 搜索排序-倒序
     */
    public static final int SORTORDER_DES = 0;

    /**
     * 排序字段-收藏时间
     */
    public static final int SORTFIELD_FAVORITE_TIME = 1;

    /**
     * 排序字段-评论时间
     */
    public static final int SORTFIELD_COMMENT_TIME = 1;

    /**
     * 排序字段-文件类别
     */
    public static final int SORTFIELD_FAVORITE_TYPE = 2;

    /**
     * 排序字段-直播课/线下课开始时间
     */
    public static final int SORTFIELD_COURSE_STARTTIME = 2;

    /**
     * 排序字段-全部消息
     */
    public static final int SEARCHTYPE_MESSAGE_ALL = 2;

    /**
     * 排序字段-未读消息
     */
    public static final int SEARCHTYPE_MESSAGE_NOREAD = 0;

    /**
     * 排序字段-已读消息
     */
    public static final int SEARCHTYPE_MESSAGE_READED = 1;

    /**
     * 搜索类型-所有
     */
    public static final int SEARCHTYPE_ALL = 0;

    /**
     * 搜索类型-待参加
     */
    public static final int SEARCHTYPE_READY = 1;

    /**
     * 搜索类型-未开始
     */
    public static final int SEARCHTYPE_NOTSTARTED = 2;

    /**
     * 搜索类型-已结束
     */
    public static final int SEARCHTYPE_FINISHED = 3;

    /**
     * 消息状态-已读
     */
    public static final int MESSAGE_STATUS_READED = 1;

    /**
     * 消息状态-未读
     */
    public static final int MESSAGE_STATUS_UNREADED = 0;

    /**
     * 消息状态-全部
     */
    public static final int MESSAGE_STATUS_ALL = 2;

    /**
     * 自动更新开始
     */
    public static final int UPDATE_BEGIN = 10;

    /**
     * 图片下载成功码
     */
    public static final int IMAGE_CACHE_SUCCESS = 200;

    /**
     * 直播课/线下课状态-未开始
     */
    public static final int COURSE_STATUS_NOTSTARTED = 0;
    /**
     * 直播课/线下课状态-待参加
     */
    public static final int COURSE_STATUS_READY = 1;

    /**
     * 直播课/线下课状态-进行中
     */
    public static final int COURSE_STATUS_ONGOING = 2;

    /**
     * 直播课/线下课状态-已完成
     */
    public static final int COURSE_STATUS_FINISHED = 3;

    /**
     * 全部课时
     */
    public static final int FILETYPE_ALL = -100;

    /**
     * 文件格式-章节
     */
    public static final int FILETYPE_CHAPTER = 1;

    /**
     * 文件格式-视频
     */
    public static final int FILETYPE_VIDEO = 2;

    /**
     * 文件格式-富文本
     */
    public static final int FILETYPE_ARTICLE = 3;

    /**
     * 文件格式-PDF
     */
    public static final int FILETYPE_PDF = 4;

    /**
     * 文件格式-ZIP
     */
    public static final int FILETYPE_ZIP = 5;
    /**
     * 文件格式-测试
     */
    public static final int FILETYPE_EXAM = 6;
    /**
     * 文件格式-作业
     */
    public static final int FILETYPE_HOMEWORK = 7;

    /**
     * 文件格式-练习
     */
    public static final int FILETYPE_EXERCISE = 8;

    /**
     * 文件格式-SCORM
     */
    public static final int FILETYPE_SCORM = 9;

    /**
     * 文件格式-互动
     */
    public static final int FILETYPE_INTERACTIVE = 10;

    /**
     * 文件格式-三分屏
     */
    public static final int FILETYPE_SCREEN = 11;

    /**
     * 文件格式-外部链接
     */
    public static final int FILETYPE_LINK = 12;

    /**
     * 文件格式-调查问卷
     */
    public static final int FILETYPE_QUESTION = 13;

    /**
     * UI延迟刷新时间
     */
    public final static int UI_REFRESH_DELAY = 1000;

    /**
     * UI刷新标志
     */
    public final static int UI_REFRESH = 301;

    /**
     * 操作成功标志
     */
    public final static int OK = 0;

    /**
     * 版本升级
     */
    public static final String VERSION_UPGRADE = "version_upgrade";

    /**
     * 通过系统通知打开应用
     */
    public static final String LAUNCHAEGS = "launchargs";

    /**
     * 开启服务Id
     */
    public static final String START_SERVICE_ID = "service_id";
    public static final String LOGIN_USER_NAME = "login_user_name";
    public static final String LOGIIN_USER_PWD = "logiin_user_pwd";
    /**
     * 我的应用模块点击进入目标---第三方应用
     */
    public static final int APP_TYPE_THIRD_PARTY = 1;
    /**
     * 我的应用模块点击进入目标---本APP模块
     */
    public static final int APP_TYPE_PAP_MODULE = 2;

    /**
     * 错误提示消息--暂无数据
     */
    public static final String ERROR_MSG_NO_DATA = "暂无数据";
    public static final int SUCCESS = 1;
    public static final int FAILUE = 0;

    /**
     * 根据选项卡选中项获取新闻类型id
     *
     * @param position 选项卡选中位置
     * @return 对应新闻类型id字符串
     */
    public static String getNewsClassId(int position) {
        switch (position) {
            case 0:// 警务新闻
                return "58";
            case 1:// 政务新闻
                return "78";
            case 2:// 政策法规
                return "60";
        }
        return "59";
    }

    /**
     * 根据选项卡选中项获取新闻类型id
     *
     * @param position 选项卡选中位置
     * @return 对应新闻类型id字符串
     */
    public static String getPicNewsClassId(int position) {
        switch (position) {
            case 0:// 警务新闻
                return "58";
            case 1:// 政务新闻
                return "78";
            case 2:// 政策法规
                return "60";
        }
        return "59";
    }


}
