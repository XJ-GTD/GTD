package com.manager.master.service.serviceImpl;

import com.manager.master.bean.UserInfoBean;
import com.manager.master.dao.IUserDao;
import com.manager.master.dao.IScheduleDao;
import com.manager.master.dto.ScheduleInDto;
import com.manager.master.dto.ScheduleOutDto;
import com.manager.master.service.IGroupService;
import com.manager.master.service.IScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * create by zy on 2018/05/05.
 * 日程管理
 */

@Service
@Transactional
public class ScheduleServiceImpl implements IScheduleService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private IScheduleDao scheduleDao;
    @Resource
    private IUserDao userDao;
    @Autowired
    IGroupService IGroupService;
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
    public int createSchedule(@RequestBody ScheduleInDto inDto) {
        inDto.setScheduledState("-1"); //事件状态SCHEDULE_STATE(-1 未完成 1完成)
//        inDto.setScheduleCreateDate(new Date());// new Date()为获取当前系统时间
        DateFormat df2= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date scheduleStartDate=null;
        Date scheduleFinishDate=null;
        Date scheduledEndDate=null;
        try {
            if(inDto.getScheduleStartDate()!=null){
                scheduleStartDate=df2.parse(inDto.getScheduleStartDate());// 开始时间
            }
            if(inDto.getScheduleFinishDate()!=null) {
                scheduleFinishDate = df2.parse(inDto.getScheduleFinishDate());// 完成时间
            }
            if(inDto.getScheduledEndDate()!=null) {
                scheduledEndDate = df2.parse(inDto.getScheduledEndDate());// 截止时间
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        String scheduleStartDate=inDto.getScheduleStartDate();// 开始时间
//        String scheduleFinishDate = inDto.getScheduleFinishDate();// 完成时间
//        String scheduleCreateDate=df2.format(new Date());// new Date()为获取当前系统时间new Date()
//        String scheduledEndDate = inDto.getScheduledEndDate();// 截止时间

        String scheduleName=inDto.getScheduleName();//事件名称
        String scheduleDetial=inDto.getScheduleDetial();//事件详情
        int scheduleIssuer=inDto.getScheduleIssuer();//发布人id
        Date scheduleCreateDate=new Date();// 创建时间
        String scheduledState=inDto.getScheduledState();//事件状态(-1 未完成 1完成)
        String  groupId=inDto.getGroupId();//组群id
        String scheduledMap=inDto.getScheduledMap();//位置
        String scheduledRenindDate=inDto.getScheduledRenindDate();//提醒时间
        String scheduledRenindRepeat=inDto.getScheduledRenindRepeat();//重复提醒
        String  scheduledRenindRepeatType=inDto.getScheduledRenindRepeatType();//重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）
        scheduleDao.createSchedule(scheduleName,scheduleDetial,scheduleIssuer,
                scheduleCreateDate,scheduleStartDate,scheduleFinishDate,
                scheduledEndDate,scheduledState,groupId,
                scheduledMap,scheduledRenindDate,scheduledRenindRepeat,
                scheduledRenindRepeatType);


        return this.selectScheduleId();
    }


    /**
     * 获取上次添加日程id
     * @param
     */
    public int selectScheduleId(){
        int ScheduleId=scheduleDao.selectScheduleId();
      return ScheduleId;
    }



    /**
     * 日程关联创建（执行事件表）
     * @param
     */
    public  void   createExecutorSchedule(@RequestBody ScheduleInDto inDto){
        int  userid=0;
        DateFormat df2= new SimpleDateFormat("yyyy/MM/dd HH:mm");
        int scheduledId=inDto.getScheduleId();         //执行事件IDSCHEDULE_ID
        String  userMobile=inDto.getUserId();         //执行人电话（执行人id）String  ,拼写字符串
        Date executorFinshDate= null;     //完成时间-执行事件表
        Date executorRenindDate=null;       //提醒时间-执行事件表
        try {
            if(inDto.getExecutorFinshDate()!=null) {
                executorFinshDate = df2.parse(inDto.getExecutorFinshDate());//完成时间-执行事件表
            }
            if(inDto.getExecutorRenindDate()!=null) {
                executorRenindDate = df2.parse(inDto.getExecutorRenindDate());    //提醒时间-执行事件表
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String scheduledState=inDto.getScheduledState();//事件状态(-1 未完成 1完成)
        String executorRenindRepeat=inDto.getExecutorRenindRepeat();     //重复提醒-执行事件表
        String executorRenindRepeatType=inDto.getExecutorRenindRepeatType();     //重复提醒类型-执行事件表（1 每日 2 每月 3每年）


        if(userMobile!=null){
            //添加群组创建人
            String groupId=inDto.getGroupId();
            int userId=inDto.getScheduleIssuer();//获取用户id
            int roleId=1;//1群主 2成员 3发布人 4执行人
            String groupName=inDto.getScheduleName();
            if("0".equals(inDto.getFlagCreateGroup())){
                IGroupService.createGroup(groupId,groupName,userId, roleId);
            }
            //分割电话号码
            String[] mobile = userMobile.split(",");
            for (int i = 0; i < mobile.length; i++) {
                //获取用户id
                UserInfoBean userInfo= userDao.findUser(mobile[i]);
                userid=userInfo.getUserId();
                //添加执行事件表
                scheduleDao.createExecutorScheduleId(userid,scheduledId,executorFinshDate,scheduledState,executorRenindDate,executorRenindRepeat,executorRenindRepeatType);

                //添加群组
                groupId=inDto.getGroupId();
                userId=userInfo.getUserId();//获取用户id
                roleId=2;
                groupName=inDto.getScheduleName();
                if("0".equals(inDto.getFlagCreateGroup())) {
                    IGroupService.createGroup(groupId, groupName, userId, roleId);
                }
            }
        }else{
            //执行人为空时发布人变为执行人
            userid=inDto.getScheduleIssuer();
            scheduleDao.createExecutorScheduleId(userid,scheduledId,executorFinshDate,scheduledState,executorRenindDate,executorRenindRepeat,executorRenindRepeatType);
        }
    }

    /**
     * 查询个人单条日程信息
     *
     * @param scheduledId
     * @return
     */
    @Override
    public ScheduleOutDto findScheduleByOne(int scheduledId) {

        return scheduleDao.findScheduleByOne(scheduledId);
    }

    /**
     * 编辑个人单条日程信息
     *
     * @param inDto
     * @return
     */
    @Override
    public ScheduleOutDto updateSchedule(ScheduleInDto inDto) {

        DateFormat df2= new SimpleDateFormat("yyyy/MM/dd HH:mm");
        int scheduledId=inDto.getScheduleId();
        String scheduleName=inDto.getScheduleName();
        String scheduleDetial=inDto.getScheduleDetial();
        int scheduleIssuer=inDto.getScheduleIssuer();
        Date scheduleCreateDate= null;
        Date  scheduleStartDate= null;
        Date scheduleFinshDate=null;
        Date scheduledEndDate=null;
        try {
            if(inDto.getScheduleCreateDate()!=null) {
                scheduleCreateDate = df2.parse(inDto.getScheduleCreateDate());
            }
            if(inDto.getScheduleStartDate()!=null) {
                scheduleStartDate = df2.parse(inDto.getScheduleStartDate());
            }
            if(inDto.getScheduleFinishDate()!=null) {
                scheduleFinshDate = df2.parse(inDto.getScheduleFinishDate());
            }
            if(inDto.getScheduledEndDate()!=null) {
                scheduledEndDate = df2.parse(inDto.getScheduledEndDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Date scheduleEditDate=new Date();
        String scheduledState=inDto.getScheduledState();
        String GroupId=inDto.getGroupId();
        String scheduledMap=inDto.getScheduledMap();
        String scheduledRenindDate=inDto.getScheduledRenindDate();
        String scheduledRenindRepeat=inDto.getScheduledRenindRepeat();
        String scheduledRenindRepeatTyp=inDto.getScheduledRenindRepeatType();

        scheduleDao.updateSchedule(scheduledId,scheduleName,scheduleDetial,
                scheduleIssuer,scheduleCreateDate,scheduleStartDate,
                scheduleEditDate,scheduleFinshDate,scheduledEndDate,
                scheduledState,GroupId,scheduledMap,
                scheduledRenindDate,scheduledRenindRepeat,scheduledRenindRepeatTyp);
        return null;
    }
}
