package com.master.timemanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebSettings settings;
    private Button btnCalendar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将屏幕设置为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.wv_webview);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        init();
    }

    private void init() {

        setWebView();
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用js方法，要以javascript:开头 方法名注意要加括号
                webView.loadUrl("javascript:calendarClick()");
            }
        });

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
        });
        webView.loadUrl("file:///android_asset/html/home/index.html");

        /**
         * 设置可以被js调用的方法逻辑;
         * 添加调用接口,并给接口设置名字;
         */
        webView.addJavascriptInterface(new Object() {

            @android.webkit.JavascriptInterface
            public void toast1() {
                Toast.makeText(MainActivity.this, "提示一下", 0).show();
            }

            @android.webkit.JavascriptInterface
            public void toast2(String str) {
                Toast.makeText(MainActivity.this, "输入框中输入的内容是：" + str, 0)
                        .show();
            }
        }, "test");
    }


}
