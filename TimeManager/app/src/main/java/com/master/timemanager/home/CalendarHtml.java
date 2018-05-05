package com.master.timemanager.home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.master.timemanager.R;

/**
 * create by wzy on 2018/05/02.
 * 首页日历activity
 */
public class CalendarHtml extends Activity {

    private WebView wv;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        wv = (WebView) findViewById(R.id.tm_calendar);
        init();
    }

    private void init(){
        //初始化控件

        //获取webSettings
        settings = wv.getSettings();
        //让webView支持JS
        settings.setJavaScriptEnabled(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("http:") || url.startsWith("https:") ) {
                    view.loadUrl(url);
                    return false;
                }else{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            }
        });
        wv.loadUrl("file:///android_asset/html/home/index_calendar.html");
    }



}
