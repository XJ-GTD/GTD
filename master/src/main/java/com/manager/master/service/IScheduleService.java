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
     * 日程关联创建（执行事件表）
     * @param
     */
    void  createExecutorSchedule(@RequestBody ScheduleInDto inDto);


    /**
     * 查询个人单条日程信息
     * @return
     */
    ScheduleOutDto  findScheduleByOne(int scheduledId);
    /**
     * 编辑个人单条日程信息
     * @return
     */
    ScheduleOutDto  updateSchedule(ScheduleInDto inDto);

    /**
     * 查询一个群组下的所有日程
     * @return
     */
    List<ScheduleOutDto>  findScheduleByGroup(String groupId);

    /**
     * 查询一个群组下的所有日程（含有执行人姓名）
     * @return
     */
    List<ScheduleOutDto>  findScheduleAndUserName(String groupId);

    /**
     * 根据事件ID和执行人ID查询事件表和执行事件表。
     * @return
     */
    List<ScheduleOutDto>  findScheduleAndExeBySchIdAndUserId(int scheduleId,int userId);


    /**
     * 群组事件创建
     * @param
     */
     void createSchByGroupId(@RequestBody ScheduleOutDto inDto);
    /**
     * 创建执行事件表
     * @param
     */
    void createExecutorScheduleAfterCreateGroupSch(@RequestBody ScheduleOutDto inDto);

}
