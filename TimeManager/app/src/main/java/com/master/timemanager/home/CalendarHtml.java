package com.master.timemanager.home;

import android.app.Activity;
import com.master.json.ScheduleJson;
import com.master.util.HttpRequestUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * create by wzy on 2018/05/02.
 * 首页日历activity
 */
public class CalendarHtml extends Activity {

    public static String SCHEDULE_URL = "http://192.168.99.35:8080/gtd/schedul";

    public static String addSchedule(String mobile, String password) {
        String url = SCHEDULE_URL + "/create";
        String data = "{}";
        return HttpRequestUtil.requestPost(url,data);
    }

    public static String findSchedule(int userid) {
        String url = SCHEDULE_URL + "/find";
        String data = "{\"userId\": \"" + userid + "\"}";
        return HttpRequestUtil.requestPost(url,data);
    }

    public static ScheduleJson jsonToUserString(String json) {
        ScheduleJson scheduleJson = new ScheduleJson();
        //解析json
        try {
            JSONObject jsonObject = new JSONObject(json);

            //第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            scheduleJson.setCode(jsonObject.optString("code"));
            scheduleJson.setMessage(jsonObject.optString("message"));

            //第二层解析

            //第二层解析
            JSONObject scheduleinfo = data.optJSONObject("scheduleinfo");
            scheduleJson.setScheduleName(scheduleinfo.optString("scheduleName"));
            scheduleJson.setScheduledId(scheduleinfo.optInt("scheduledId"));
            scheduleJson.setScheduleDetial(scheduleinfo.optString("scheduleDetial"));
            scheduleJson.setScheduleIssuer(scheduleinfo.optInt("scheduleIssuer"));
            scheduleJson.setScheduleCreateDate(scheduleinfo.optString("scheduleCreateDate"));
            scheduleJson.setScheduleStartDate(scheduleinfo.optString("scheduleStartDate"));
            scheduleJson.setScheduleFinshDate(scheduleinfo.optString("scheduleFinshDate"));
            scheduleJson.setScheduledEndDate(scheduleinfo.optString("scheduledEndDate"));
            scheduleJson.setScheduledState(scheduleinfo.optString("scheduledState"));
            scheduleJson.setGroupId(scheduleinfo.optInt("GroupId"));
            scheduleJson.setScheduledId(scheduleinfo.optInt("scheduledId"));
            scheduleJson.setExecutorFinshDate(scheduleinfo.optString("ExecutorFinshDate"));
            scheduleJson.setExecutorRenindDate(scheduleinfo.optString("ExecutorRenindDate"));
            scheduleJson.setExecutorRenindRepeat(scheduleinfo.optString("ExecutorRenindRepeat"));
            scheduleJson.setExecutorRenindRepeatType(scheduleinfo.optString("ExecutorRenindRepeatType"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return scheduleJson;
    }
}
