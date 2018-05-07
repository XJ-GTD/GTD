package com.master.timemanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

/**
 * create by wzy on 2018/05/02.
 * 首页群组activity
 */
public class MainActivity extends AppCompatActivity {

    private WebSettings settings;
    private Button btnAddCalendar;
    private Button btnCalendar;
    private Button btnGroup;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将屏幕设置为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.tm_index);
//        btnAddCalendar = (Button) findViewById(R.id.btnAddCalendar);
//        btnGroup = (Button) findViewById(R.id.btnGroup);
//        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        init();
    }

    private void init() {

        setWebView();
//        btnAddCalendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //调用js方法，要以javascript:开头 方法名注意要加括号
//                webView.loadUrl("javascript:addSchedule()");
//            }
//        });
//        btnGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //调用js方法，要以javascript:开头 方法名注意要加括号
//                webView.loadUrl("javascript:calendarClick()");
//            }
//        });
//        btnCalendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //调用js方法，要以javascript:开头 方法名注意要加括号
//                webView.loadUrl("javascript:calendarClick()");
//            }
//        });
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
        }, "test");

        webView.addJavascriptInterface(new Object() {
            @android.webkit.JavascriptInterface
            public void loginSuccess(String accountName, String password) {
//                LoginHtml.LoginByPost(accountName, password);
            }
        }, "login");
    }


}
