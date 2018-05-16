package com.master;

import android.app.Application;

/**
 * create by wzy on 2018/05/10
 * 全局变量
 */
public class GlobalVar extends Application {

    private static GlobalVar instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /* ==== 请求地址 ==== */
    private static String requestURL = "http://192.168.99.39:8080/gtd";

    /* ==== Controller ==== */
    private static String USER_URL = requestURL + "/user";           //用户类
    private static String GROUP_URL = requestURL + "/group";        //群组类
    private static String SCHEDULE_URL = requestURL + "/schedule";   //日程类

    /* ==== Connect ==== */
    private static String USER_LOGIN_URL = USER_URL + "/login";     //登陆
    private static String USER_SIGNIN_URL = USER_URL + "/signin";   //注册

    private static String GROUP_FIND_URL = GROUP_URL + "/find";     //全部群组查询
    private static String GROUP_ADD_URL = GROUP_URL + "/";

    private static String SCHEDULE_ADD_URL = SCHEDULE_URL + "/create";      //添加日程
    private static String SCHEDULE_FIND_URL = SCHEDULE_URL + "/find";       //查询日程列表
    private static String SCHEDULE_SINGLE_FIND_URL = SCHEDULE_URL + "/findScheduleByOne";       //查询单个日程
    private static String SCHEDULE_GROUP_URL = SCHEDULE_URL + "/findSchByGroup";        //查询群组全部日程
    private static String SCHEDULE_MINE_GROUP_URL = SCHEDULE_URL + "/findSchAndExcu";       //查询群组内日程是否自己执行

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public static String USER_LOGIN_URL() {
        return USER_LOGIN_URL;
    }

    public static void setUserLoginUrl(String userLoginUrl) {
        USER_LOGIN_URL = userLoginUrl;
    }

    public static String GROUP_FIND_URL() {
        return GROUP_FIND_URL;
    }

    public static void setGroupFindUrl(String groupFindUrl) {
        GROUP_FIND_URL = groupFindUrl;
    }

    public static String GROUP_ADD_URL() {
        return GROUP_ADD_URL;
    }

    public static void setGroupAddUrl(String groupAddUrl) {
        GROUP_ADD_URL = groupAddUrl;
    }

    public static GlobalVar getInstance() {
        return instance;
    }

    public static String SCHEDULE_ADD_URL() {
        return SCHEDULE_ADD_URL;
    }

    public static void setScheduleAddUrl(String scheduleAddUrl) {
        SCHEDULE_ADD_URL = scheduleAddUrl;
    }

    public static String SCHEDULE_FIND_URL() {
        return SCHEDULE_FIND_URL;
    }

    public static void setScheduleFindUrl(String scheduleFindUrl) {
        SCHEDULE_FIND_URL = scheduleFindUrl;
    }

    public static String SCHEDULE_SINGLE_FIND_URL() {
        return SCHEDULE_SINGLE_FIND_URL;
    }

    public static void setScheduleSingleFindUrl(String scheduleSingleFindUrl) {
        SCHEDULE_SINGLE_FIND_URL = scheduleSingleFindUrl;
    }

    public static String SCHEDULE_GROUP_URL() {
        return SCHEDULE_GROUP_URL;
    }

    public static void setScheduleGroupUrl(String scheduleGroupUrl) {
        SCHEDULE_GROUP_URL = scheduleGroupUrl;
    }

    public static String USER_SIGNIN_URL() {
        return USER_SIGNIN_URL;
    }

    public static void setUserSigninUrl(String userSigninUrl) {
        USER_SIGNIN_URL = userSigninUrl;
    }

    public static String SCHEDULE_MINE_GROUP_URL() {
        return SCHEDULE_MINE_GROUP_URL;
    }

    public static void setScheduleMineGroupUrl(String scheduleMineGroupUrl) {
        SCHEDULE_MINE_GROUP_URL = scheduleMineGroupUrl;
    }
}
