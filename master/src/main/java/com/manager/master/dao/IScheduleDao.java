package com.manager.master.dao;

import com.manager.master.dto.ScheduleOutDto;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * create by zy on 2018/05/04.
 * 日程管理
 */
@Mapper
public interface IScheduleDao {

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
            " #{scheduleName}, #{scheduleDetial}, #{scheduleIssuer},#{scheduleCreateDate}, #{scheduleStartDate},#{scheduleFinishDate},#{scheduledEndDate},#{scheduledState}," +
            "#{groupId},#{scheduledMap},#{scheduledRenindDate},#{scheduledRenindRepeat},#{scheduledRenindRepeatType})")
    ScheduleOutDto createSchedule(@Param("scheduleName") String scheduleNamwe,@Param("scheduleDetial") String scheduleDetial,@Param("scheduleIssuer") int scheduleIssuer,
                                  @Param("scheduleCreateDate") Date scheduleCreateDate,@Param("scheduleStartDate")  Date scheduleStartDate, @Param("scheduleFinishDate")  Date scheduleFinishDate,
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


    /**
     * 查询单条个人日程信息
     * @return
     */
    @Select("select * FROM gtd_schedule where SCHEDULE_ID = #{scheduledId}")
    ScheduleOutDto findScheduleByOne(@Param("scheduledId") int scheduledId);

    /**
     * 编辑个人日程
     *
     */
    @Update("UPDATE gtd.GTD_SCHEDULE SET " +
            "`SCHEDULE_ID`=#{scheduledId}, " +//事件ID
            "`SCHEDULE_NAME`=#{scheduleName}, " + //事件名
            "`SCHEDULE_DETIAL`=#{scheduleDetial} , " + //事件详情

            "`SCHEDULE_ISSUER`=#{scheduleIssuer} , " + //发布人
            "`SCHEDULE_CREATE_DATE`=#{scheduleCreateDate} , " + //创建时间
            "`SCHEDULE_START_DATE`=#{scheduleStartDate} , " + //开始时间

            "`SCHEDULE_EDIT_DATE`=#{scheduleEditDate} , " + //修改时间
            "`SCHEDULE_FINISH_DATE`=#{scheduleFinshDate} , " + //完成时间
            "`SCHEDULE_END_DATE`=#{scheduledEndDate} , " + //截止时间

            "`SCHEDULE_STATE`=#{scheduleIssuer} , " + //事件状态(-1 未完成 1完成)
            "`GROUP_ID`=#{scheduleIssuer} , " + //组群id
            "`SCHEDULE_MAP`=#{scheduledMap} , " + //位置

            "`SCHEDULE_REMIND_DATE`=#{scheduledRenindDate} , " + //提醒时间
            "`SCHEDULE_REMIND_REPEAT`=#{scheduledRenindRepeat} , " + //重复提醒
            "`SCHEDULE_REMIND_REPEAT_TYPE`=#{scheduledRenindRepeatType}  " + //重复提醒类型（1 每日 2 每月 3每年）
            "WHERE `SCHEDULE_ID`=#{scheduledId}")
    void updateSchedule(@Param("scheduledId") int scheduledId,@Param("scheduleName") String scheduleName,@Param("scheduleDetial") String scheduleDetial,
                        @Param("scheduleIssuer") int scheduleIssuer,@Param("scheduleCreateDate") Date scheduleCreateDate,@Param("scheduleStartDate") Date  scheduleStartDate,
                        @Param("scheduleEditDate") Date scheduleEditDate,@Param("scheduleFinshDate") Date scheduleFinshDate,@Param("scheduledEndDate") Date scheduledEndDate,
                        @Param("scheduledState") String scheduledState,@Param("GroupId") String GroupId,@Param("scheduledMap") String scheduledMap,
                        @Param("scheduledRenindDate") String scheduledRenindDate,@Param("scheduledRenindRepeat") String scheduledRenindRepeat,@Param("scheduledRenindRepeatType") String scheduledRenindRepeatType);

    //DELETE FROM `gtd`.`GTD_SCHEDULE` WHERE `SCHEDULE_ID`='1133';
    /**
     * 删除个人日程信息
     * @return
     */
    @Delete("DELETE FROM gtd.GTD_SCHEDULE WHERE `SCHEDULE_ID`=#{userId}")
    void deleteGroup(@Param("userId") int userId);

    /**
     * 查询一个群组下的所有日程
     * @return
     */
    @Select(" select  " +
            " GS.SCHEDULE_ID, " +
            " GS.SCHEDULE_NAME, " +
            " GS.SCHEDULE_DETIAL, " +
            " GS.SCHEDULE_ISSUER, " +
            " date_format(GS.SCHEDULE_CREATE_DATE,'%Y-%m-%d %T') SCHEDULE_CREATE_DATE, " +
            " GS.SCHEDULE_START_DATE, " +
            " GS.SCHEDULE_EDIT_DATE, " +
            " GS.SCHEDULE_FINISH_DATE, " +
            " GS.SCHEDULE_END_DATE, " +
            " GS.SCHEDULE_STATE, " +
            " GS.GROUP_ID, " +
            " GS.SCHEDULE_MAP, " +
            " GS.SCHEDULE_REMIND_DATE, " +
            " GS.SCHEDULE_REMIND_REPEAT, " +
            " GS.SCHEDULE_REMIND_REPEAT_TYPE " +
            " from gtd_schedule GS  " +
            " where GS.group_id = #{groupId} "  )

    List<ScheduleOutDto> findScheduleByGroup(@Param("groupId") String groupId);

    /**
     * 查询一个群组下的所有日程(关联查询执行人姓名)
     * @return
     */
    @Select(" select  " +
            " GS.SCHEDULE_ID, " +
            " GS.SCHEDULE_NAME, " +
            " GS.SCHEDULE_DETIAL, " +
            " GS.SCHEDULE_ISSUER, " +
            " date_format(GS.SCHEDULE_CREATE_DATE,'%Y-%m-%d %T') SCHEDULE_CREATE_DATE, " +
            " GS.SCHEDULE_START_DATE, " +
            " GS.SCHEDULE_EDIT_DATE, " +
            " GS.SCHEDULE_FINISH_DATE, " +
            " GS.SCHEDULE_END_DATE, " +
            " GS.SCHEDULE_STATE, " +
            " GS.GROUP_ID, " +
            " GS.SCHEDULE_MAP, " +
            " GS.SCHEDULE_REMIND_DATE, " +
            " GS.SCHEDULE_REMIND_REPEAT, " +
            " GS.SCHEDULE_REMIND_REPEAT_TYPE, " +
            " gu.user_name " +
            " from gtd_schedule GS  " +
            " LEFT JOIN GTD_EXECUTOR_SCHEDULE GES ON GS.SCHEDULE_ID = GES.SCHEDULE_ID " +
            " LEFT JOIN GTD_USER GU ON GU.USER_ID = GES.USER_ID " +
            " where group_id = #{groupId} "  )
    List<ScheduleOutDto> findScheduleAndUserName(@Param("groupId") String groupId);
    /**
     * 根据事件ID和执行人ID查询事件表和执行事件表。
     * @return
     */
    @Select(" select " +
            " GS.SCHEDULE_ID,GS.SCHEDULE_NAME,GS.SCHEDULE_DETAIL,GS.SCHEDULE_ISSUER," +
            " GS.SCHEDULE_CREATE_DATE,GS.SCHEDULE_START_DATE,GS.SCHEDULE_EDIT_DATE," +
            " GS.SCHEDULE_FINISH_DATE,GS.SCHEDULE_END_DATE,GS.SCHEDULE_STATE," +
            " GS.GROUP_ID,GS.SCHEDULE_MAP,GS.SCHEDULE_REMIND_DATE,GS.SCHEDULE_REMIND_REPEAT," +
            " GS.SCHEDULE_REMIND_REPEAT_TYPE,GES.USER_ID,GES.SCHEDULE_ID,GES.EXECUTOR_FINISH_DATE," +
            " GES.EXECUTOR_STATE,GES.EXECUTOR_REMIND_DATE,GES.EXECUTOR_REMIND_REPEAT,GES.EXECUTOR_REMIND_REPEAT_TYPE," +
            " GES.EXECUTOR_SCHEDULE_NUMBER" +
            " from gtd_schedule GS" +
            " left join GTD_EXECUTOR_SCHEDULE GES ON GS.SCHEDULE_ID = GES.SCHEDULE_ID" +
            " WHERE GS.SCHEDULE_ID = #{scheduleId}" +
            " AND GES.USER_ID = #{userId}" )
    List<ScheduleOutDto> findScheduleAndExeBySchIdAndUserId(@Param("scheduleId") int scheduleId,@Param("userId") int userId);



}
