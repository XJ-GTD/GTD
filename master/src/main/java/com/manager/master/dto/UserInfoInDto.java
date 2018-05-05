package com.manager.master.dto;

/**
 * create by wzy on 201/05/03
 * 用户登陆返回信息类
 */
public class UserInfoInDto {

    private String accountName;     //登录账号
    private String password;        //登录密码
    private String mobile;          //登录手机

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
