package com.manager.master.service;

import com.manager.master.dto.ScheduleInDto;
import com.manager.master.dto.ScheduleOutDto;
import org.springframework.web.bind.annotation.RequestBody;

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
    List<ScheduleOutDto>  findSchedule(int scheduleExecutor);
    /**
     * 日程创建
     * @param
     */
    ScheduleOutDto creatySchedule(@RequestBody ScheduleInDto inDto);
}
