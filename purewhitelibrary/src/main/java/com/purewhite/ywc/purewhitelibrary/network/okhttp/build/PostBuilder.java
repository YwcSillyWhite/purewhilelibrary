package com.purewhite.ywc.purewhitelibrary.network.okhttp.build;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * @author yuwenchao
 */
public class PostBuilder extends OkRequestBuilder<PostBuilder>{


    public PostBuilder() {
    }

    public PostBuilder(String url, Object objectTag, Map<String, String> paramsRequest) {
        super(url, objectTag, paramsRequest);
    }

    @Override
    public PostBuilder addParam(String key, String value) {
        if (paramsRequest==null)
        {
            paramsRequest=new HashMap<>();
        }
        return this;
    }

    @Override
    public PostBuilder addParams(Map<String, String> map) {
        if (map!=null)
        {
            paramsRequest=map;
        }
        return this;
    }

    @Override
    public PostBuilder build() {
        builder.post(obtianBody());
        builder.url(url);
        if (objectTag!=null)
            builder.tag(objectTag);
        builder.build();
        return this;
    }


    private RequestBody obtianBody()
    {
        FormBody.Builder builder = new FormBody.Builder();
        addParams(builder);
        return builder.build();
    }

    private void addParams(FormBody.Builder builder)
    {
        if (paramsRequest!=null&&paramsRequest.size()>0)
        {
            Iterator<String> iterator = paramsRequest.keySet().iterator();
            while (iterator.hasNext())
            {
                String key = iterator.next();
                builder.add(key,paramsRequest.get(key));
            }
        }
    }
}
