package com.master.timemanager.javascriptClass;

import android.content.Context;
import android.os.Handler;

/**
 * create by wzy on 2018/05/09
 * js调取封装方法
 */
public class JSInterface {

    private Handler mHandler;
    private Runnable mRunnable;
    private Context context;

    public JSInterface(Handler handler, Runnable runnable, Context ct ) {
        this.mHandler = handler;
        this.mRunnable = runnable;
        this.context = ct;
    }



}
