package com.manager.master.dao;

import com.manager.master.bean.UserInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */
@Mapper
public interface IUserDao {

    /**
     * 查询用户信息
     * @return
     */
    @Select("select * from GTD_USER where USER_ID = ( select USER_ID from GTD_ACCOUNT where ACCOUNT_MOBILE = #{mobile})")
    UserInfoBean findUser(@Param("mobile") String mobile);

}
