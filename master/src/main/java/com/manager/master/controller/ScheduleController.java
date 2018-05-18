package com.manager.master.controller;

import com.manager.master.dto.BaseOutDto;
import com.manager.master.dto.PersonScheduleDto;
import com.manager.master.dto.ScheduleInDto;
import com.manager.master.dto.ScheduleOutDto;
import com.manager.master.service.IScheduleService;
import com.manager.util.UUIDUtil;
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
@RequestMapping(value = "/schedule")
public class ScheduleController {
    private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    IScheduleService scheduleService;

    /**
     * 日程创建
     * @parame
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public BaseOutDto create(@RequestBody ScheduleInDto inDto) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, Object> data = new HashMap<>();
        //获取群编号
        String uuid =UUIDUtil.getUUID();
        inDto.setGroupId(uuid);//给群组id加上关联号

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
//        if(inDto.getScheduleIssuer()==0 ){
//            outBean.setCode("1");
//            outBean.setMessage("[发布人为空]");
//            logger.info("[发布人为空！]");
//        }
//        if(inDto.getGroupId()==null  &&  "".equals(inDto.getGroupId())){
//            outBean.setCode("1");
//            outBean.setMessage("[群组ID为空]");
//            logger.info("[群组ID为空！]");
//        }
//        scheduleService.createSchedule(inDto);

        //查询日程id
//        int  ScheduleId=scheduleService.selectScheduleId();
        int  ScheduleId = scheduleService.createSchedule(inDto);
        inDto.setScheduleId(ScheduleId);
        //添加日程关联
        scheduleService.createExecutorSchedule(inDto);


        data.put("scheduleInfo", ScheduleId);
        outBean.setData(data);
        outBean.setCode("0");
        outBean.setMessage("[创建成功]");
        logger.info("[创建成功]");
        return outBean;
    }
    /**
     * 个人日程编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
    @ResponseBody
    public BaseOutDto updateSchedule(@RequestBody ScheduleInDto inDto) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, ScheduleOutDto> data = new HashMap<>();
        inDto.setScheduleState("-1");//事件状态(-1 未完成 1完成)
        scheduleService.updateSchedule(inDto);

        outBean.setData(data);
        outBean.setCode("0");
        outBean.setMessage("[修改成功]");
        logger.info("[修改成功]"+ data);


        return outBean;
    }
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
        List<ScheduleOutDto> ScheduleDataList = scheduleService.findSchedule(Integer.parseInt(inDto.getUserId()));

        if(ScheduleDataList != null){
            data.put("scheduleInfoList", ScheduleDataList);
            outBean.setData(data);
            outBean.setCode("0");
            outBean.setMessage("[查询成功]");
            logger.info("[查询成功]"+ data);
        }else{
            data.put("scheduleInfoList", ScheduleDataList);
            outBean.setData(data);
            outBean.setCode("1");
            outBean.setMessage("[查询失败]");
            logger.info("[查询失败]" + data);
        }

        return outBean;
    }

    /**
     * 单条日程查询
     * @param scheduledId
     * @return
     */
    @RequestMapping(value = "/findScheduleByOne/{scheduledId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseOutDto findByOne(@PathVariable int scheduledId) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, ScheduleOutDto> data = new HashMap<>();
        ScheduleOutDto ScheduleData = scheduleService.findScheduleByOne(scheduledId);

        if(ScheduleData != null){
            data.put("scheduleInfo", ScheduleData);
            outBean.setData(data);
            outBean.setCode("0");
            outBean.setMessage("[查询成功]");
            logger.info("[查询成功]"+ data);
        }else{
            data.put("scheduleInfo", ScheduleData);
            outBean.setData(data);
            outBean.setCode("1");
            outBean.setMessage("[查询失败]");
            logger.info("[查询失败]" + data);
        }

        return outBean;
    }
    /**
     * 一个群组下的所有日程查询
     * @param
     * @return
     */
    @RequestMapping(value = "/findSchByGroup/{groupId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseOutDto findScheduleByGroupId(@PathVariable String groupId) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, List<ScheduleOutDto>> data = new HashMap<>();
        List<ScheduleOutDto> ScheduleDataList = scheduleService.findScheduleByGroup(groupId);

        if(ScheduleDataList != null){
            data.put("scheduleInfoList", ScheduleDataList);
            outBean.setData(data);
            outBean.setCode("0");
            outBean.setMessage("[查询成功]");
            logger.info("[查询成功]"+ data);
        }else{
            data.put("scheduleInfoList", ScheduleDataList);
            outBean.setData(data);
            outBean.setCode("1");
            outBean.setMessage("[查询失败]");
            logger.info("[查询失败]" + data);
        }

        return outBean;
    }
    /**
     * 根据事件ID和USERID查询时间表和执行表
     * @param
     * @return
     */
    @RequestMapping(value = "/findSchAndExcu/{scheduleId}/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseOutDto findSchAndExcuByScheduleIdAndUserId(@PathVariable int scheduleId,@PathVariable int userId) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, ScheduleOutDto> data = new HashMap<>();
        ScheduleOutDto ScheduleData = scheduleService.findScheduleAndExeBySchIdAndUserId(scheduleId,userId);

        if(ScheduleData != null){
            data.put("scheduleInfo", ScheduleData);
            outBean.setData(data);
            outBean.setCode("0");
            outBean.setMessage("[查询成功]");
            logger.info("[查询成功]"+ data);
        }else{
            data.put("scheduleInfo", ScheduleData);
            outBean.setCode("1");
            outBean.setData(data);
            outBean.setMessage("[查询失败]");
            logger.info("[查询失败]" + data);
        }

        return outBean;
    }
    /**
     * 新建群组下的事件,然后添加执行事件表的关联
     * @parame
     * @return
     */
    @RequestMapping(value = "/createII", method = RequestMethod.POST)
    @ResponseBody
    public BaseOutDto createSchBy(@RequestBody ScheduleInDto inDto) {
        BaseOutDto outBean = new BaseOutDto();
        //添加日程
        scheduleService.createSchByGroupId(inDto);
        //添加日程关联
        scheduleService.createExecutorScheduleAfterCreateGroupSch(inDto);
        outBean.setCode("0");
        outBean.setMessage("[创建成功]");
        logger.info("[创建成功]");
        return outBean;
    }

    /**
     * 个人日历日程查询
     * @parame
     * @return
     */
    @RequestMapping(value = "/createschbycalendar/{date}/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseOutDto createSchByCalendar(@PathVariable String date,@PathVariable int userId) {
        BaseOutDto outBean = new BaseOutDto();
        PersonScheduleDto PersonScheduleDto=new PersonScheduleDto();
//        PersonScheduleDto.setCalendarDate(date);

        outBean.setCode("0");
        outBean.setMessage("[创建成功]");
        logger.info("[创建成功]");
        return outBean;
    }
    /**
     * 个人日程编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/updateScheduleByScheduleIdAndUserId", method = RequestMethod.POST)
    @ResponseBody
    public BaseOutDto updateScheduleByScheduleIdAndUserId(@RequestBody ScheduleInDto inDto) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, ScheduleOutDto> data = new HashMap<>();
        inDto.setScheduleState("-1");//事件状态(-1 未完成 1完成)
        scheduleService.updateScheduleByScheduleIdAndUserId(inDto);

        outBean.setData(data);
        outBean.setCode("0");
        outBean.setMessage("[修改成功]");
        logger.info("[修改成功]"+ data);

        return outBean;
    }

}
