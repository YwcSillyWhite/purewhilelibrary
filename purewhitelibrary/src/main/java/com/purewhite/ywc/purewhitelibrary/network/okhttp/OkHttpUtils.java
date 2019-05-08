package com.purewhite.ywc.purewhitelibrary.network.okhttp;


import com.google.gson.Gson;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.build.GetBuilder;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.build.PostBuilder;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.executor.OkThreadSave;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author yuwenchao
 */
public class OkHttpUtils {
    private static OkHttpUtils okhttpUtils;
    private OkHttpClient okHttpClient;
    private OkThreadSave okThreadSave;
    private Gson gson;

    public static OkHttpUtils newInstance() {
        if (okhttpUtils==null)
        {
            synchronized (OkHttpUtils.class)
            {
                if (okhttpUtils==null)
                {
                    okhttpUtils=new OkHttpUtils();
                }
            }
        }
        return okhttpUtils;
    }

    public OkHttpUtils() {
        if (okHttpClient==null)
        {
            okHttpClient=OkManager.getOkHttp();
        }
    }

    public void cancleTag(Object tag)
    {
        for (Call call:okHttpClient.dispatcher().queuedCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
        for (Call call:okHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
            {
                call.cancel();
            }
        }
    }


    /**
     * 发起请求
     * @param okCallBack
     */
    public void enqueue(OkCallBack okCallBack,Request request)
    {
        if (okCallBack==null)
            return;
        final OkCallBack finalOkCallBack=okCallBack;
        okCallBack.onBefore();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailResultCallback(e,finalOkCallBack);
            }

            @Override
            public void onResponse(Call call, Response response){
                try
                {
                    if (call.isCanceled())
                    {
                        sendFailResultCallback(new Exception("Canceled"), finalOkCallBack);
                    }
                    else
                    {
                        if (response.code()==200)
                        {
                            String responseBody = response.body().string();
                            if (finalOkCallBack.mType==null||
                                    finalOkCallBack.mType==String.class)
                            {
                                sendSuccessResultCallback(responseBody,finalOkCallBack);
                            }
                            else
                            {
                                if (gson==null)
                                    gson=new Gson();
                                Object object= gson.fromJson(responseBody, finalOkCallBack.mType);
                                sendSuccessResultCallback(object,finalOkCallBack);
                            }
                        }
                        else
                        {
                            sendFailResultCallback(new Exception("response.code = 200"), finalOkCallBack);
                        }
                    }
                }
                catch (Exception e)
                {
                    sendFailResultCallback(e, finalOkCallBack);

                }
                finally {
                    if (response.body()!=null)
                    {
                        response.body().close();
                    }
                }
            }
        });
    }


    /**
     * 请求失败
     * @param exception
     * @param okCallBack
     */
    private void sendFailResultCallback(final Exception exception, final OkCallBack okCallBack) {
        if (okCallBack==null)
            return;
        if (okThreadSave==null)
            okThreadSave=new OkThreadSave();
        okThreadSave.executor(new Runnable() {
            @Override
            public void run() {
                okCallBack.onFail(exception);
                okCallBack.onAfter();
            }
        });
    }

    /**
     * 请求成功
     * @param object
     * @param okCallBack
     */
    private void sendSuccessResultCallback(final Object object, final OkCallBack okCallBack)
    {
        if (okCallBack==null)
            return;
        if (okThreadSave==null)
            okThreadSave=new OkThreadSave();
        okThreadSave.executor(new Runnable() {
            @Override
            public void run() {
                //在处理数据中出现错误，不会导致app崩溃
                try
                {
                    okCallBack.onSuccess(object);
                    okCallBack.onAfter();
                }
                catch (Exception e)
                {
                    okCallBack.onFail(e);
                }


            }
        });
    }





    public static GetBuilder get()
    {
        return new GetBuilder();
    }

    public static GetBuilder get(String url, Object objectTag, Map<String, String> paramsRequest)
    {
        return new GetBuilder(url,objectTag,paramsRequest);
    }

    public static PostBuilder post()
    {
        return new PostBuilder();
    }



    public static PostBuilder post(String url, Object objectTag, Map<String, String> paramsRequest)
    {
        return new PostBuilder(url,objectTag,paramsRequest);
    }



}
