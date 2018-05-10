package com.master.util;


import android.content.Context;
import android.util.Log;
import com.master.json.ScheduleJson;
import org.java_websocket.WebSocket;
import rx.Subscriber;
import rx.functions.Action1;
import ua.naiksoftware.stomp.LifecycleEvent;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;
import ua.naiksoftware.stomp.client.StompMessage;
import android.app.AlertDialog;

import static android.content.ContentValues.TAG;
/**
 * create by yoyo on 2018/05/07
 * websocket长链接工具类
 */
public class StompUtil {

    private static StompClient mStompClient;

    /**
     * 创建client 实例
     * @param
     * @return
     */
    public static void init(final String userId, final Context context) {
        mStompClient = Stomp.over(WebSocket.class, "ws://192.168.99.25:8080/gtd/webSocketServer/websocket");
        mStompClient.connect();
        mStompClient.lifecycle().subscribe(new Action1<LifecycleEvent>() {
            @Override
            public void call(LifecycleEvent lifecycleEvent) {
                switch (lifecycleEvent.getType()) {
                    case OPENED:
                        Log.d(TAG, "Stomp connection opened");
                        registerStompTopic(context);
                        registerStompByUserId(userId,context);
                        break;
                    case ERROR:
                        Log.e(TAG, "Stomp Error", lifecycleEvent.getException());
                        break;
                    case CLOSED:
                        Log.d(TAG, "Stomp connection closed");
                        break;
                }
            }
        });
    }

    /**
     * 订阅公共信息
     * @param
     * @return
     */
    public static void registerStompTopic(final Context context) {
        mStompClient.topic("/topic/getResponse").subscribe(new Action1<StompMessage>() {
            @Override
            public void call(StompMessage stompMessage) {
                Log.e(TAG, "call: " +stompMessage.getPayload() );
                showPublicDialog(stompMessage.getPayload(),context);
            }
        });
    }

    /**
     * 订阅用户个人日程推送
     * @param
     * @return
     */
    public static void registerStompByUserId(String userId,final Context context) {
        String topicUrl ="/user/"+userId+"/message";
        mStompClient.topic(topicUrl).subscribe(new Action1<StompMessage>() {
            @Override
            public void call(StompMessage stompMessage) {
                Log.e(TAG, "call: " +stompMessage.getPayload() );
                showPublicDialog(stompMessage.getPayload(),context);
            }
        });
    }

    /**
     * 指派给别人日程
     * @param
     * @return
     */
    private static void sendMessage(final ScheduleJson schedule) {
        String data ="{\"userId\":\""+schedule.getUserId()+"\",\"schedulePhoneNum\":\""+schedule.getScheduleIssuer()+"\",\"scheduleName\":\""+schedule.getScheduleName()+"\",\"scheduleFinshDateString\":\""+schedule.getScheduledEndDate()+"\"}";
        mStompClient.send("/app/cheat",data)
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, schedule.getScheduleName()+"发送成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e(TAG, "call: " +schedule.getScheduleName()+ "发送错误");
                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }
                });
    }


    // 列表对话框
    private static void showUserDialog(String[] strs, Context context){
        new AlertDialog.Builder(context)
                .setTitle("日程")
                .setItems(strs, null)
                .setPositiveButton("确定", null)
                .show();
    }

    // 简单消息提示框
    private static void showPublicDialog(String message, Context context){
        new AlertDialog.Builder(context)
                .setTitle("系统消息")
                .setMessage(message)
                .setPositiveButton("确定", null)
                .show();
    }

}
