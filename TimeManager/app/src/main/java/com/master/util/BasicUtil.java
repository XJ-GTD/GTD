package com.master.util;

import com.master.json.BaseJson;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * create by wzy on 2018/05/07.
 * 基础工具类
 */
public class BasicUtil {

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

}
