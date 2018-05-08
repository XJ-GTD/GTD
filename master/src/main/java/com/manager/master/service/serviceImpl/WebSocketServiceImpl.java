package com.manager.master.service.serviceImpl;

import com.manager.master.dto.ScheduleInDto;
import com.manager.master.dto.ServerMessageDto;
import com.manager.master.dto.ToUserMessageDto;
import com.manager.master.service.IWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tzx ;
 * @Date: Created in 14:25 2018/5/8
 */
@Service
@Transactional
public class WebSocketServiceImpl implements IWebSocketService {

    //注入SimpMessagingTemplate 用于点对点消息发送
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 广播
     * 发送给所有在线用户
     *
     * @param msg
     */
    @Override
    public void sendMsg(ServerMessageDto msg) {
        template.convertAndSend("/topic/getResponse", msg);
    }

    /**
     * 发送给指定用户
     * @param toUserMsg
     * @param schedule
     */
    @Override
    public void send2Users(ToUserMessageDto toUserMsg, ScheduleInDto schedule) {
        toUserMsg.getUsers().forEach(name -> {
            template.convertAndSendToUser(name, "/message", toUserMsg.getMessage());
        });
        if(schedule.getUserMobile() != null && !"".equals(schedule.getUserMobile())){
            if(true){
                //指定用户发送
                List<String> users = new ArrayList<>();
//                users.add("tong");
                users.add(schedule.getUserMobile());

            }

        }

    }
}
