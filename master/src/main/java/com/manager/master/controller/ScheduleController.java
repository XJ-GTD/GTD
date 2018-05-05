package com.manager.master.controller;

import com.manager.master.dto.BaseOutDto;
import com.manager.master.dto.ScheduleInDto;
import com.manager.master.dto.ScheduleOutDto;
import com.manager.master.service.ScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public BaseOutDto creaty(@RequestBody ScheduleInDto inDto) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, Object> data = new HashMap<>();
        if(inDto.getScheduleName()==null  &&  "".equals(inDto.getScheduleName())){
            outBean.setCode("1");
            outBean.setMessage("[事件名为空]");
            logger.info("[事件名为空！]");
        }
        if(inDto.getScheduleIssuer()==0 ){
            outBean.setCode("1");
            outBean.setMessage("[发布人为空]");
            logger.info("[发布人为空！]");
        }
        if(inDto.getScheduleIssuer()==0 ){
            outBean.setCode("1");
            outBean.setMessage("[发布人为空]");
            logger.info("[发布人为空！]");
        }
        if(inDto.getGroupId()==0 ){
            outBean.setCode("1");
            outBean.setMessage("[群组ID为空]");
            logger.info("[群组ID为空！]");
        }
        scheduleService.creatySchedule(inDto);
        //查询日程id
        int  ScheduleId=scheduleService.selectScheduleId();
        inDto.setScheduledId(ScheduleId);
        //添加日程关联
        scheduleService.creatyExecutorSchedule(inDto);


        data.put("scheduleinfo", ScheduleId);
        outBean.setData(data);
        outBean.setCode("0");
        outBean.setMessage("[创建成功]");
        logger.info("[创建成功]");
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
    public BaseOutDto find(@RequestBody ScheduleInDto inDto) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, List<ScheduleOutDto>> data = new HashMap<>();
        List<ScheduleOutDto> ScheduleData= scheduleService.findSchedule(inDto.getScheduleExecutor());

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
