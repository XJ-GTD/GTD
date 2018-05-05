package com.manager.master.service.serviceImpl;

import com.manager.master.bean.UserAccountBean;
import com.manager.master.bean.UserInfoBean;
import com.manager.master.dao.IUserDao;
import com.manager.master.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private Logger logger = LogManager.getLogger(this.getClass());
    @Resource
    private IUserDao userDao;

    @Override
    public UserInfoBean findUser(String mobile) {
        return userDao.findUser(mobile);
    }

    @Override
    public UserAccountBean loginUser(String mobile, String passWord) {

        UserAccountBean userInfo =  userDao.loginUser(mobile, passWord);
        //判断用户账号是否存在
        if(userInfo != null){
            return userInfo;
        }else{
            return null;
        }
    }


}
