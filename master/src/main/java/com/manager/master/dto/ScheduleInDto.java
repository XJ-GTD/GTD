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
    private String scheduleCreateDate;     //创建时间SCHEDULE_CREATE_DATE
    private String  scheduleStartDate;     //开始时间SCHEDULE_START_DATE
    private String  scheduleFinishDate;     //完成时间SCHEDULE_FINISH_DATE
    private String scheduledEndDate;     //截止时间SCHEDULE_END_DATE
    private String scheduledState;     //事件状态SCHEDULE_STATE(-1 未完成 1完成)
    private String GroupId;          //组群idGROUP_ID
    private String scheduledMap;     //位置SCHEDULE_MAP
    private String scheduledRenindDate;     //提醒时间SCHEDULE_REMIND_DATE
    private String scheduledRenindRepeat;     //重复提醒SCHEDULE_REMIND_REPEAT
    private String scheduledRenindRepeatType;     //重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）
    private String flagCreateGroup;     //是否创建群组（0否 1是）
    private String flagFocus;     //是否关注（0否 1是）

    //执行事件表(日程关联表)
    private int scheduleId;          //执行事件IDSCHEDULE_ID
    private String  userId;         //执行人电话（执行人id）  String  ,拼写字符串
    private String ExecutorFinshDate;     //完成时间-执行事件表
    private String  ExecutorRenindDate;    //提醒时间-执行事件表
    private String ExecutorRenindRepeat;     //重复提醒-执行事件表
    private String ExecutorRenindRepeatType;     //重复提醒类型-执行事件表（1 每日 2 每月 3每年）



    private String schedulePhoneNum;//发布人电话号码
    private String scheduleFinshDateString;//完成时间(String)

    public String getFlagCreateGroup() {
        return flagCreateGroup;
    }

    public void setFlagCreateGroup(String flagCreateGroup) {
        this.flagCreateGroup = flagCreateGroup;
    }

    public String getFlagFocus() {
        return flagFocus;
    }

    public void setFlagFocus(String flagFocus) {
        this.flagFocus = flagFocus;
    }

    public String getSchedulePhoneNum() {
        return schedulePhoneNum;
    }

    public void setSchedulePhoneNum(String schedulePhoneNum) {
        this.schedulePhoneNum = schedulePhoneNum;
    }
    public String getScheduleFinshDateString() {
        return scheduleFinshDateString;
    }

    public void setScheduleFinshDateString(String scheduleFinshDateString) {
        this.scheduleFinshDateString = scheduleFinshDateString;
    }

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

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getScheduleCreateDate() {
        return scheduleCreateDate;
    }

    public void setScheduleCreateDate(String scheduleCreateDate) {
        this.scheduleCreateDate = scheduleCreateDate;
    }

    public String getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(String scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public String getScheduleFinishDate() {
        return scheduleFinishDate;
    }

    public void setScheduleFinishDate(String scheduleFinishDate) {
        this.scheduleFinishDate = scheduleFinishDate;
    }

    public String getScheduledEndDate() {
        return scheduledEndDate;
    }

    public void setScheduledEndDate(String scheduledEndDate) {
        this.scheduledEndDate = scheduledEndDate;
    }

    public String getExecutorFinshDate() {
        return ExecutorFinshDate;
    }

    public void setExecutorFinshDate(String executorFinshDate) {
        ExecutorFinshDate = executorFinshDate;
    }

    public String getExecutorRenindDate() {
        return ExecutorRenindDate;
    }

    public void setExecutorRenindDate(String executorRenindDate) {
        ExecutorRenindDate = executorRenindDate;
    }
}
