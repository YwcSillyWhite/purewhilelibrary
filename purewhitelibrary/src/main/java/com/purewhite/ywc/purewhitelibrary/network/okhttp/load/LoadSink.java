package com.purewhite.ywc.purewhitelibrary.network.okhttp.load;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.RequestBody;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

public final class LoadSink extends ForwardingSink {

    private RequestBody requestBody;
    private OnLoadProgressLinstener loadProgressLinstener;
    private long writeBytes;

    public LoadSink(@NotNull Sink delegate, RequestBody requestBody, OnLoadProgressLinstener loadProgressLinstener) {
        super(delegate);
        this.requestBody = requestBody;
        this.loadProgressLinstener = loadProgressLinstener;
    }


    @Override
    public void write(@NotNull Buffer source, long byteCount) throws IOException {
        super.write(source, byteCount);
        writeBytes+=byteCount;
        loadProgressLinstener.loadProgress(writeBytes,requestBody.contentLength());
    }



}
