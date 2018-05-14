package com.master.json;

/**
 * create by wzy on 2018/05/08
 * 群组类
 */
public class GroupJson {

    private String groupId;//群组ID
    private String roleName;//角色ID 1群主 2成员 3发布人 4执行人
    private String groupName;//群组名
    private String scheduleName;//事件名
    private String scheduleCreateDate;//创建时间

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleCreateDate() {
        return scheduleCreateDate;
    }

    public void setScheduleCreateDate(String scheduleCreateDate) {
        this.scheduleCreateDate = scheduleCreateDate;
    }
}
