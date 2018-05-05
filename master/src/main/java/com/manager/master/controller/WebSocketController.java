package com.manager.master.controller;

import com.manager.master.bean.ClientMessage;
import com.manager.master.bean.ServerMessage;
import com.manager.master.bean.ToUserMessage;
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
    public ServerMessage sendTo(ClientMessage clientMessage){
        //方法用于广播测试
        System.out.println("clientMessage.getName() = " + clientMessage.getName());
        return new ServerMessage("Welcome , "+clientMessage.getName()+" !");
    }

    @MessageMapping("/cheat")
    // 发送的订阅路径为/user/{userId}/message
    // /user/路径是默认的一个，如果想要改变，必须在config 中setUserDestinationPrefix
    public void cheatTo(ToUserMessage toUserMessage){
        //方法用于点对点测试
        System.out.println("toUserMessage.getMessage() = " + toUserMessage.getMessage());
        System.out.println("toUserMessage.getUserId() = " + toUserMessage.getUserId());                                        messagingTemplate.convertAndSendToUser(toUserMessage.getUserId(),"/message",toUserMessage.getMessage());
    }
}
