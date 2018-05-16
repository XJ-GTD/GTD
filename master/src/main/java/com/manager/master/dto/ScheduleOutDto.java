package com.manager.master.dto;

import java.util.Date;
/**
 * 日程
 * creaty  zy
 */
public class ScheduleOutDto {
    private int scheduleId;         //事件id
    private String scheduleName;         //事件名
    private String scheduleDetail;       //事件详情
    private int scheduleIssuer;       //发布人
    private int scheduleExecutor;       //执行人EXECUTOR
    private Date scheduleCreateDate;     //创建时间SCHEDULE_CREATE_DATE
    private Date  scheduleStartDate;     //开始时间SCHEDULE_START_DATE
    private Date  scheduleFinishDate;     //完成时间SCHEDULE_FINISH_DATE
    private Date scheduleEndDate;     //截止时间SCHEDULE_END_DATE
    private String scheduleState;     //事件状态SCHEDULE_STATE(-1 未完成 1完成)
    private String GroupId;          //组群idGROUP_ID
    private String scheduleMap;     //位置SCHEDULE_MAP
    private String scheduleRemindDate;     //提醒时间SCHEDULE_REMIND_DATE
    private String scheduleRemindRepeat;     //重复提醒SCHEDULE_REMIND_REPEAT
    private String scheduleRemindRepeatType;     //重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）
    private Date scheduleEditDate;//修改时间SCHEDULE_EDIT_DATE

    //执行事件表(日程关联表)
    private Date ExecutorFinishDate;     //完成时间-执行事件表
    private Date ExecutorRemindDate;    //提醒时间-执行事件表
    private String ExecutorRemindRepeat;     //重复提醒-执行事件表
    private String ExecutorRemindRepeatType;     //重复提醒类型-执行事件表（1 每日 2 每月 3每年）
    private int userId;//执行人ID  USER_ID
    //事件ID
    private int executorState;//事件状态 EXECUTOR_STATE
    //用户表
    private String userName;//执行人姓名（用户表里的用户名）

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleDetail() {
        return scheduleDetail;
    }

    public void setScheduleDetail(String scheduleDetail) {
        this.scheduleDetail = scheduleDetail;
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

    public Date getScheduleFinishDate() {
        return scheduleFinishDate;
    }

    public void setScheduleFinishDate(Date scheduleFinishDate) {
        this.scheduleFinishDate = scheduleFinishDate;
    }

    public Date getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(Date scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

    public String getScheduleState() {
        return scheduleState;
    }

    public void setScheduleState(String scheduleState) {
        this.scheduleState = scheduleState;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public String getScheduleMap() {
        return scheduleMap;
    }

    public void setScheduleMap(String scheduleMap) {
        this.scheduleMap = scheduleMap;
    }

    public String getScheduleRemindDate() {
        return scheduleRemindDate;
    }

    public void setScheduleRemindDate(String scheduleRemindDate) {
        this.scheduleRemindDate = scheduleRemindDate;
    }

    public String getScheduleRemindRepeat() {
        return scheduleRemindRepeat;
    }

    public void setScheduleRemindRepeat(String scheduleRemindRepeat) {
        this.scheduleRemindRepeat = scheduleRemindRepeat;
    }

    public String getScheduleRemindRepeatType() {
        return scheduleRemindRepeatType;
    }

    public void setScheduleRemindRepeatType(String scheduleRemindRepeatType) {
        this.scheduleRemindRepeatType = scheduleRemindRepeatType;
    }

    public Date getExecutorRemindDate() {
        return ExecutorRemindDate;
    }

    public void setExecutorRemindDate(Date executorRemindDate) {
        ExecutorRemindDate = executorRemindDate;
    }

    public String getExecutorRemindRepeat() {
        return ExecutorRemindRepeat;
    }

    public void setExecutorRemindRepeat(String executorRemindRepeat) {
        ExecutorRemindRepeat = executorRemindRepeat;
    }

    public String getExecutorRemindRepeatType() {
        return ExecutorRemindRepeatType;
    }

    public void setExecutorRemindRepeatType(String executorRemindRepeatType) {
        ExecutorRemindRepeatType = executorRemindRepeatType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getScheduleEditDate() {
        return scheduleEditDate;
    }

    public void setScheduleEditDate(Date scheduleEditDate) {
        this.scheduleEditDate = scheduleEditDate;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExecutorState() {
        return executorState;
    }

    public void setExecutorState(int executorState) {
        this.executorState = executorState;
    }

    public Date getExecutorFinishDate() {
        return ExecutorFinishDate;
    }

    public void setExecutorFinishDate(Date executorFinishDate) {
        ExecutorFinishDate = executorFinishDate;
    }
}
