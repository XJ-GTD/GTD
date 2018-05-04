package com.manager.master.service.serviceImpl;

import com.manager.master.dao.ScheduleDao;
import com.manager.master.dto.ScheduleInDao;
import com.manager.master.dto.ScheduleOutDao;
import com.manager.master.service.ScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create by wzy on 2018/04/24.
 * 用户管理
 */

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ScheduleDao scheduleDao;

    @Override
    public ScheduleOutDao findSchedule(String scheduleExecutor) {
        scheduleDao.findSchedule(scheduleExecutor);
        return scheduleDao.findSchedule(scheduleExecutor);
    }

    @Override
    public ScheduleOutDao creatySchedule(String mobile, String passWord) {
        return null;
    }
}
