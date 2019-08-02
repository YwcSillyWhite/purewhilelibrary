package com.purewhite.ywc.purewhitelibrary.network.okhttp.build.base;


import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkhttpBuilder;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;

import okhttp3.Request;
import okhttp3.WebSocketListener;

/**
 * @author yuwenchao
 */
public abstract class OkRequestBuilder<B extends OkRequestBuilder> {

    protected String url;

    protected Request.Builder builder=new Request.Builder();

    public B url(String url)
    {
        this.url=url;
        return (B)this;
    }

    public B tag(Object objectTag)
    {
        builder.tag(objectTag);
        return (B)this;
    }


    protected abstract void build();


    public  void enqueue(OkCallBack okCallBack)
    {
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
