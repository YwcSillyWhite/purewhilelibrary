package com.purewhite.ywc.purewhitelibrary.network.okhttp.build;


import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;

import java.util.Map;

import okhttp3.Request;

/**
 * @author yuwenchao
 */
public abstract class OkRequestBuilder {

    protected String url;
    protected Object objectTag;
    //请求参数
    protected Map<String,String> paramsRequest;
    protected Request.Builder builder=new Request.Builder();

    public OkRequestBuilder() {
    }

    public OkRequestBuilder(String url, Object objectTag,Map<String,String> paramsRequest) {
        this.url=url;
        this.objectTag=objectTag;
        this.paramsRequest=paramsRequest;
    }

    public OkRequestBuilder url(String url)
    {
        this.url=url;
        builder.url(url);
        return this;
    }

    public OkRequestBuilder tag(Object objectTag)
    {
        this.objectTag=objectTag;
        builder.tag(objectTag);
        return this;
    }

    public abstract OkRequestBuilder addParam(String key, String value);

    public abstract OkRequestBuilder addParams(Map<String,String> map);


    public abstract OkRequestBuilder build();

    public  void enqueue(OkCallBack okCallBack)
    {
        OkHttpUtils.newInstance().enqueue(okCallBack,builder.build());
    }



}
