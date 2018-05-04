package com.manager.master.dao;

import com.manager.master.bean.UserAccountBean;
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
//    /**
//     * 查询用户信息
//     * @return
//     */
//    @Select("select * from GTD_USER where USER_ID = ( select USER_ID from GTD_ACCOUNT where ACCOUNT_MOBILE = #{mobile} and ACCOUNT_PASSWORD= #{passWord} )")
//    UserInfoBean loginUser(@Param("mobile") String mobile,@Param("passWord") String passWord);
    /**
     * 查询用户信息
     * @return
     */
    @Select("SELECT * FROM GTD_ACCOUNT t WHERE t.ACCOUNT_MOBILE = #{mobile} AND t.ACCOUNT_PASSWORD = #{password}")
    UserAccountBean loginUser(@Param("mobile") String mobile, @Param("password")String password);

}
