package com.manager.master.bean;

/**发送给用户的消息
 * @Author: tzx ;
 * @Date: Created in 17:54 2018/5/3
 */
public class ToUserMessage {
    private String userId;
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
