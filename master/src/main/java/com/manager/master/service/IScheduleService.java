package com.manager.master.service;

import com.manager.master.dto.ScheduleInDto;
import com.manager.master.dto.ScheduleOutDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * create by zy on 2018/05/05.
 *  * 日程管理
 */
public interface IScheduleService {

    /**
     * 查询个人日程信息
     * @return
     */
    List<ScheduleOutDto>  findSchedule(int scheduleExecutor);
    /**
     * 日程创建
     * @param
     */
    int createSchedule(@RequestBody ScheduleInDto inDto);

    /**
     * 获取上次添加日程id
     * @param
     */
    int selectScheduleId();

    /**
     * 日程关联创建（执行事件表）
     * @param
     */
    void  createExecutorSchedule(@RequestBody ScheduleInDto inDto);
}