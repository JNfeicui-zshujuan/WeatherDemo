package com.example.zhengshujuan.weatherdemo.Energy;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhengshujuan on 2016/5/23.
 */
public class GetImageData {
    //定义一个获取网络图片数据的方法
    public static byte[] getImageData(String path) throws IOException {
        URL url=new URL(path);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        //设置超时时间
        connection.setConnectTimeout(5000);
        //设置请求类型为get类型
        connection.setRequestMethod("GET");
        //判断请求是否成功
//        if (connection.getResponseCode()!=200){
//
//        }
        InputStream inputStream=connection.getInputStream();
        byte[] bt=StreamTools.read(inputStream);
        inputStream.close();
        return bt;
    }
}
