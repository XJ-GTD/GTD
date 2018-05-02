package com.master.timemanager.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.master.timemanager.R;

public class CalendarHtml extends Activity {

    private Button btn1;
    private Button btn2;
    private WebView wv;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        init();
    }

    private void init(){
        //初始化控件
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(btn1_clickListener);

        btn2 = (Button) findViewById(R.id.btn2);

        btn2.setOnClickListener(btn2_clickListener);

        wv = (WebView) findViewById(R.id.wv);
        //获取webSettings
        settings = wv.getSettings();
        //让webView支持JS
        settings.setJavaScriptEnabled(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wv.setWebViewClient(new WebViewClient(){});
        wv.loadUrl("file:///android_asset/html/home/index-calendar.html");
    }

    private Button.OnClickListener btn1_clickListener = new Button.OnClickListener(){

        @Override
        public void onClick(View view) {
        }
    };
    private Button.OnClickListener btn2_clickListener = new Button.OnClickListener(){

        @Override
        public void onClick(View view) {
        }
    };

}
