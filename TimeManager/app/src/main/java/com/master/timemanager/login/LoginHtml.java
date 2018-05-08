package com.master.timemanager.login;

import android.app.Activity;
import com.master.json.UserInfoJson;
import com.master.util.HttpRequestUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * create by wzy on 2018/05/03.
 * 登陆连接类
 */
public class LoginHtml extends Activity {

    //登陆接口路径
    public static String LOGIN_URL = "http://192.168.99.35:8080/gtd/user";

    /**
     * 登陆请求 POST
     * @param mobile
     * @param password
     * @return
     */
    public static String loginByPost(String mobile, String password) {
        String url = LOGIN_URL + "/login";
        String data = "{\"mobile\":\""+mobile+"\",\"password\":\""+password+"\" }";
        return HttpRequestUtil.requestPOST(url,data);
    }


    /**
     * 登陆获取用户信息json处理
     * @param json
     * @return
     */
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
            JSONObject userinfo = data.optJSONObject("userinfo");
            userInfoJson.setAccountId(userinfo.optInt("accountId"));
            userInfoJson.setAccountMobile(userinfo.optString("accountMobile"));
            userInfoJson.setAccountName(userinfo.optString("accountName"));
            userInfoJson.setUserId(userinfo.optInt("userId"));




        } catch (JSONException e) {
            e.printStackTrace();
        }

        //显示数据
//        tv_native_orignal.setText(json);
//        tv_native_last.setText(shopInfo.toString());

        return userInfoJson;
    }

}
