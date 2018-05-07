package com.master.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * create by wzy on 2018/05/07
 * 请求工具类
 */
public class HttpRequestUtil {

    public static String requestPost(String strUrl, String data) {
        String msg = "";
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式，请示超时
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Accept","*/*");
//            conn.setRequestProperty("Cache-control","no-cacahe");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            //设置运行输入输出
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //Post方式不能缓存,需手动设置为false
            conn.setUseCaches(false);
            //这里可以写一些请求头的东西...

            //获取输出流
//            OutputStream out = conn.getOutputStream();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream()); // 获取输出流
            out.write(data.getBytes()); //将要传递的数据写入数据输出流
            out.flush();    //输出缓存
            out.close();    //关闭数据输出流
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader in = new InputStreamReader(
                        conn.getInputStream()); // 获得读取的内容
                BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
                String inputLine = null;
                String result ="";
                while ((inputLine = buffer.readLine()) != null) {
                    result += inputLine + "\n";
                }
                in.close(); //关闭字符输入流
                return result;
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

}
