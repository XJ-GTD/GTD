package com.manager.master.service;

import com.manager.master.bean.UserAccountBean;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */
public interface IUserService {

    /**
     * 查询用户账户信息
     * @return
     */
    public UserAccountBean findUser();
}
