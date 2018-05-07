package com.master.timemanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import com.master.json.UserInfoJson;
import com.master.timemanager.login.LoginHtml;

/**
 * create by wzy on 2018/05/02.
 * main
 */
public class MainActivity extends AppCompatActivity {

    private WebSettings settings;
    private WebView webView;
    private UserInfoJson user;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("message");
            if (!val.equals("")) {
                user = LoginHtml.jsonToUserString(val);
            } else {
                user = null;
            }
            Log.i("user","请求结果为--->"+ user);
        }
    };
    private Runnable mRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将屏幕设置为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.tm_index);

        init();
    }

    private void init() {
        setWebView();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写onKeyDown，当浏览网页，WebView可以后退时执行后退操作。
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setWebView() {
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
            public void toast1() {
                Toast.makeText(MainActivity.this, "提示一下", 0).show();
            }

            @SuppressLint("WrongConstant")
            @android.webkit.JavascriptInterface
            public void toast2(String str) {
                Toast.makeText(MainActivity.this, "输入框中输入的内容是：" + str, 0)
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
                            String post = LoginHtml.LoginByPost(accountName, password);
                            Message msg = new Message();
                            Bundle data = new Bundle();
                            data.putString("message", post);
                            msg.setData(data);
                            mHandler.handleMessage(msg);
                            loginDeal();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(mRunnable).start();
                Toast.makeText(MainActivity.this, "正在登陆中..." , 0)
                        .show();

            }
        }, "login");

    }

    @Override
    protected void onDestroy() {
        //将线程销毁掉
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    @SuppressLint("WrongConstant")
    private void loginDeal() {
        if (user != null && user.getCode().equals("0")) {
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("file:///android_asset/html/home/index.html");
                }
            });
        } else {
            Toast.makeText(MainActivity.this, user.getMessage() , 0)
                    .show();
        }
    }
}
