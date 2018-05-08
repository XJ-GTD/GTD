package com.master.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * create by wzy on 2018/05/07
 * 请求工具类
 */
public class HttpRequestUtil {

    /**
     * GET 请求
     * @param strUrl
     * @return
     */
    public static String requestGET(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式，请示超时
            conn.setRequestMethod("GET");
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.i("HttpRequestUtil", "get请求成功");
                InputStreamReader in = new InputStreamReader(conn.getInputStream()); // 获得读取的内容
                BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
                String inputLine = null;
                StringBuilder result = new StringBuilder();
                while ((inputLine = buffer.readLine()) != null) {
                    result.append(inputLine).append("\n");
                }
                in.close(); //关闭字符输入流
                return URLDecoder.decode(result.toString(), "UTF-8");
            } else {
                Log.i("HttpRequestUtil", "get请求失败");
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * POST 请求
     * @param strUrl
     * @param data
     * @return
     */
    public static String requestPOST(String strUrl, String data) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式，请示超时
            conn.setRequestMethod("POST");
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
                Log.i("HttpRequestUtil", "post请求成功");
                InputStreamReader in = new InputStreamReader(conn.getInputStream()); // 获得读取的内容
                BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
                String inputLine = null;
                StringBuilder result = new StringBuilder();
                while ((inputLine = buffer.readLine()) != null) {
                    result.append(inputLine).append("\n");
                }
                in.close(); //关闭字符输入流
                return URLDecoder.decode(result.toString(), "UTF-8");
            } else {
                Log.i("HttpRequestUtil", "post请求失败");
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
