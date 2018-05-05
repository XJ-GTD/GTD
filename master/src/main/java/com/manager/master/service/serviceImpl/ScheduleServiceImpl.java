package com.manager.master.service.serviceImpl;

import com.manager.master.dao.ScheduleDao;
import com.manager.master.dto.ScheduleInDto;
import com.manager.master.dto.ScheduleOutDto;
import com.manager.master.service.ScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ScheduleDao scheduleDao;

    /**
     * 查询个人日程
     * @param scheduleExecutor
     * @return
     */
    @Override
    public List<ScheduleOutDto> findSchedule(int scheduleExecutor) {
        scheduleDao.findSchedule(scheduleExecutor);
        return scheduleDao.findSchedule(scheduleExecutor);
    }

    /**
     * 添加日程
     * @param inDto
     * @return
     */
    @Override
    public ScheduleOutDto creatySchedule(@RequestBody ScheduleInDto inDto) {
        inDto.setScheduledState("-1"); //事件状态SCHEDULE_STATE(-1 未完成 1完成)
        inDto.setScheduleCreateDate(new Date());// new Date()为获取当前系统时间

        String scheduleName=inDto.getScheduleName();//事件名称
        String scheduleDetial=inDto.getScheduleDetial();//事件详情
        int scheduleIssuer=inDto.getScheduleIssuer();//发布人id
        Date scheduleCreateDate=inDto.getScheduleCreateDate();// 创建时间
        Date scheduleStartDate=inDto.getScheduleStartDate();// 开始时间
        Date scheduleFinshDate=inDto.getScheduleFinshDate();// 完成时间
        Date scheduledEndDate=inDto.getScheduledEndDate();// 截止时间
        String scheduledState=inDto.getScheduledState();//事件状态(-1 未完成 1完成)
        int  groupId=inDto.getGroupId();//组群id
        String scheduledMap=inDto.getScheduledMap();//位置
        String scheduledRenindDate=inDto.getScheduledRenindDate();//提醒时间
        String scheduledRenindRepeat=inDto.getScheduledRenindRepeat();//重复提醒
        String  scheduledRenindRepeatType=inDto.getScheduledRenindRepeatType();//重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）

        return scheduleDao.creatySchedule(scheduleName,scheduleDetial,scheduleIssuer,
        scheduleCreateDate,scheduleStartDate,scheduleFinshDate,
        scheduledEndDate,scheduledState,groupId,
        scheduledMap,scheduledRenindDate,scheduledRenindRepeat,
        scheduledRenindRepeatType);
    }
}
