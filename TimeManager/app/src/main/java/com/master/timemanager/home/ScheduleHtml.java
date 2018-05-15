package com.master.timemanager.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebView;
import android.widget.Toast;
import com.master.GlobalVar;
import com.master.json.BaseJson;
import com.master.json.ScheduleJson;
import com.master.json.UserInfoJson;
import com.master.util.BasicUtil;
import com.master.util.HttpRequestUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wzy on 2018/05/09.
 * 日程相关业务
 */
public class ScheduleHtml {

    /**
     * 添加日程请求 POST
     * @return
     */
    public static String addSchedule(String scheduleName, String scheduleDetial, String scheduleStartDate, String scheduledEndDate,
                                     String scheduledRenindDate, String scheduledRenindRepeatType, String executor, int scheduleIssuer,
                                     String flagCreateGroup, String flagFocus) {
        String url = GlobalVar.SCHEDULE_ADD_URL();
        String data = "{\"scheduleIssuer\":\""+ scheduleIssuer +"\", \"userId\":\""+ executor +"\", \"scheduleName\":\""+ scheduleName +"\", \"scheduleDetial\":\""+ scheduleDetial +"\"," +
                "\"scheduleStartDate\":\""+ scheduleStartDate + "\", \"scheduledEndDate\":\"" + scheduledEndDate + "\", \"scheduledRenindDate\":\""+ scheduledRenindDate + "\"," +
                "\"scheduledRenindRepeatType\":\""+ scheduledRenindRepeatType + "\",\"flagCreateGroup\":\""+ flagCreateGroup + "\",\"flagFocus\":\""+ flagFocus + "\"}";
        return HttpRequestUtil.requestPOST(url,data);
    }

    /**
     * 查询日程请求 POST
     * @param userid
     * @return
     */
    public static String findAllSchedule(int userid) {
        String url = GlobalVar.SCHEDULE_FIND_URL();
        String data = "{\"userId\": \"" + userid + "\"}";
        return HttpRequestUtil.requestPOST(url,data);
    }

    /**
     * 查询单群组日程请求 GET
     * @param groupId
     * @return
     */
    public static String findGroupSchedule(String groupId) {
        String url = GlobalVar.SCHEDULE_GROUP_URL() + "/" + groupId;
        return HttpRequestUtil.requestGET(url);
    }

    /**
     * 查询单日程请求 GET
     * @param scheduleId
     * @return
     */
    public static String findSchedule(String scheduleId) {
        String url = GlobalVar.SCHEDULE_SINGLE_FIND_URL() + "/" + scheduleId;
        return HttpRequestUtil.requestGET(url);
    }

    /**
     * 接受日程list
     * @param json
     * @return
     */
    public static BaseJson jsonToScheduleString(String json) {
        BaseJson scheduleBase = new BaseJson();
        List<ScheduleJson> scheduleList = new ArrayList<>();
        //解析json
        try {
            JSONObject jsonObject = new JSONObject(json);

            //复杂json解析方法
            //第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            scheduleBase.setCode(jsonObject.optString("code"));
            scheduleBase.setMessage(jsonObject.optString("message"));
            if (!scheduleBase.getCode().equals("0")) {
                return scheduleBase;
            }

            //第二层解析
            JSONArray jsonArray = data.optJSONArray("scheduleInfoList");
            scheduleBase.setJsonArray(jsonArray.toString());

            //第三层解析
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                ScheduleJson json1 = new ScheduleJson();
                if (jsonObject1 != null) {

                    json1.setScheduleName(jsonObject1.optString("scheduleName"));
                    json1.setScheduleId(jsonObject1.optString("scheduleId"));
                    json1.setScheduleDetial(jsonObject1.optString("scheduleDetail"));
                    json1.setScheduleIssuer(jsonObject1.optString("scheduleIssuer"));
                    json1.setUserName(jsonObject1.optString("userName"));
                    json1.setScheduleCreateDate(jsonObject1.optString("scheduleCreateDate"));
                    json1.setScheduleStartDate(jsonObject1.optString("scheduleStartDate"));
                    json1.setScheduleFinshDate(jsonObject1.optString("scheduleFinshDate"));
                    json1.setScheduledEndDate(jsonObject1.optString("scheduleEndDate"));
                    json1.setScheduledState(jsonObject1.optString("scheduledState"));
                    json1.setGroupId(jsonObject1.optString("GroupId"));
                    json1.setExecutorFinshDate(jsonObject1.optString("ExecutorFinshDate"));
                    json1.setExecutorRenindDate(jsonObject1.optString("ExecutorRenindDate"));
                    json1.setExecutorRenindRepeat(jsonObject1.optString("ExecutorRenindRepeat"));
                    json1.setExecutorRenindRepeatType(jsonObject1.optString("ExecutorRenindRepeatType"));

                    scheduleList.add(json1);
                }

            }
            if (scheduleList != null && scheduleList.size() != 0) {
                scheduleBase.setDataList(scheduleList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return scheduleBase;
    }

    /*================== ======= =================*/

    public static void groupSchedule(final WebView webView, final Context context, final UserInfoJson user, final String groupId) {
        webView.loadUrl("file:///android_asset/html/schedule/group_schedule.html");


        webView.addJavascriptInterface(new Object() {
            /*返回单个群组的日程列表*/
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public String groupDetail() {
                String dataJson = findGroupSchedule(groupId);
                BaseJson data = jsonToScheduleString(dataJson);
                if (data.getCode().equals("0")) {
                    return data.getJsonArray();
                } else {
                    return null;
                }
            }

            /*返回单个日程详情*/
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void findSingleSchedule(final String scheduleId) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        singleSchedule(webView, context, user, scheduleId);
                    }
                });

            }
        }, "schedule");
    }

    /* 单个事件详情*/
    private static void singleSchedule(final WebView webView, final Context context, final UserInfoJson user, final String scheduleId) {
        webView.loadUrl("file:///android_asset/html/schedule/scheduleDetail.html");

        webView.addJavascriptInterface(new Object() {

            /*返回单个日程详情*/
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public String singleScheduleDetail() {
                String dataJson = findSchedule(scheduleId);
                BaseJson data = BasicUtil.jsonToString(dataJson);
                if (data.getCode().equals("0")) {
                    return data.getJsonArray();
                } else {
                    return null;
                }

            }
        }, "singleSchedule");

    }

    public static void addSchedule(final WebView webView, final Context context, final UserInfoJson user) {
        webView.loadUrl("file:///android_asset/html/schedule/add_schedule.html");

        webView.addJavascriptInterface(new Object() {
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void add_Schedule(String scheduleName, String scheduleDetial, String scheduleStartDate, String scheduledEndDate,
                                     String scheduledRenindDate, String scheduledRenindRepeatType, String executor, String flagCreateGroup,
                                     String flagFocus) {
                String dataJson = addSchedule(scheduleName, scheduleDetial, scheduleStartDate, scheduledEndDate, scheduledRenindDate, scheduledRenindRepeatType, executor, user.getUserId(), flagCreateGroup, flagFocus);
                BaseJson data = BasicUtil.jsonToString(dataJson);
                if (data.getCode().equals("0")) {
                    Toast.makeText(context, data.getMessage() , 0).show();
                    goBackIndex(webView);
                } else {
                    //失败应本地存储，预留
                    Toast.makeText(context, data.getMessage() , 0).show();
                }
            }
        }, "index_schedule");
    }

    private static void goBackIndex(final WebView webView) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/html/home/index.html");
            }
        });

    }
}
