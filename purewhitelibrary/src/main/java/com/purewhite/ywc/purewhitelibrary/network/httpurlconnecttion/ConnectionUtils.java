package com.purewhite.ywc.purewhitelibrary.network.httpurlconnecttion;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author yuwenchao
 */
public class ConnectionUtils {


    public static String request(String urlcontent) throws Exception
    {
        if (!TextUtils.isEmpty(urlcontent))
        {
            URL url = new URL(urlcontent);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //请求超时间  默认单位毫秒
            urlConnection.setConnectTimeout(10*1000);
            //请求方式
            urlConnection.setRequestMethod("GRT");
            //接收到的数据json数据
            urlConnection.setRequestProperty("Content-Type","application/json");
            //接收到的数据格式是utf-8
            urlConnection.setRequestProperty("Charset","UTF-8");
            //客户端可以处理的字符集类型是utf-8
            urlConnection.setRequestProperty("Accept-Charset","UTF-8");
            //发启链接
            urlConnection.connect();
            //获取请求状态
            int responseCode = urlConnection.getResponseCode();
            //200是拿到数据
            if (responseCode==HttpURLConnection.HTTP_OK)
            {
                InputStream inputStream = urlConnection.getInputStream();
                String respon = streamToString(inputStream);
                return respon;
            }
            else
            {
                //错误的时候返回错误信息
                String responseMessage = urlConnection.getResponseMessage();
                LogUtils.error("httpUrlConnecttion",
                        !TextUtils.isEmpty(responseMessage)
                                ?responseMessage:"请求错误");
            }
        }
        return null;
    }


    private static String streamToString(InputStream inputStream) throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes))!=-1)
        {
            baos.write(bytes,0,len);
        }
        baos.close();
        inputStream.close();
        return new String(baos.toByteArray());
    }
}
