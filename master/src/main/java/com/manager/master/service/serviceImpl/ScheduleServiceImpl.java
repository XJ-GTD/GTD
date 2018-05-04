package com.manager.master.service.serviceImpl;

import com.manager.master.dao.ScheduleDao;
import com.manager.master.dto.ScheduleInDao;
import com.manager.master.dto.ScheduleOutDao;
import com.manager.master.service.ScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */

@Service
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
    public List<ScheduleOutDao> findSchedule(int scheduleExecutor) {
        scheduleDao.findSchedule(scheduleExecutor);
        return scheduleDao.findSchedule(scheduleExecutor);
    }

    /**
     * 添加日程
     * @param inDto
     * @return
     */
    @Override
    public ScheduleOutDao creatySchedule(@RequestBody ScheduleInDao inDto) {
        inDto.setScheduleCreateDate(new Date());// new Date()为获取当前系统时间
        return null;
    }
}
