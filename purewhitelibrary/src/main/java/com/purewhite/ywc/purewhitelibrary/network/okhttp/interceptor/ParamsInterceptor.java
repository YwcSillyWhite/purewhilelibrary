package com.purewhite.ywc.purewhitelibrary.network.okhttp.interceptor;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author yuwenchao
 * 添加公共参数
 *
 */
public class ParamsInterceptor implements Interceptor {



    protected String key[]={"apikey"};
    protected String value[]={"apikey"};


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (key!=null&&value!=null&&key.length>0&&key.length==value.length)
        {
            RequestBody body = request.body();
            if (body instanceof FormBody)
            {
                //post请求
                FormBody formBody = (FormBody) body;
                FormBody.Builder builder = new FormBody.Builder();
                for (int i = 0; i < formBody.size(); i++) {
                    builder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
                for (int i = 0; i < key.length; i++) {
                    builder.addEncoded(key[i],value[i]);
                }
                request=request.newBuilder().post(builder.build()).build();
            }
            else
            {
                //get请求
                HttpUrl.Builder builder = request.url().newBuilder();
                for (int i = 0; i < key.length; i++) {
                    builder.addQueryParameter(key[i],value[i]);
                }
                request=request.newBuilder().url(builder.build()).build();
            }
        }
        return chain.proceed(request);
    }





}
