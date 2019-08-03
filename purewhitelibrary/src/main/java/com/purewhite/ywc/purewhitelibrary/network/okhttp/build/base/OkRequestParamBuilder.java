package com.purewhite.ywc.purewhitelibrary.network.okhttp.build.base;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.NonNull;

public abstract class OkRequestParamBuilder<B> extends OkRequestBuilder{

    //请求参数
    protected Map<String,String> paramsRequest=new HashMap<>();

    public B addParam(@NonNull String key, @NonNull String value)
    {
        paramsRequest.put(key,value);
        return ((B) this);
    }

    public B addParams(Map<String,String> paramsRequest)
    {
        if (paramsRequest!=null&&paramsRequest.size()>0)
        {
            for (String key:paramsRequest.keySet())
            {
                addParam(key,paramsRequest.get(key));
            }
        }
        return ((B) this);
    }

}
