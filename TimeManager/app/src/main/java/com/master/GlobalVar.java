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
    private static String requestURL = "http://192.168.99.31:8080/gtd";

    /* ==== Controller ==== */
    private static String USER_URL = requestURL + "/user";           //用户类
    private static String GROUP_URL = requestURL + "/group";        //群组类
    private static String SCHEDULE_URL = requestURL + "/schedule";   //日程类

    /* ==== Connect ==== */
    private static String USER_LOGIN_URL = USER_URL + "/login";
    private static String GROUP_FIND_URL = GROUP_URL + "/find";
    private static String GROUP_ADD_URL = GROUP_URL + "/";

    private static String SCHEDULE_ADD_URL = SCHEDULE_URL + "/create";
    private static String SCHEDULE_FIND_URL = SCHEDULE_URL + "/find";
    private static String SCHEDULE_SINGLE_FIND_URL = SCHEDULE_URL + "/findScheduleByOne";
    private static String SCHEDULE_GROUP_URL = SCHEDULE_URL + "/findSchByGroup";

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
}
