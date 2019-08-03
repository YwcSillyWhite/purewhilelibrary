package com.purewhite.ywc.purewhitelibrary.network.okhttp.build;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.build.base.OkRequestBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PostStringBuilder extends OkRequestBuilder<PostStringBuilder> {

    private MediaType mediaType=MediaType.parse("application/json; charset=utf-8");
    private String content;
    private Gson gson=new Gson();

    public void replaceMediaType(MediaType mediaType)
    {
        if (mediaType!=null)
        {
            this.mediaType=mediaType;
        }
    }

    public void addString(String content)
    {
        if (!TextUtils.isEmpty(content))
        {
            this.content=content;
        }
    }


    @Override
    protected void build() {
        builder.post(obtianBody(obtianBody()));
    }


    private RequestBody obtianBody()
    {
        return RequestBody.create(content,mediaType);
    }
}
