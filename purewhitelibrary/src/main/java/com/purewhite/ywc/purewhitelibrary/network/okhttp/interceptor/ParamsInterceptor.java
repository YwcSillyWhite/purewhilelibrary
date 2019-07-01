package com.purewhite.ywc.purewhitelibrary.network.okhttp.interceptor;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

import java.io.IOException;
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


    private Map<String,String> map;

    public ParamsInterceptor() {
        map=AppUtils.mapOkhhtp;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (map!=null&&map.size()>0)
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
                for (String key : map.keySet()) {
                    builder.addEncoded(key,map.get(key));
                }
                request=request.newBuilder().post(builder.build()).build();
            }
            else
            {
                //get请求
                HttpUrl.Builder builder = request.url().newBuilder();
                for (String key : map.keySet()) {
                    builder.addQueryParameter(key,map.get(key));
                }
                request=request.newBuilder().url(builder.build()).build();
            }
        }
        return chain.proceed(request);
    }





}
