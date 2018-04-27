package com.manager.master.service.serviceImpl;

import com.manager.master.bean.UserInfoBean;
import com.manager.master.dao.IUserDao;
import com.manager.master.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public UserInfoBean findUser(String mobile) {
        return userDao.findUser(mobile);
    }

}
