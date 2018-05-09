package com.manager.master.service;

import com.manager.master.bean.UserAccountBean;
import com.manager.master.bean.UserInfoBean;
import com.manager.master.dto.BaseOutDto;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */
public interface IUserService {

    /**
     * 查询用户信息
     * @return
     */
     UserInfoBean findUser(String mobile);
    /**
     * 用户登录
     * @param mobile  登录账号
     * @param passWord    登录密码
     */
    UserAccountBean loginUser(String mobile, String passWord);

    /**
     * 根据用户ID查询用户号码
     * @param userId
     * @return
     */
    String findMobileById(int userId);
}
