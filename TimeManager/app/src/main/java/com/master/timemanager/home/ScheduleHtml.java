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
    public static String addSingleSchedule(String scheduleName, String scheduleDetail, String scheduleStartDate, String scheduleEndDate,
                                     String scheduleRemindDate, String scheduleRemindRepeatType, String executor, int scheduleIssuer,
                                     String flagCreateGroup, String flagFocus, String scheduleCreateDate) {
        String url = GlobalVar.SCHEDULE_ADD_URL();
        String data = "{\"scheduleIssuer\":\""+ scheduleIssuer +"\", \"userId\":\""+ executor +"\", \"scheduleName\":\""+ scheduleName +"\", \"scheduleDetail\":\""+ scheduleDetail +"\"," +
                "\"scheduleStartDate\":\""+ scheduleStartDate + "\", \"scheduleEndDate\":\"" + scheduleEndDate + "\", \"scheduleRemindDate\":\""+ scheduleRemindDate + "\"," +
                "\"scheduleRemindRepeatType\":\""+ scheduleRemindRepeatType + "\",\"flagCreateGroup\":\""+ flagCreateGroup + "\"," +
                "\"flagFocus\":\""+ flagFocus + "\",\"scheduleCreateDate\":\""+ scheduleCreateDate + "\"}";
        return HttpRequestUtil.requestPOST(url,data);
    }

    /**
     * 群组内添加日程 POST
     * @return
     */
    public static String addGroupSchedule(String scheduleName, String scheduleDetail, String scheduleStartDate, String scheduleEndDate,
                                           String scheduleRemindDate, String scheduleRemindRepeatType, String executor, int scheduleIssuer,
                                          String flagFocus, String scheduleCreateDate, String groupId) {
        String url = GlobalVar.SCHEDULE_ADD_GROUP_URL();
        String data = "{\"scheduleIssuer\":\""+ scheduleIssuer +"\", \"userId\":\""+ executor +"\", \"scheduleName\":\""+ scheduleName +"\", \"scheduleDetail\":\""+ scheduleDetail +"\"," +
                "\"scheduleStartDate\":\""+ scheduleStartDate + "\", \"scheduleEndDate\":\"" + scheduleEndDate + "\", \"scheduleRemindDate\":\""+ scheduleRemindDate + "\"," +
                "\"scheduleRemindRepeatType\":\""+ scheduleRemindRepeatType + "\",\"flagFocus\":\""+ flagFocus + "\",\"scheduleCreateDate\":\""+ scheduleCreateDate + "\"," +
                "\"groupId\":\"" + groupId + "\"}";
        return HttpRequestUtil.requestPOST(url,data);
    }

    /**
     * 查询日程列表请求 POST
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
    public static String findSchedule(int scheduleId) {
        String url = GlobalVar.SCHEDULE_SINGLE_FIND_URL() + "/" + scheduleId;
        return HttpRequestUtil.requestGET(url);
    }

    /**
     * 查询群组内日程执行人是否有自己
     * @return
     */
    public static String findGroupScheduleMine(int scheduleId, int userId) {
        String url = GlobalVar.SCHEDULE_MINE_GROUP_URL() + "/" + scheduleId + "/" + userId;
        return HttpRequestUtil.requestGET(url);
    }

    /**
     * 接受日程list
     * @param json
     * @return
     */
    public static BaseJson jsonToScheduleString(String json, int flag) {
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
            JSONArray jsonArray = null;
            if (flag == 1) {
                jsonArray = data.optJSONArray("scheduleInfoList");
                scheduleBase.setJsonArray(jsonArray.toString());
            } else if (flag == 2){
                scheduleBase.setJsonArray(data.optString("scheduleInfo"));
                return scheduleBase;
            }

            //第三层解析
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                ScheduleJson json1 = new ScheduleJson();
                if (jsonObject1 != null) {

                    json1.setScheduleName(jsonObject1.optString("scheduleName"));
                    json1.setScheduleId(jsonObject1.optString("scheduleId"));
                    json1.setScheduleDetail(jsonObject1.optString("scheduleDetail"));
                    json1.setScheduleIssuer(jsonObject1.optString("scheduleIssuer"));
                    json1.setUserName(jsonObject1.optString("userName"));
                    json1.setScheduleCreateDate(jsonObject1.optString("scheduleCreateDate"));
                    json1.setScheduleStartDate(jsonObject1.optString("scheduleStartDate"));
                    json1.setScheduleFinishDate(jsonObject1.optString("scheduleFinishDate"));
                    json1.setScheduleEndDate(jsonObject1.optString("scheduleEndDate"));
                    json1.setScheduleState(jsonObject1.optString("scheduleState"));
                    json1.setGroupId(jsonObject1.optString("GroupId"));
                    json1.setExecutorFinishDate(jsonObject1.optString("ExecutorFinishDate"));
                    json1.setExecutorRemindDate(jsonObject1.optString("ExecutorRemindDate"));
                    json1.setExecutorRemindRepeat(jsonObject1.optString("ExecutorRemindRepeat"));
                    json1.setExecutorRemindRepeatType(jsonObject1.optString("ExecutorRemindRepeatType"));

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

    /*================== ==== 页面相关方法 === =================*/

    /* 群组日程列表初始化 */
    public static void groupSchedule(final WebView webView, final Context context, final UserInfoJson user, final String groupId) {
        webView.loadUrl("file:///android_asset/html/schedule/group_schedule.html");


        webView.addJavascriptInterface(new Object() {

            /*返回单个群组的日程列表*/
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public String groupDetail() {
                String dataJson = findGroupSchedule(groupId);
                BaseJson data = jsonToScheduleString(dataJson, 1);
                if (data.getCode().equals("0")) {
                    return data.getJsonArray();
                } else {
                    return null;
                }
            }

            /*返回单个日程详情*/
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void findSingleSchedule(final int scheduleId) {
                String dataJson = findGroupScheduleMine(scheduleId, user.getUserId());
                final BaseJson data = jsonToScheduleString(dataJson, 2);
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (data.getCode().equals("0")) {
                            singleSchedule(webView, context, user, data, groupId);
                        } else {
                            Toast.makeText(context, "小主不用执行此日程~" , 0).show();
                        }
                    }
                });

            }

            /* 群组的日程添加 */
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void groupAddSchedule() {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        addScheduleGroup(webView, context, user, groupId);
                    }
                });
            }
        }, "schedule");
    }

    /* 单个事件详情*/
    private static void singleSchedule(final WebView webView, final Context context, final UserInfoJson user, final BaseJson data, final String groupId) {
        webView.loadUrl("file:///android_asset/html/schedule/scheduleDetail.html");

        webView.addJavascriptInterface(new Object() {

            /*返回单个日程详情*/
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public String singleScheduleDetail() {
                if (data.getCode().equals("0")) {
                    return data.getJsonArray();
                } else {
                    return null;
                }
            }

            /* 日程编辑 */
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void editSchedule() {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        editGroupSchedule(webView, context, user, groupId);
                    }
                });
            }

        }, "singleSchedule");

    }

    /**
     * 添加日程
     * @param webView
     * @param context
     * @param user
     */
    public static void addSchedule(final WebView webView, final Context context, final UserInfoJson user) {
        webView.loadUrl("file:///android_asset/html/schedule/add_schedule.html");

        webView.addJavascriptInterface(new Object() {

            /* 日程创建 */
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void add_Schedule(String scheduleName, String scheduleDetail, String scheduleStartDate, String scheduleEndDate,
                                     String scheduleRemindDate, String scheduleRemindRepeatType, String executor, String flagCreateGroup,
                                     String flagFocus) {
                /* 获取当前时间[创建时间] */
                String scheduleCreateDate = BasicUtil.getNowDate();
                String dataJson = addSingleSchedule(scheduleName, scheduleDetail, scheduleStartDate, scheduleEndDate, scheduleRemindDate,
                            scheduleRemindRepeatType, executor, user.getUserId(), flagCreateGroup, flagFocus, scheduleCreateDate);
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

    /**
     * 群组内添加日程
     * @param webView
     * @param context
     * @param user
     * @param groupId
     */
    public static void addScheduleGroup(final WebView webView, final Context context, final UserInfoJson user, final String groupId) {
        webView.loadUrl("file:///android_asset/html/schedule/add_schedule_Group.html");

        webView.addJavascriptInterface(new Object() {
            /* 群组内日程创建 */
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void add_Schedule_Group(String scheduleName, String scheduleDetail, String scheduleStartDate, String scheduleEndDate,
                    String scheduleRemindDate, String scheduleRemindRepeatType, String executor, String flagFocus) {
                /* 获取当前时间[创建时间] */
                String scheduleCreateDate = BasicUtil.getNowDate();
                String dataJson = addGroupSchedule(scheduleName, scheduleDetail, scheduleStartDate, scheduleEndDate, scheduleRemindDate,
                        scheduleRemindRepeatType, executor, user.getUserId(), flagFocus, scheduleCreateDate, groupId);
                BaseJson data = BasicUtil.jsonToString(dataJson);
                if (data.getCode().equals("0")) {
                    Toast.makeText(context, data.getMessage() , 0).show();
                    goBackGroup(webView);
                } else {
                    //失败应本地存储，预留
                    Toast.makeText(context, data.getMessage() , 0).show();
                }
            }

        }, "group_schedule");
    }

    /* 编辑日程 */
    public static void editGroupSchedule(final WebView webView, final Context context, final UserInfoJson user, final String groupId) {
        webView.loadUrl("file:///android_asset/html/schedule/add_schedule.html");

        webView.addJavascriptInterface(new Object() {
            /* 日程编辑 */
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void editPersonSchedule() {
                webView.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

        }, "personSchedule");
    }

    /* 返回首页 */
    private static void goBackIndex(final WebView webView) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/html/home/index.html");
            }
        });

    }

    /* 返回群组 */
    private static void goBackGroup(final WebView webView) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/html/schedule/group_schedule.html");
            }
        });

    }
}
