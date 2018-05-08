package com.manager.master.dto;

import java.util.Date;

/**
 * 日程
 * create  zy
 */
public class ScheduleInDto {
    private String scheduleName;         //事件名
    private String scheduleDetial;       //事件详情
    private int scheduleIssuer;       //发布人
    private int scheduleExecutor;       //执行人EXECUTOR
    private Date scheduleCreateDate;     //创建时间SCHEDULE_CREATE_DATE
    private Date  scheduleStartDate;     //开始时间SCHEDULE_START_DATE
    private Date  scheduleFinshDate;     //完成时间SCHEDULE_FINISH_DATE
    private Date scheduledEndDate;     //截止时间SCHEDULE_END_DATE
    private String scheduledState;     //事件状态SCHEDULE_STATE(-1 未完成 1完成)
    private String GroupId;          //组群idGROUP_ID
    private String scheduledMap;     //位置SCHEDULE_MAP
    private String scheduledRenindDate;     //提醒时间SCHEDULE_REMIND_DATE
    private String scheduledRenindRepeat;     //重复提醒SCHEDULE_REMIND_REPEAT
    private String scheduledRenindRepeatType;     //重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）


    //执行事件表(日程关联表)
    private int scheduledId;          //执行事件IDSCHEDULE_ID
    private String  userId;         //执行人电话（执行人id）
    private Date ExecutorFinshDate;     //完成时间-执行事件表
    private Date ExecutorRenindDate;    //提醒时间-执行事件表
    private String ExecutorRenindRepeat;     //重复提醒-执行事件表
    private String ExecutorRenindRepeatType;     //重复提醒类型-执行事件表（1 每日 2 每月 3每年）

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleDetial() {
        return scheduleDetial;
    }

    public void setScheduleDetial(String scheduleDetial) {
        this.scheduleDetial = scheduleDetial;
    }

    public int getScheduleIssuer() {
        return scheduleIssuer;
    }

    public void setScheduleIssuer(int scheduleIssuer) {
        this.scheduleIssuer = scheduleIssuer;
    }

    public int getScheduleExecutor() {
        return scheduleExecutor;
    }

    public void setScheduleExecutor(int scheduleExecutor) {
        this.scheduleExecutor = scheduleExecutor;
    }

    public Date getScheduleCreateDate() {
        return scheduleCreateDate;
    }

    public void setScheduleCreateDate(Date scheduleCreateDate) {
        this.scheduleCreateDate = scheduleCreateDate;
    }

    public Date getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(Date scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public Date getScheduleFinshDate() {
        return scheduleFinshDate;
    }

    public void setScheduleFinshDate(Date scheduleFinshDate) {
        this.scheduleFinshDate = scheduleFinshDate;
    }

    public Date getScheduledEndDate() {
        return scheduledEndDate;
    }

    public void setScheduledEndDate(Date scheduledEndDate) {
        this.scheduledEndDate = scheduledEndDate;
    }

    public String getScheduledState() {
        return scheduledState;
    }

    public void setScheduledState(String scheduledState) {
        this.scheduledState = scheduledState;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public String getScheduledMap() {
        return scheduledMap;
    }

    public void setScheduledMap(String scheduledMap) {
        this.scheduledMap = scheduledMap;
    }

    public String getScheduledRenindDate() {
        return scheduledRenindDate;
    }

    public void setScheduledRenindDate(String scheduledRenindDate) {
        this.scheduledRenindDate = scheduledRenindDate;
    }

    public String getScheduledRenindRepeat() {
        return scheduledRenindRepeat;
    }

    public void setScheduledRenindRepeat(String scheduledRenindRepeat) {
        this.scheduledRenindRepeat = scheduledRenindRepeat;
    }

    public String getScheduledRenindRepeatType() {
        return scheduledRenindRepeatType;
    }

    public void setScheduledRenindRepeatType(String scheduledRenindRepeatType) {
        this.scheduledRenindRepeatType = scheduledRenindRepeatType;
    }

    public int getScheduledId() {
        return scheduledId;
    }

    public void setScheduledId(int scheduledId) {
        this.scheduledId = scheduledId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getExecutorFinshDate() {
        return ExecutorFinshDate;
    }

    public void setExecutorFinshDate(Date executorFinshDate) {
        ExecutorFinshDate = executorFinshDate;
    }

    public Date getExecutorRenindDate() {
        return ExecutorRenindDate;
    }

    public void setExecutorRenindDate(Date executorRenindDate) {
        ExecutorRenindDate = executorRenindDate;
    }

    public String getExecutorRenindRepeat() {
        return ExecutorRenindRepeat;
    }

    public void setExecutorRenindRepeat(String executorRenindRepeat) {
        ExecutorRenindRepeat = executorRenindRepeat;
    }

    public String getExecutorRenindRepeatType() {
        return ExecutorRenindRepeatType;
    }

    public void setExecutorRenindRepeatType(String executorRenindRepeatType) {
        ExecutorRenindRepeatType = executorRenindRepeatType;
    }
}
