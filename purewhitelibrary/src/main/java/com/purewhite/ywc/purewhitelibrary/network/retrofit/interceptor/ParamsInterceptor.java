package com.purewhite.ywc.purewhitelibrary.network.retrofit.interceptor;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author yuwenchao
 * 添加公共参数
 */
public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody body = request.body();
        if (body instanceof FormBody)
        {
            //post请求
            FormBody formBody = (FormBody) body;
            FormBody.Builder builder = new FormBody.Builder();
            for (int i = 0; i < formBody.size(); i++) {
                builder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
            }
            builder.addEncoded("apikey", "pureWhite");
            request=request.newBuilder().post(builder.build()).build();
        }
        else
        {
            //get请求
            HttpUrl.Builder builder = request.url().newBuilder();
            builder.addQueryParameter("apikey","pureWhite");
            request=request.newBuilder().url(builder.build()).build();
        }
        return chain.proceed(request);
    }
}
