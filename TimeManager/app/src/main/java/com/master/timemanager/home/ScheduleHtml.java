package com.master.timemanager.home;

import android.content.Context;
import android.webkit.WebView;
import com.master.json.BaseJson;
import com.master.json.ScheduleJson;
import com.master.json.UserInfoJson;
import com.master.util.HttpRequestUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wzy on 2018/05/09.
 * 首页添加相关业务
 */
public class ScheduleHtml {

    //添加日程相关日程接口路径
    public static String SCHEDULE_URL = "http://192.168.99.35:8080/gtd/schedul";

    public static String addSchedule() {
        String url = SCHEDULE_URL + "/create/";
        String data = "";
//        "{\"mobile\":\""+mobile+"\",\"password\":\""+password+"\" }";
        return HttpRequestUtil.requestPOST(url,data);
    }

    public static String findGroup() {
        String url = SCHEDULE_URL + "/find";
        return HttpRequestUtil.requestGET(url);
    }

    /**
     *
     * @param json
     * @return
     */
    public static BaseJson jsonToUserString(String json) {
        BaseJson addScheduleBase = new BaseJson();
        List<ScheduleJson> scheduleJsonList = new ArrayList<>();
        //解析json
        try {
            JSONObject jsonObject = new JSONObject(json);

            //第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            addScheduleBase.setCode(jsonObject.optString("code"));
            addScheduleBase.setMessage(jsonObject.optString("message"));
            if (!addScheduleBase.getCode().equals("0")) {
                return addScheduleBase;
            }

            //第二层解析
            JSONArray jsonArray = data.optJSONArray("groupInfoList");

            //第三层解析
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                ScheduleJson json1 = new ScheduleJson();
                if (jsonObject1 != null) {

//                    json1.setGroupId(jsonObject1.optString("groupId"));
//                    json1.setGroupName(jsonObject1.optString("groupName"));
//                    json1.setRoleName(jsonObject1.optString("roleName"));

                    scheduleJsonList.add(json1);
                }
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return addScheduleBase;
    }

    /*================== ======= =================*/

    public static void initSchedule(final WebView webView, final Context context, final UserInfoJson user) {
        webView.loadUrl("file:///android_asset/html/schedule/add_schedule.html");
    }

}
