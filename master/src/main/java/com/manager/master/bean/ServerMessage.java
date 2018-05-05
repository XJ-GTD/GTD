package com.manager.master.bean;

/**服务器端消息
 * @Author: tzx ;
 * @Date: Created in 17:52 2018/5/3
 */
public class ServerMessage {
    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ServerMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
