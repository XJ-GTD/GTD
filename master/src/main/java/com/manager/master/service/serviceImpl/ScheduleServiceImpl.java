package com.manager.master.service.serviceImpl;

import com.manager.master.bean.UserInfoBean;
import com.manager.master.dao.IUserDao;
import com.manager.master.dao.ScheduleDao;
import com.manager.master.dto.ScheduleInDto;
import com.manager.master.dto.ScheduleOutDto;
import com.manager.master.service.GroupService;
import com.manager.master.service.ScheduleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * create by zy on 2018/05/05.
 * 日程管理
 */

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private ScheduleDao scheduleDao;
    @Resource
    private IUserDao userDao;
    @Autowired
    GroupService groupService;
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
        inDto.setScheduleCreateDate(new Date());// new Date()为获取当前系统时间

        String scheduleName=inDto.getScheduleName();//事件名称
        String scheduleDetial=inDto.getScheduleDetial();//事件详情
        int scheduleIssuer=inDto.getScheduleIssuer();//发布人id
        Date scheduleCreateDate=inDto.getScheduleCreateDate();// 创建时间
        Date scheduleStartDate=inDto.getScheduleStartDate();// 开始时间
        Date scheduleFinshDate=inDto.getScheduleFinshDate();// 完成时间
        Date scheduledEndDate=inDto.getScheduledEndDate();// 截止时间
        String scheduledState=inDto.getScheduledState();//事件状态(-1 未完成 1完成)
        String  groupId=inDto.getGroupId();//组群id
        String scheduledMap=inDto.getScheduledMap();//位置
        String scheduledRenindDate=inDto.getScheduledRenindDate();//提醒时间
        String scheduledRenindRepeat=inDto.getScheduledRenindRepeat();//重复提醒
        String  scheduledRenindRepeatType=inDto.getScheduledRenindRepeatType();//重复提醒类型SCHEDULE_REMIND_REPEAT_TYPE（1 每日 2 每月 3每年）
        scheduleDao.createSchedule(scheduleName,scheduleDetial,scheduleIssuer,
                scheduleCreateDate,scheduleStartDate,scheduleFinshDate,
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
        int scheduledId=inDto.getScheduledId();         //执行事件IDSCHEDULE_ID
        String  userMobile=inDto.getUserId();         //执行人电话（执行人id）
        Date executorFinshDate=inDto.getExecutorFinshDate();     //完成时间-执行事件表
        String scheduledState=inDto.getScheduledState();//事件状态(-1 未完成 1完成)
        Date executorRenindDate=inDto.getExecutorRenindDate();    //提醒时间-执行事件表
        String executorRenindRepeat=inDto.getExecutorRenindRepeat();     //重复提醒-执行事件表
        String executorRenindRepeatType=inDto.getExecutorRenindRepeatType();     //重复提醒类型-执行事件表（1 每日 2 每月 3每年）


        //执行人为空时发布人变为执行人
        userid=inDto.getScheduleIssuer();
        scheduleDao.createExecutorScheduleId(userid,scheduledId,executorFinshDate,scheduledState,executorRenindDate,executorRenindRepeat,executorRenindRepeatType);

        if(userMobile!=null){
            //添加群组
            String groupId=inDto.getGroupId();
            int userId=inDto.getScheduleIssuer();//获取用户id
            int roleId=1;//1群主 2成员 3发布人 4执行人
            String groupName=inDto.getScheduleName();
            groupService.createGroup(groupId,groupName,userId, roleId);
            //分割电话号码
            String[] mobile = userMobile.split(",");
            for (int i = 0; i < mobile.length; i++) {
                //获取用户id
                UserInfoBean userInfo= userDao.findUser(mobile[i]);
                userid=userInfo.getUserId();
                scheduleDao.createExecutorScheduleId(userid,scheduledId,executorFinshDate,scheduledState,executorRenindDate,executorRenindRepeat,executorRenindRepeatType);

                //添加群组
                groupId=inDto.getGroupId();
                userId=inDto.getScheduleIssuer();//获取用户id
                roleId=2;
                groupName=inDto.getScheduleName();
                groupService.createGroup(groupId,groupName,userId, roleId);
            }
        }
    }
}
