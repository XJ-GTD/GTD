package com.manager.master.controller;

import com.manager.master.bean.UserAccountBean;
import com.manager.master.dto.BaseOutDto;
import com.manager.master.dto.ScheduleInDao;
import com.manager.master.dto.ScheduleOutDao;
import com.manager.master.service.IUserService;
import com.manager.master.service.ScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by zy on 2018/05/04.
 * 日程类
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/schedul")
public class ScheduleController {
    private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    ScheduleService scheduleService;
    /**
     * 日程创建
     * @param
     * @return
     */
    @RequestMapping(value = "/creaty", method = RequestMethod.POST)
    @ResponseBody
    public BaseOutDto creaty(@RequestBody ScheduleInDao inDto) {
        BaseOutDto outBean = new BaseOutDto();
        ScheduleOutDao ScheduleData= scheduleService.creatySchedule(inDto);
        return outBean;
    }
    /**
     * 日程编辑
     * @param
     * @return
     */

    /**
     * 日程查询
     * @param
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public BaseOutDto find(@RequestBody ScheduleInDao inDto) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, List<ScheduleOutDao>> data = new HashMap<>();
        List<ScheduleOutDao> ScheduleData= scheduleService.findSchedule(inDto.getScheduleExecutor());
        if(ScheduleData!=null){
            data.put("scheduleinfo", ScheduleData);
            outBean.setData(data);
            outBean.setCode("0");
            outBean.setMessage("[查询成功]");
            logger.info("[查询成功]"+ data);
        }else{
            data.put("scheduleinfo", ScheduleData);
            outBean.setData(data);
            outBean.setCode("1");
            outBean.setMessage("[查询失败]");
            logger.info("[查询失败]" + data);
        }

        return outBean;
    }
}
