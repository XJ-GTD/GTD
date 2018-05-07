package com.manager.master.controller;

import com.manager.master.dto.ClientMessageDto;
import com.manager.master.dto.ServerMessageDto;
import com.manager.master.dto.ToUserMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @Author: tzx ;
 * @Date: Created in 18:53 2018/5/3
 */
@Controller
public class WebSocketController {


    //注入SimpMessagingTemplate 用于点对点消息发送
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    @SendTo("/topic/getResponse")//SendTo 发送至 Broker 下的指定订阅路径
    public ServerMessageDto sendTo(ClientMessageDto clientMessage){
        //方法用于广播测试
        System.out.println("clientMessage.getName() = " + clientMessage.getName());
        return new ServerMessageDto("Welcome , "+clientMessage.getName()+" !");
    }

    // 发送的订阅路径为/user/{userId}/message
    @MessageMapping("/cheat")
    public void cheatTo(ToUserMessageDto toUserMessage){
        //方法用于点对点测试
        System.out.println("toUserMessage.getMessage() = " + toUserMessage.getMessage());
        System.out.println("toUserMessage.getUserId() = " + toUserMessage.getUserId());
        messagingTemplate.convertAndSendToUser(toUserMessage.getUserId(),"/message",toUserMessage.getMessage());
    }
}
