package com.master.timemanager.home;

import android.app.Activity;
import com.master.json.UserInfoJson;
import com.master.util.HttpRequestUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * create by wzy on 2018/05/07.
 * 首页群组activity
 */
public class GroupHtml extends Activity {

    //群组相关日程接口路径
    public static String GROUP_URL = "http://192.168.99.35:8080/gtd/group";

    /**
     * 添加群组请求 POST
     * @param mobile
     * @param password
     * @return
     */
    public static String addGroup(String mobile, String password) {
        String url = GROUP_URL + "/";
        String data = "";
        return HttpRequestUtil.requestPOST(url,data);
    }

    /**
     * 查询群组请求 POST
     * @param mobile
     * @param password
     * @return
     */
    public static String findGroup(String mobile, String password) {
        String url = GROUP_URL + "/";
        String data = "";
        return HttpRequestUtil.requestPOST(url,data);
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
