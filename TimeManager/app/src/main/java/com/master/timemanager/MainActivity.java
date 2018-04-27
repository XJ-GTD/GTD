package com.master.timemanager;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private WebView wv;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
//                view.loadUrl(url);
//                return true;
            }
        });
        wv.loadUrl("file:///android_asset/html/home/index-calendar");
    }

    private Button.OnClickListener btn1_clickListener = new Button.OnClickListener(){

        @Override
        public void onClick(View view) {
//            wv.loadUrl("file:///android_asset/index-calendar.html");
//            wv.loadUrl("https://www.baidu.com/");
        }
    };
    private Button.OnClickListener btn2_clickListener = new Button.OnClickListener(){

        @Override
        public void onClick(View view) {
//            wv.loadUrl("https://www.sina.com/");
        }
    };


}
