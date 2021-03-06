package com.purewhite.ywc.purewhitelibrary.network.okhttp.build.base;


import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkhttpBuilder;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.load.LoadRequestBody;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.load.OnLoadProgressLinstener;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.WebSocketListener;

/**
 * @author yuwenchao
 */
public abstract class OkRequestBuilder<B extends OkRequestBuilder> {

    protected String url;
    protected Request.Builder builder=new Request.Builder();
    private Map<String,String> headMap=new HashMap<>();
    //上传回掉接口
    protected OnLoadProgressLinstener onLoadProgressLinstener;


    public B addHead(@NonNull String key, @NonNull String value)
    {
        headMap.put(key,value);
        return ((B) this);
    }

    public B addHead(Map<String,String> headMap)
    {
        if (headMap!=null&&headMap.size()>0)
        {
            for (String key:headMap.keySet())
            {
                addHead(key,headMap.get(key));
            }
        }
        return ((B) this);
    }

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


    //是否接入上传进度功能
    public B addLoadProgress(OnLoadProgressLinstener onLoadProgressLinstener)
    {
        this.onLoadProgressLinstener=onLoadProgressLinstener;
        return ((B) this);
    }


    private void buildBefore()
    {
        if (headMap.size()>0)
        {
            for (String key:headMap.keySet())
            {
                builder.addHeader(key,headMap.get(key));
            }
        }
        build();
    }

    protected abstract void build();



    protected RequestBody obtianBody(RequestBody requestBody)
    {
        if (onLoadProgressLinstener!=null)
        {
            return new LoadRequestBody(requestBody,onLoadProgressLinstener);
        }
        return requestBody;
    }


    public  void enqueue(OkCallBack okCallBack)
    {
        enqueue(OkhttpBuilder.defaultOKhttp,okCallBack);
    }

    public  void enqueue(String key,OkCallBack okCallBack)
    {
        buildBefore();
        OkHttpUtils.newInstance().enqueue(key,builder.build(),okCallBack);
    }


    public void newWebSocket(String key, WebSocketListener webSocketListener)
    {
        buildBefore();
        OkHttpUtils.newInstance().newWebSocket(key,builder.build(),webSocketListener);
    }


}
