package com.manager.master.service.serviceImpl;

import com.manager.master.dao.IScheduleDao;
import com.manager.master.dao.IUserDao;
import com.manager.master.dto.ScheduleInDto;
import com.manager.master.dto.ScheduleOutDto;
import com.manager.master.dto.UserInfoOutDto;
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
        return scheduleDao.findSchedule(scheduleExecutor);
    }

    /**
     * 添加日程
     * @param inDto
     * @return
     */
    @Override
    public int createSchedule(@RequestBody ScheduleInDto inDto) {
        inDto.setScheduleState("-1"); //事件状态SCHEDULE_STATE(-1 未完成 1完成)
//        inDto.setScheduleCreateDate(new Date());// new Date()为获取当前系统时间
        DateFormat df2= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date scheduleCreateDate = null;  // 创建时间
        Date scheduleStartDate=null;
        Date scheduleFinishDate=null;
        Date scheduleEndDate=null;
        try {
            if(inDto.getScheduleStartDate()!=null){
                scheduleCreateDate=df2.parse(inDto.getScheduleCreateDate());// 开始时间
            }
            if(inDto.getScheduleStartDate()!=null){
                scheduleStartDate=df2.parse(inDto.getScheduleStartDate());// 开始时间
            }
            if(inDto.getScheduleFinishDate()!=null) {
                scheduleFinishDate = df2.parse(inDto.getScheduleFinishDate());// 完成时间
            }
            if(inDto.getScheduleEndDate()!=null) {
                scheduleEndDate = df2.parse(inDto.getScheduleEndDate());// 截止时间
            }
//        String scheduleStartDate=inDto.getScheduleStartDate();// 开始时间
//        String scheduleFinishDate = inDto.getScheduleFinishDate();// 完成时间
//        String scheduleCreateDate=df2.format(new Date());// new Date()为获取当前系统时间new Date()
//        String scheduledEndDate = inDto.getScheduledEndDate();// 截止时间
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        String scheduleName=inDto.getScheduleName();//事件名称
        String scheduleDetail=inDto.getScheduleDetail();//事件详情
        int scheduleIssuer = inDto.getScheduleIssuer();//发布人id
        String scheduleState = inDto.getScheduleState();//事件状态(-1 未完成 1完成)
        String  groupId = inDto.getGroupId();//组群id
        String scheduleMap = inDto.getScheduleMap();//位置
        Date scheduleRemindDate = inDto.getScheduleRemindDate();//提醒时间

        String scheduleRemindRepeat = inDto.getScheduleRemindRepeat();//重复提醒
        String scheduleRemindRepeatType = inDto.getScheduleRemindRepeatType();//重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）
        scheduleDao.createSchedule(scheduleName,scheduleDetail,scheduleIssuer,
            scheduleCreateDate,scheduleStartDate,scheduleFinishDate,
            scheduleEndDate,scheduleState,groupId,
            scheduleMap,scheduleRemindDate,scheduleRemindRepeat,
            scheduleRemindRepeatType);


        return scheduleDao.selectScheduleId();
    }


    /**
     * 日程关联创建（执行事件表）
     * @param
     */
    public  void   createExecutorSchedule(@RequestBody ScheduleInDto inDto){
        int  userId = 0;
        DateFormat df2= new SimpleDateFormat("yyyy/MM/dd HH:mm");
        int scheduledId = inDto.getScheduleId();         //执行事件IDSCHEDULE_ID
        String userMobile = inDto.getUserId();         //执行人电话（执行人id）String  ,拼写字符串
        Date executorFinishDate= null;     //完成时间-执行事件表
        Date executorRemindDate=null;       //提醒时间-执行事件表
        try {
            if(inDto.getExecutorFinishDate()!=null) {
                executorFinishDate = df2.parse(inDto.getExecutorFinishDate());//完成时间-执行事件表
            }
            if(inDto.getExecutorRemindDate()!=null) {
                executorRemindDate = df2.parse(inDto.getExecutorRemindDate());    //提醒时间-执行事件表
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String scheduledState=inDto.getScheduleState();//事件状态(-1 未完成 1完成)
        String executorRemindRepeat=inDto.getExecutorRemindRepeat();     //重复提醒-执行事件表
        String executorRemindRepeatType=inDto.getExecutorRemindRepeatType();     //重复提醒类型-执行事件表（1 每日 2 每月 3每年）


        if(userMobile!=null){
            //添加群组创建人
            String groupId=inDto.getGroupId();
            userId=inDto.getScheduleIssuer();//获取用户id
            int roleId=1;//1群主 2成员 3发布人 4执行人
            String groupName=inDto.getScheduleName();
            if("0".equals(inDto.getFlagCreateGroup())){
                IGroupService.createGroup(groupId,groupName,userId, roleId);
            }
            //分割电话号码
            String[] mobile = userMobile.split(",");
            for (int i = 0; i < mobile.length; i++) {
                //获取用户id
                UserInfoOutDto userInfo= userDao.findUser(mobile[i]);
                userId=userInfo.getUserId();
                //添加执行事件表
                scheduleDao.createExecutorScheduleId(userId,scheduledId,executorFinishDate,scheduledState,executorRemindDate,executorRemindRepeat,executorRemindRepeatType);

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
            userId=inDto.getScheduleIssuer();
            scheduleDao.createExecutorScheduleId(userId,scheduledId,executorFinishDate,scheduledState,executorRemindDate,executorRemindRepeat,executorRemindRepeatType);
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

        DateFormat df2= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int scheduledId=inDto.getScheduleId();
        String scheduleName=inDto.getScheduleName();
        String scheduleDetail=inDto.getScheduleDetail();
        int scheduleIssuer=inDto.getScheduleIssuer();
        Date scheduleCreateDate= null;
        Date scheduleStartDate= null;
        Date scheduleFinishDate=null;
        Date scheduleEndDate=null;
        try {
            if(inDto.getScheduleStartDate()!=null) {
                scheduleCreateDate = df2.parse(inDto.getScheduleCreateDate());
            }
            if(inDto.getScheduleStartDate()!=null) {
                scheduleStartDate = df2.parse(inDto.getScheduleStartDate());
            }
            if(inDto.getScheduleFinishDate()!=null) {
                scheduleFinishDate = df2.parse(inDto.getScheduleFinishDate());
            }
            if(inDto.getScheduleEndDate()!=null) {
                scheduleEndDate = df2.parse(inDto.getScheduleEndDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Date scheduleEditDate=new Date();
        String scheduleState=inDto.getScheduleState();
        String GroupId=inDto.getGroupId();
        String scheduleMap=inDto.getScheduleMap();
        Date scheduleRemindDate=inDto.getScheduleRemindDate();
        String scheduleRemindRepeat=inDto.getScheduleRemindRepeat();
        String scheduleRemindRepeatTyp=inDto.getScheduleRemindRepeatType();

        scheduleDao.updateSchedule(scheduledId,scheduleName,scheduleDetail,
                scheduleIssuer,scheduleCreateDate,scheduleStartDate,
                scheduleEditDate,scheduleFinishDate,scheduleEndDate,
                scheduleState,GroupId,scheduleMap,
                scheduleRemindDate,scheduleRemindRepeat,scheduleRemindRepeatTyp);
        return null;
    }

    /**
     * 查询一个群组下的所有日程
     *
     * @param groupId
     * @return
     */
    @Override
    public List<ScheduleOutDto> findScheduleByGroup(String groupId) {
        String result = "";
        //查询群组下的日程
        List<ScheduleOutDto> schList= scheduleDao.findScheduleByGroup(groupId);

        for (ScheduleOutDto sod : schList ) {
            List<ScheduleOutDto> sodAndUser =  scheduleDao.findScheduleAndUserName(groupId);
            //拼接执行人姓名
            for (ScheduleOutDto sodUser : sodAndUser) {
                String userName = sodUser.getUserName();
                if ("".equals(result) || result==null){
                    result = userName;
                }else {
                    result = result+","+userName;
                }

            }
            sod.setUserName(result);
        }

        return schList;
    }

    /**
     * 查询一个群组下的所有日程（含有执行人姓名）
     *
     * @param groupId
     * @return
     */
    @Override
    public List<ScheduleOutDto> findScheduleAndUserName(String groupId) {
        return scheduleDao.findScheduleAndUserName(groupId);
    }

    /**
     * 根据事件ID和执行人ID查询事件表和执行事件表。
     *
     * @param userId
     * @return
     */
    @Override
    public ScheduleOutDto findScheduleAndExeBySchIdAndUserId(int scheduleId,int userId) {
        return scheduleDao.findScheduleAndExeBySchIdAndUserId(scheduleId,userId);
    }

    /**
     * 群组事件创建
     *
     * @param outDto
     */
    @Override
    public void createSchByGroupId(ScheduleOutDto outDto) {
        outDto.setScheduleState("-1"); //事件状态SCHEDULE_STATE(-1 未完成 1完成)
//        inDto.setScheduleCreateDate(new Date());// new Date()为获取当前系统时间
        DateFormat df2= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date scheduleStartDate=null;
        Date scheduleFinishDate=null;
        Date scheduleEndDate=null;
        Date scheduleEditDate = null;
        try {
            scheduleStartDate=outDto.getScheduleStartDate();// 开始时间
            scheduleFinishDate = outDto.getScheduleFinishDate();// 完成时间
            scheduleEndDate = outDto.getScheduleEndDate();// 截止时间
            scheduleEditDate = outDto.getScheduleEditDate();
            String scheduleName=outDto.getScheduleName();//事件名称
            String scheduleDetail=outDto.getScheduleDetail();//事件详情
            int scheduleIssuer=outDto.getScheduleIssuer();//发布人id
            Date scheduleCreateDate=new Date();// 创建时间
            String scheduleState=outDto.getScheduleState();//事件状态(-1 未完成 1完成)

            String  groupId=outDto.getGroupId();//组群id
            String scheduleMap=outDto.getScheduleMap();//位置
            String scheduleRemindDate=outDto.getScheduleRemindDate();//提醒时间
            String scheduleRemindRepeat=outDto.getScheduleRemindRepeat();//重复提醒
            String  scheduleRemindRepeatType=outDto.getScheduleRemindRepeatType();//重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）
            scheduleDao.createSchByGroupId(scheduleName,scheduleDetail,scheduleIssuer,
                    scheduleCreateDate,scheduleStartDate,scheduleEditDate, scheduleFinishDate,
                    scheduleEndDate,scheduleState,groupId,
                    scheduleMap,scheduleRemindDate,scheduleRemindRepeat,
                    scheduleRemindRepeatType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建执行事件表
     *
     * @param outDto
     */
    @Override
    public void createExecutorScheduleAfterCreateGroupSch(ScheduleOutDto outDto) {
        int  userId = 0;
        DateFormat df2= new SimpleDateFormat("yyyy/MM/dd HH:mm");
        int scheduleId = outDto.getScheduleId();         //执行事件IDSCHEDULE_ID
        String userMobile = outDto.getUserId();         //执行人电话（执行人id）String  ,拼写字符串
//        Date executorFinishDate= null;     //完成时间-执行事件表
//        Date executorRemindDate=null;       //提醒时间-执行事件表
        Date executorFinishDate =outDto.getExecutorFinishDate();//完成时间-执行事件表
        Date  executorRemindDate = outDto.getExecutorRemindDate();    //提醒时间-执行事件表
        String scheduleState=outDto.getScheduleState();//事件状态(-1 未完成 1完成)
        String executorRemindRepeat=outDto.getExecutorRemindRepeat();     //重复提醒-执行事件表
        String executorRemindRepeatType=outDto.getExecutorRemindRepeatType();     //重复提醒类型-执行事件表（1 每日 2 每月 3每年）
        Integer schIDNew = userDao.selectPKId();
        scheduleDao.createExecutorScheduleAfterCreateGroupSch(userId,schIDNew,executorFinishDate,
        scheduleState,executorRemindDate,executorRemindRepeat,
        executorRemindRepeatType);

    }
}
