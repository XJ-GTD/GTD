package com.master.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

public class HtmlFileUtil {

    public static Uri changeUri(String filePath, Context context){
        File html = new File(filePath);
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.timemanager.fileprovider", html);
        } else {
            uri = Uri.fromFile(html);
        }
        return uri;
    }
}
