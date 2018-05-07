package com.master.timemanager.login;

import com.master.json.UserInfoJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * create by wzy on 2018/05/03.
 * 登陆连接类
 */
public class LoginHtml {

    public static String LOGIN_URL = "http://192.168.99.35:8080/gtd/user/login";

    public static String LoginByPost(String mobile, String password) {
        String msg = "";
        try {
            URL url = new URL(LOGIN_URL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式，请示超时
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Accept","*/*");
//            conn.setRequestProperty("Cache-control","no-cacahe");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            //设置运行输入输出
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //Post方式不能缓存,需手动设置为false
            conn.setUseCaches(false);
            //我们请求的数据:
            String data2 = "{\"mobile\":\""+mobile+"\",\"password\":\""+password+"\" }";
            //这里可以写一些请求头的东西...
            //获取输出流
//            OutputStream out = conn.getOutputStream();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream()); // 获取输出流
            out.write(data2.getBytes()); //将要传递的数据写入数据输出流
            out.flush();    //输出缓存
            out.close();    //关闭数据输出流
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader in = new InputStreamReader(
                        conn.getInputStream()); // 获得读取的内容
                BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
                String inputLine = null;
                String result ="";
                while ((inputLine = buffer.readLine()) != null) {
                    result += inputLine + "\n";
                }
                in.close(); //关闭字符输入流
                return result;
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public static UserInfoJson jsonToJavaString(String json) {
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
