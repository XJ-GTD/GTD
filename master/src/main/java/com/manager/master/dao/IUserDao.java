package com.manager.master.dao;

import com.manager.master.bean.UserAccountBean;
import org.apache.ibatis.annotations.Select;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */
public interface IUserDao {

    /**
     * 查询用户账户信息
     * @return
     */
    @Select("select * from GTD_ACCOUNT")
    UserAccountBean findUser();
}
