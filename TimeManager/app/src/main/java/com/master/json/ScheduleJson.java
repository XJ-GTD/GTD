package com.master.json;

/**
 * create by wzy on 2018/05/07
 * 日程类
 */
public class ScheduleJson extends BaseJson {

    private String scheduleName;         //事件名
    private String scheduleDetial;       //事件详情
    private int scheduleIssuer;       //发布人
    private String scheduleCreateDate;     //创建时间SCHEDULE_CREATE_DATE
    private String scheduleStartDate;     //开始时间SCHEDULE_START_DATE
    private String scheduleFinshDate;     //完成时间SCHEDULE_FINISH_DATE
    private String scheduledEndDate;     //截止时间SCHEDULE_END_DATE
    private String scheduledState;     //事件状态SCHEDULE_STATE(-1 未完成 1完成)
    private int GroupId;          //组群idGROUP_ID
    private String scheduledMap;     //位置SCHEDULE_MAP
    private String scheduledRenindDate;     //提醒时间SCHEDULE_REMIND_DATE
    private String scheduledRenindRepeat;     //重复提醒SCHEDULE_REMIND_REPEAT
    private String scheduledRenindRepeatType;     //重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）
    private String flagCreateGroup;     //是否创建群组（1否 0是）
    private String flagFocus;     //是否关注（1否 0是）

    //执行事件表(日程关联表)
    private int scheduledId;          //执行事件IDSCHEDULE_ID
    private String userId;         //执行人电话（执行人id）
    private String ExecutorFinshDate;     //完成时间-执行事件表
    private String ExecutorRenindDate;    //提醒时间-执行事件表
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

    public String getScheduleFinshDate() {
        return scheduleFinshDate;
    }

    public void setScheduleFinshDate(String scheduleFinshDate) {
        this.scheduleFinshDate = scheduleFinshDate;
    }

    public String getScheduledEndDate() {
        return scheduledEndDate;
    }

    public void setScheduledEndDate(String scheduledEndDate) {
        this.scheduledEndDate = scheduledEndDate;
    }

    public String getScheduledState() {
        return scheduledState;
    }

    public void setScheduledState(String scheduledState) {
        this.scheduledState = scheduledState;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
}
