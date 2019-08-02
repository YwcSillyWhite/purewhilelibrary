package com.purewhite.ywc.purewhitelibrary.network.okhttp.build;

import android.net.Uri;
import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.network.okhttp.build.base.OkRequestParamBuilder;

/**
 * @author yuwenchao
 */
public class GetBuilder extends OkRequestParamBuilder<GetBuilder> {



    @Override
    public void build() {
        builder.get();
        builder.url(obtainUri());
    }

    private String obtainUri()
    {
        if (TextUtils.isEmpty(url))
        {
            return "";
        }
        else
        {
            if (paramsRequest.size()==0)
            {
                return url;
            }
            else
            {
                Uri.Builder build= Uri.parse(url).buildUpon();
                for (String key:paramsRequest.keySet())
                {
                    build= Uri.parse(url).buildUpon();
                }
                return build.build().toString();
            }
        }
    }
}
