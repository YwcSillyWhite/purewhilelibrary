package com.purewhite.ywc.purewhitelibrary.network.okhttp.interceptor;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
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
            if (request.method().equals("GET"))
            {
                //get请求
                HttpUrl.Builder builder = request.url().newBuilder();
                for (int i = 0; i < key.length; i++) {
                    builder.addQueryParameter(key[i],value[i]);
                }
                request=request.newBuilder().url(builder.build()).build();
            }
            else if (request.method().equals("POST"))
            {
                RequestBody body = request.body();
                if (body instanceof FormBody)
                {
                    FormBody.Builder builder = new FormBody.Builder();
                    FormBody formBody = (FormBody) body;
                    for (int i = 0; i < formBody.size(); i++) {
                        builder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                    }
                    for (int i = 0; i < key.length; i++) {
                        builder.addEncoded(key[i],value[i]);
                    }
                    request=request.newBuilder().post(builder.build()).build();
                }
            }

        }
        return chain.proceed(request);
    }





}
