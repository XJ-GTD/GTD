package com.master.timemanager.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import com.master.GlobalVar;
import com.master.json.BaseJson;
import com.master.json.UserInfoJson;
import com.master.timemanager.home.GroupHtml;
import com.master.util.BasicUtil;
import com.master.util.HttpRequestUtil;
import com.master.util.StompUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * create by wzy on 2018/05/03.
 * 登陆连接类
 */
public class LoginHtml extends Activity {

    /**
     * 用户注册 POST
     * @return
     */
    public static String signInByPost(String accountName, String mobile, String password) {
        String url = GlobalVar.USER_SIGNIN_URL();
        String data = "{\"accountName\":\"" + accountName + "\",\"accountMobile\": \"" + mobile + "\", \"accountPassword\":\""+password+"\" }";
        return HttpRequestUtil.requestPOST(url,data);
    }

    /**
     * 登陆请求 POST
     * @param mobile
     * @param password
     * @return
     */
    public static String loginByPost(String mobile, String password) {
        String url = GlobalVar.USER_LOGIN_URL();
        String data = "{\"accountMobile\":\""+mobile+"\",\"accountPassword\":\""+password+"\" }";
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
            if (!userInfoJson.getCode().equals("0")) {
                return userInfoJson;
            }

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

    /* ====================== 登陆相关操作 ======================*/

    private static UserInfoJson user;
    @SuppressLint("HandlerLeak")
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("message");
            user = LoginHtml.jsonToUserString(val);

            Log.i("user","请求结果为--->"+ user);
        }
    };
    private static Runnable mRunnable;

    public static void loginWebView(final WebView webView, WebSettings settings, final Context context) {
        settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            //加载进度获取title
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //网页加载完成
                } else {
                    //网页加载中
                }
            }
        });
        webView.loadUrl("file:///android_asset/html/login/login.html");

        /**
         * 设置可以被js调用的方法逻辑;
         * 添加调用接口,并给接口设置名字;
         */
        webView.addJavascriptInterface(new Object() {
            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void signIn() {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        userSignIn(webView, context);
                    }
                });
            }

            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void toast2(String str) {
                Toast.makeText(context, "输入框中输入的内容是：" + str, 0)
                        .show();
            }

            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void loginSuccess(final String accountName, final String password) {
                mRunnable = new Runnable() {

                    @Override
                    public void run() {
                        try{
                            Looper.prepare();
                            String post = LoginHtml.loginByPost(accountName, password);
                            Message msg = new Message();
                            Bundle data = new Bundle();
                            data.putString("message", post);
                            msg.setData(data);
                            mHandler.handleMessage(msg);
                            loginDeal(webView, context);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(mRunnable).start();
                Toast.makeText(context, "正在登陆中..." , 0)
                        .show();

            }

        }, "login");

    }

    @Override
    protected  void onDestroy() {
        //将线程销毁掉
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    @SuppressLint("WrongConstant")
    private static void loginDeal(final WebView webView, final Context context) {
        if (user != null && user.getCode().equals("0")) {
            webView.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, user.getMessage() , 0).show();
                    StompUtil.init(String.valueOf(user.getUserId()),context);
                    StompUtil.registerStompTopic(context);
                    StompUtil.registerStompByUserId(String.valueOf(user.getUserId()),context);
                    GroupHtml.initGroup(webView, context, user);
                }
            });
        } else {
            webView.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, user.getMessage(), 0).show();
                }
                });
        }
    }

    /* 注册 */
    private static void userSignIn(final WebView webView, final Context context) {
        webView.loadUrl("file:///android_asset/html/login/register.html");

        webView.addJavascriptInterface(new Object() {

            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void signIn(final String accountName, String mobile, String verificationCode, final String password) {
                final String accountNm = accountName;
                final String mb = mobile;
                final String pw = password;
                BaseJson post = BasicUtil.jsonToString(signInByPost(accountNm, mb, pw));
                if (post.getCode().equals("0")) {
                    Toast.makeText(context, post.getMessage()+ "正在登陆中...", 0).show();
                    mRunnable = new Runnable() {

                        @Override
                        public void run() {
                            try{
                                Looper.prepare();
                                String post = LoginHtml.loginByPost(mb, pw);
                                Message msg = new Message();
                                Bundle data = new Bundle();
                                data.putString("message", post);
                                msg.setData(data);
                                mHandler.handleMessage(msg);
                                loginDeal(webView, context);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    new Thread(mRunnable).start();

                } else {
                    Toast.makeText(context, post.getMessage() + "请重新注册", 0).show();
                }
            }

        }, "signIn");
    }
}
