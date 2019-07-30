package com.purewhite.ywc.purewhitelibrary.network.okhttp.build;


import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkhttpBuilder;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.WebSocketListener;

/**
 * @author yuwenchao
 */
public abstract class OkRequestBuilder<B extends OkRequestBuilder> {

    protected String url;
    protected Object objectTag;
    //请求参数
    protected Map<String,String> paramsRequest=new HashMap<>();
    protected Request.Builder builder=new Request.Builder();


    public B url(String url)
    {
        this.url=url;
        builder.url(url);
        return (B)this;
    }

    public B tag(Object objectTag)
    {
        this.objectTag=objectTag;
        builder.tag(objectTag);
        return (B)this;
    }

    public abstract B addParam(String key, String value);

    public abstract B addParams(Map<String,String> map);


    protected abstract void build();



    public  void enqueue(OkCallBack okCallBack)
    {
        build();
        enqueue(OkhttpBuilder.defaultOKhttp,okCallBack);
    }

    public  void enqueue(String key,OkCallBack okCallBack)
    {
        build();
        OkHttpUtils.newInstance().enqueue(key,builder.build(),okCallBack);
    }


    public void newWebSocket(String key, WebSocketListener webSocketListener)
    {
        build();
        OkHttpUtils.newInstance().newWebSocket(key,builder.build(),webSocketListener);
    }


}
