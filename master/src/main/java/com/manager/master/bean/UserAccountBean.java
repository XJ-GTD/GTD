package com.manager.master.bean;

/**
 * create by wzy on 2018/04/24.
 * 用户账户类
 */
public class UserAccountBean {

    private int ACCOUNT_ID;             //账户ID
    private String ACCOUNT_NAME;        //账户名
    private String ACCOUNT_PASSWORD;    //账户密码
    private String ACCOUNT_MOBILE;      //手机号
    private int USER_ID;                //用户ID

    public int getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(int ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public String getACCOUNT_PASSWORD() {
        return ACCOUNT_PASSWORD;
    }

    public void setACCOUNT_PASSWORD(String ACCOUNT_PASSWORD) {
        this.ACCOUNT_PASSWORD = ACCOUNT_PASSWORD;
    }

    public String getACCOUNT_MOBILE() {
        return ACCOUNT_MOBILE;
    }

    public void setACCOUNT_MOBILE(String ACCOUNT_MOBILE) {
        this.ACCOUNT_MOBILE = ACCOUNT_MOBILE;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }
}
