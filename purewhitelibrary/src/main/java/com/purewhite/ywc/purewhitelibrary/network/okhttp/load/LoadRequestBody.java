package com.purewhite.ywc.purewhitelibrary.network.okhttp.load;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;

public class LoadRequestBody extends RequestBody {

    private RequestBody requestBody;
    private OnLoadProgressLinstener onLoadProgressLinstener;
    public LoadRequestBody(RequestBody requestBody, OnLoadProgressLinstener onLoadProgressLinstener) {
        this.requestBody = requestBody;
        this.onLoadProgressLinstener = onLoadProgressLinstener;
    }


    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(@NotNull BufferedSink sink) throws IOException {
        LoadSink loadSink=new LoadSink(sink,requestBody,onLoadProgressLinstener);
        BufferedSink bufferedSink = Okio.buffer(loadSink);
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }



}
