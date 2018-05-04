package com.manager.master.service;

import com.manager.master.dto.ScheduleOutDao;

import java.util.List;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */
public interface ScheduleService {

    /**
     * 查询个人日程信息
     * @return
     */
    List<ScheduleOutDao>  findSchedule(String scheduleExecutor);
    /**
     * 日程创建
     * @param mobile  登录账号
     * @param passWord    登录密码
     */
    ScheduleOutDao creatySchedule(String mobile, String passWord);
}
