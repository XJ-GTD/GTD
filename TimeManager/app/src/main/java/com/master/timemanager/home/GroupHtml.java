package com.master.timemanager.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import android.widget.Toast;
import com.master.GlobalVar;
import com.master.json.BaseJson;
import com.master.json.GroupJson;
import com.master.json.UserInfoJson;
import com.master.util.AudioRecordUtil;
import com.master.util.HttpRequestUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wzy on 2018/05/07.
 * 首页群组activity
 */
public class GroupHtml extends Activity {

    /**
     * 添加群组请求 POST
     * @param mobile
     * @param password
     * @return
     */
    public static String addGroup(String mobile, String password) {
        String url = GlobalVar.GROUP_ADD_URL();
        String data = "";
        return HttpRequestUtil.requestPOST(url,data);
    }

    /**
     * 查询群组请求 GET
     * @param userId
     * @return
     */
    public static String findGroup(int userId) {
        String url = GlobalVar.GROUP_FIND_URL() + "/" + userId;
        return HttpRequestUtil.requestGET(url);
    }

    /**
     *
     * @param json
     * @return
     */
    public static BaseJson jsonToGroupString(String json) {
        BaseJson groupBase = new BaseJson();
        List<GroupJson> groupJsonList = new ArrayList<>();
        //解析json
        try {
            JSONObject jsonObject = new JSONObject(json);

            //第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            groupBase.setCode(jsonObject.optString("code"));
            groupBase.setMessage(jsonObject.optString("message"));
            if (!groupBase.getCode().equals("0")) {
                return groupBase;
            }

            //第二层解析
            JSONArray jsonArray = data.optJSONArray("groupInfoList");
            groupBase.setJsonArray(jsonArray.toString());

            //第三层解析
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                GroupJson json1 = new GroupJson();
                if (jsonObject1 != null) {

                    json1.setGroupId(jsonObject1.optString("groupId"));
                    json1.setGroupName(jsonObject1.optString("groupName"));
                    json1.setRoleName(jsonObject1.optString("roleName"));
                    json1.setScheduleName(jsonObject1.optString("scheduleName"));
                    json1.setScheduleCreateDate(jsonObject1.optString("scheduleCreateDate"));

                    groupJsonList.add(json1);
                }
            }

            groupBase.setDataList(groupJsonList);

            } catch (JSONException e) {
            e.printStackTrace();
        }

        return groupBase;
    }

    /*================ ====== 群组相关业务 ======= ==========*/

    public static void initGroup(final WebView webView, final Context context, final UserInfoJson user) {

        webView.loadUrl("file:///android_asset/html/home/index.html");

        webView.addJavascriptInterface(new Object() {
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public String getGroupList() {
                BaseJson groupJson = GroupHtml.jsonToGroupString(GroupHtml.findGroup(user.getUserId()));
                Toast.makeText(context, "展示" , 0).show();
                if (groupJson.getCode().equals("0")){
                    return groupJson.getJsonArray();
                } else {
                    return null;
                }
            }

            @android.webkit.JavascriptInterface
            public void groupScheduleDetail(final String groupId) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        ScheduleHtml.groupSchedule(webView, context, user, groupId);
                    }
                });

            }

            @android.webkit.JavascriptInterface
            public void addSchedule() {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        ScheduleHtml.addSchedule(webView, context, user);
                    }
                });

            }

            @android.webkit.JavascriptInterface
            public void recordStart() {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        AudioRecordUtil recordUtil=AudioRecordUtil.getInstance();
                        recordUtil.startRecordAndFile();
                    }
                });

            }

            @android.webkit.JavascriptInterface
            public void recordEnd() {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        AudioRecordUtil recordUtil=AudioRecordUtil.getInstance();
                        recordUtil.stopRecordAndFile(context);
                    }
                });

            }

        }, "index_group");

    }

    private static void showGroupList(final WebView webView, final BaseJson groupJson) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:groupShow("+ groupJson.getJsonArray() +")");
            }
        });

    }
}
