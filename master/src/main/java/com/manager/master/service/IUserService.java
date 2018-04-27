package com.manager.master.service;

import com.manager.master.bean.UserInfoBean;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */
public interface IUserService {

    /**
     * 查询用户信息
     * @return
     */
    public UserInfoBean findUser(String mobile);
}
