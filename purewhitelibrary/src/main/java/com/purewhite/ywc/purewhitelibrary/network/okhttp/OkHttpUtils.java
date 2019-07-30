package com.purewhite.ywc.purewhitelibrary.network.okhttp;


import com.google.gson.Gson;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.build.GetBuilder;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.build.PostBuilder;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.executor.OkThreadSave;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;

/**
 * @author yuwenchao
 */
public class OkHttpUtils {
    private static OkHttpUtils okhttpUtils;
    private Map<String,OkHttpClient> map=new HashMap<>();
    private OkhttpBuilder okhttpBuilder=new OkhttpBuilder();

    public void setOkhttpBuilder(OkhttpBuilder okhttpBuilder) {
        if (okhttpBuilder!=null)
        {
            this.okhttpBuilder = okhttpBuilder;
        }
    }

    private Gson gson=new Gson();
    private OkThreadSave okThreadSave=new OkThreadSave();


    private OkHttpUtils() {
        map.put(OkhttpBuilder.defaultOKhttp,okhttpBuilder.obtianClient(OkhttpBuilder.defaultOKhttp));
    }


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



    public void cancleTag(Object tag)
    {
        cancleTag(OkhttpBuilder.defaultOKhttp,tag);
    }

    public void cancleTag(String key,Object tag)
    {
        OkHttpClient okHttpClient = map.get(key);
        if (okHttpClient!=null)
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
    }


    public void enqueue(Request request,OkCallBack okCallBack)
    {
        enqueue(OkhttpBuilder.defaultOKhttp,request,okCallBack);
    }

    public void enqueue(String key,Request request,final OkCallBack okCallBack)
    {
        OkHttpClient okHttpClient = map.get(key);
        if (okHttpClient==null)
        {
            okHttpClient=okhttpBuilder.obtianClient(key);
        }
        final OkHttpClient ok=okHttpClient;
        if (ok!=null)
        {
            if (okCallBack!=null)
            {
                okCallBack.onBefore();
            }
            ok.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    if (okCallBack!=null)
                    {
                        sendFailResultCallback(e,okCallBack);
                    }
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response){
                    if (okCallBack!=null)
                    {
                        //是否请求成功
                        if (call.isCanceled())
                        {
                            sendFailResultCallback(new Exception("Canceled"), okCallBack);
                        }
                        else
                        {
                            if (response.code()!=200)
                            {
                                sendFailResultCallback(new Exception("response.code = 200"), okCallBack);
                            }
                            else
                            {
                                try
                                {
                                    String responseBody = response.body().string();
                                    if (okCallBack.mType==null|| okCallBack.mType==String.class)
                                    {
                                        sendSuccessResultCallback(responseBody,okCallBack);
                                    }
                                    else
                                    {
                                        Object object= gson.fromJson(responseBody, okCallBack.mType);
                                        sendSuccessResultCallback(object,okCallBack);
                                    }
                                }
                                catch (Exception e)
                                {
                                    sendFailResultCallback(e, okCallBack);
                                }
                                finally {
                                    if (response.body()!=null)
                                    {
                                        response.body().close();
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }


    /**
     * 请求失败
     * @param exception
     * @param okCallBack
     */
    private void sendFailResultCallback(final Exception exception, final OkCallBack okCallBack) {
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






    public void newWebSocket(String key, Request request, WebSocketListener webSocketListener)
    {
        OkHttpClient okHttpClient = map.get(key);
        if (okHttpClient==null)
        {
            okHttpClient = okhttpBuilder.obtianClient(key);
        }
        final OkHttpClient ok=okHttpClient;
        if (ok!=null)
        {
            ok.newWebSocket(request,webSocketListener);
            ok.dispatcher().executorService().shutdown();
        }
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
