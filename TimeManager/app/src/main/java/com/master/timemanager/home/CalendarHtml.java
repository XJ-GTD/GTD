package com.master.timemanager.home;

import android.app.Activity;
import com.master.json.UserInfoJson;
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
        String data = "";
        return HttpRequestUtil.requestPost(url,data);
    }

    public static String findSchedule(String mobile, String password) {
        String url = SCHEDULE_URL + "/find";
        String data = "";
        return HttpRequestUtil.requestPost(url,data);
    }

    public static UserInfoJson jsonToUserString(String json) {
        UserInfoJson userInfoJson = new UserInfoJson();
        //解析json
        try {
            JSONObject jsonObject = new JSONObject(json);

            //第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            userInfoJson.setCode(jsonObject.optString("code"));
            userInfoJson.setMessage(jsonObject.optString("message"));

            //第二层解析
            JSONObject userinfo = data.optJSONObject("");
//            userInfoJson.setAccountId(userinfo.optInt("accountId"));
//            userInfoJson.setAccountMobile(userinfo.optString("accountMobile"));
//            userInfoJson.setAccountName(userinfo.optString("accountName"));
//            userInfoJson.setUserId(userinfo.optInt("userId"));




        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userInfoJson;
    }
}
