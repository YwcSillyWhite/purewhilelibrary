package com.purewhite.ywc.purewhitelibrary.network.imageload;

import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 *
 * @author yuwenchao
 * @date 2018/11/14
 * 图片加载包装接口
 */

public interface ImageLoadWrapper {


    //默认加载图片
    void init(ImageView imageView,Object url);

    void init(String key,ImageView imageView,Object url);
    void init(String key,ImageView imageView,Object url,int wight,int height);
    //加载圆形图片
    void initCircle(ImageView imageView, Object url);

    void initRadio(ImageView imageView,Object url,int radioAd);



    //清除缓存
    void clear();
    //暂停加载
    void stop();
    //启动加载
    void start();



    void obtianBitmap(String uri, BitmapImageViewTarget bitmapImageViewTarget);
}
