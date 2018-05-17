package com.master.util;

import com.master.json.BaseJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create by wzy on 2018/05/07.
 * 基础工具类
 */
public class BasicUtil {

    /**
     * 获取返回信息 JSON
     * @param json
     * @return
     */
    public static BaseJson jsonToString(String json) {
        BaseJson baseJson = new BaseJson();
        //解析json
        try {
            JSONObject jsonObject = new JSONObject(json);

            //复杂json解析方法
            baseJson.setCode(jsonObject.optString("code"));
            baseJson.setMessage(jsonObject.optString("message"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return baseJson;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowDate() {
        String dateString = null;
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateString = sdf.format(currentTime);

        return dateString;
    }
}
