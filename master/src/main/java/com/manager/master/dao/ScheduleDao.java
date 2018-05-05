package com.manager.master.dao;

import com.manager.master.bean.UserAccountBean;
import com.manager.master.bean.UserInfoBean;
import com.manager.master.dto.ScheduleOutDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    @Select("select gs.*,ges.* FROM gtd_schedule gs inner join gtd_executor_schedule ges \n" +
            "on gs.SCHEDULE_ID=ges.SCHEDULE_ID " +
            "where ges.USER_ID = #{scheduleExecutor}")
    List<ScheduleOutDao> findSchedule(@Param("scheduleExecutor") int scheduleExecutor);
    /**
     * 查询用户信息
     * @return
     */
    @Select("SELECT * FROM GTD_ACCOUNT t WHERE t.ACCOUNT_MOBILE = #{mobile} AND t.ACCOUNT_PASSWORD = #{password}")
    ScheduleOutDao loginUser(@Param("mobile") String mobile, @Param("password") String password);

}
