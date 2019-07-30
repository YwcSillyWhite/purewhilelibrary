package com.purewhite.ywc.purewhitelibrary.network.okhttp.build;

import android.net.Uri;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @author yuwenchao
 */
public class GetBuilder extends OkRequestBuilder<GetBuilder> {


    public GetBuilder() {
    }

    public GetBuilder(String url, Object objectTag, Map<String, String> paramsRequest) {
        super(url, objectTag, paramsRequest);
    }

    @Override
    public GetBuilder addParam(String key, String value) {
        if (paramsRequest==null)
        {
            paramsRequest=new HashMap<>();
        }
        paramsRequest.put(key,value);
        return this;
    }

    @Override
    public GetBuilder addParams(Map<String, String> map) {
        if (map!=null)
        {
            paramsRequest=map;
        }
        return this;
    }

    @Override
    public GetBuilder build() {
        builder.get();
        if (objectTag!=null)
        {
            builder.tag(objectTag);
        }
        obtainUri();
        builder.url(url);
        return this;
    }

    private void obtainUri()
    {
        if (url==null||paramsRequest==null||paramsRequest.size()==0)
        {
            return;
        }
        Uri.Builder build= Uri.parse(url).buildUpon();
        Iterator<String> iterator = paramsRequest.keySet().iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            build.appendQueryParameter(key,paramsRequest.get(key).toString());
        }
        this.url=build.build().toString();
    }




}
