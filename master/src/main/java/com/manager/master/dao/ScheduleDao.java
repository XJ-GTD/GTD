package com.manager.master.dao;

import com.manager.master.dto.ScheduleOutDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * create by zy on 2018/05/04.
 * 日程管理
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
    List<ScheduleOutDto> findSchedule(@Param("scheduleExecutor") int scheduleExecutor);
    /**
     * 添加个人日程
     * @return
     */
    @Select("INSERT INTO gtd.gtd_schedule (" +
            "`SCHEDULE_NAME`, `SCHEDULE_DETIAL`, `SCHEDULE_ISSUER`,`SCHEDULE_CREATE_DATE`,`SCHEDULE_START_DATE`,`SCHEDULE_FINISH_DATE`,`SCHEDULE_END_DATE`,`SCHEDULE_STATE`, " +
            "`GROUP_ID`,`SCHEDULE_MAP`, `SCHEDULE_REMIND_DATE`,`SCHEDULE_REMIND_REPEAT`,`SCHEDULE_REMIND_REPEAT_TYPE`) " +
            "VALUES (" +
            " #{scheduleName}, #{scheduleDetial}, #{scheduleIssuer},#{scheduleCreateDate}, #{scheduleStartDate},#{scheduleFinshDate},#{scheduledEndDate},#{scheduledState}," +
            "#{groupId},#{scheduledMap},#{scheduledRenindDate},#{scheduledRenindRepeat},#{scheduledRenindRepeatType})")
    ScheduleOutDto createSchedule(@Param("scheduleName") String scheduleNamwe,@Param("scheduleDetial") String scheduleDetial,@Param("scheduleIssuer") int scheduleIssuer,
                                  @Param("scheduleCreateDate") Date scheduleCreateDate,@Param("scheduleStartDate")  Date scheduleStartDate, @Param("scheduleFinshDate")  Date scheduleFinshDate,
                                  @Param("scheduledEndDate")   Date scheduledEndDate,@Param("scheduledState")  String scheduledState,@Param("groupId")  String  groupId,
                                  @Param("scheduledMap")  String scheduledMap,@Param("scheduledRenindDate")  String scheduledRenindDate,@Param("scheduledRenindRepeat")  String scheduledRenindRepeat,
                                  @Param("scheduledRenindRepeatType")  String  scheduledRenindRepeatType);


    /**
     *获取上次添加日程id
     */
    @Select("SELECT LAST_INSERT_ID()")
    int  selectScheduleId();


    /**
     * 添加执行事件表
     * @return
     */
    @Insert("INSERT INTO gtd.GTD_EXECUTOR_SCHEDULE (" +
            "`USER_ID`, `SCHEDULE_ID`, `EXECUTOR_FINISH_DATE`," +
            "`EXECUTOR_STATE`,`EXECUTOR_REMIND_DATE`,`EXECUTOR_REMIND_REPEAT`," +
            "`EXECUTOR_REMIND_REPEAT_TYPE`) " +
            "VALUES (" +
            " #{userid}, #{scheduledId}, #{executorFinshDate}," +
            "#{scheduledState}, #{executorRenindDate},#{executorRenindRepeat}," +
            "#{executorRenindRepeatType})")


    void createExecutorScheduleId(@Param("userid") int userid,@Param("scheduledId") int  scheduledId,@Param("executorFinshDate") Date executorFinshDate,
                                  @Param("scheduledState") String scheduledState,@Param("executorRenindDate") Date executorRenindDate,@Param("executorRenindRepeat") String executorRenindRepeat,
                                  @Param("executorRenindRepeatType") String executorRenindRepeatType);

}
