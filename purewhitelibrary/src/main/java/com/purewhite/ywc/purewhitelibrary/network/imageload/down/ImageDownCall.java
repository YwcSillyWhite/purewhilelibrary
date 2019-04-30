package com.purewhite.ywc.purewhitelibrary.network.imageload.down;

/**
 * @author yuwenchao
 * @date
 */
public interface ImageDownCall {
    void onSuccess(String imageAddress);

    void onFail();
}
