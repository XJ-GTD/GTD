package com.manager.master.dao;

import com.manager.master.bean.UserAccountBean;
import com.manager.master.bean.UserInfoBean;
import com.manager.master.dto.ScheduleOutDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * create by zy on 2018/05/04.
 * 用户管理
 */
@Mapper
public interface ScheduleDao {

    /**
     * 查询个人日程信息
     * @return
     */
    @Select("select * from GTD_SCHEDULE where SCHEDULE_EXECUTOR = #{scheduleExecutor}")
    ScheduleOutDao findSchedule(@Param("scheduleExecutor") String scheduleExecutor);
    /**
     * 查询用户信息
     * @return
     */
    @Select("SELECT * FROM GTD_ACCOUNT t WHERE t.ACCOUNT_MOBILE = #{mobile} AND t.ACCOUNT_PASSWORD = #{password}")
    ScheduleOutDao loginUser(@Param("mobile") String mobile, @Param("password") String password);

}
