package com.manager.master.bean;

/**
 * create by wzy on 2018/04/24.
 * 用户信息类
 */
public class UserInfoBean {

    private int USER_ID;	        //用户ID
    private int ACCOUNT_ID;     //账户ID
    private String NICKNAME;	    //昵称
    private String EMAIL;         //邮箱
    private String USER_NAME; 	//姓名
    private String ID_NUMBER; 	//身份证号
    private String USER_HEAD; 	//头像URL

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(int ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getID_NUMBER() {
        return ID_NUMBER;
    }

    public void setID_NUMBER(String ID_NUMBER) {
        this.ID_NUMBER = ID_NUMBER;
    }

    public String getUSER_HEAD() {
        return USER_HEAD;
    }

    public void setUSER_HEAD(String USER_HEAD) {
        this.USER_HEAD = USER_HEAD;
    }
}
