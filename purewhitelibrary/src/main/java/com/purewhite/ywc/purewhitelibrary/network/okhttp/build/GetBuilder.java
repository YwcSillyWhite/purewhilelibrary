package com.purewhite.ywc.purewhitelibrary.network.okhttp.build;

import android.net.Uri;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @author yuwenchao
 */
public class GetBuilder extends OkRequestBuilder<GetBuilder> {

    @Override
    public GetBuilder addParam(String key, String value) {
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
    public void build() {
        builder.get();
        if (objectTag!=null)
        {
            builder.tag(objectTag);
        }
        obtainUri();
        builder.url(url);
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
