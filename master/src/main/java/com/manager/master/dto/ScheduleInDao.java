package com.manager.master.dto;

public class ScheduleInDao {
    String scheduleName;         //事件名
    String scheduleDetial;       //事件详情
    String scheduleIssuer;       //发布人
    String scheduleExecutor;       //执行人EXECUTOR
    String scheduleStartDate;     //完成时间SCHEDULE_START_DATE
    String scheduledEndDate;     //截止时间SCHEDULE_END_DATE
    String scheduledState;     //事件状态SCHEDULE_STATE(-1 未完成 1完成)
    String GroupId;          //组群idGROUP_ID
    String scheduledMap;     //位置SCHEDULE_MAP
    String scheduledRenindDate;     //提醒时间SCHEDULE_REMIND_DATE
    String scheduledRenindRepeat;     //重复提醒SCHEDULE_REMIND_REPEAT
    String scheduledRenindRepeatType;     //重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）

    public String getScheduleExecutor() {
        return scheduleExecutor;
    }

    public void setScheduleExecutor(String scheduleExecutor) {
        this.scheduleExecutor = scheduleExecutor;
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

    public String getScheduleIssuer() {
        return scheduleIssuer;
    }

    public void setScheduleIssuer(String scheduleIssuer) {
        this.scheduleIssuer = scheduleIssuer;
    }

    public String getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(String scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
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
}
