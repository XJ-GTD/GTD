package com.manager.master.dao;

import com.manager.master.bean.UserAccountBean;
import org.apache.ibatis.annotations.Select;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */
public interface IUserDao {

    @Select("select * from GTD_ACCOUNT")
    UserAccountBean findUser();
}
