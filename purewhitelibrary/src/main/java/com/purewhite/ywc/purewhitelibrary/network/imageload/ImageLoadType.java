package com.purewhite.ywc.purewhitelibrary.network.imageload;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;

import java.util.HashMap;
import java.util.Map;

public class ImageLoadType {

    public static final String initDefalut="initDefalut";
    public static final String initCricle="initCricle";
    private static ImageLoadType imageLoadType;
    private Map<String,RequestOptions> map=new HashMap<>();
    public static ImageLoadType newInstance() {
        if (imageLoadType ==null)
        {
            synchronized (ImageLoadType.class)
            {
                if (imageLoadType==null)
                {
                    imageLoadType=new ImageLoadType();
                }
            }
        }
        return imageLoadType;
    }


    public RequestOptions obtianRequestOptions(String key)
    {
        RequestOptions requestOptions = map.get(key);
        if (requestOptions==null)
        {
            requestOptions = new RequestOptions();
            setRequestOptions(requestOptions,key);
            map.put(key,requestOptions);
        }
        return requestOptions;
    }

    public RequestOptions obtianRequestOptions(String key,int wight,int height)
    {
        String realKey=key+"{"+wight+","+height+"}";
        RequestOptions requestOptions = map.get(realKey);
        if (requestOptions==null)
        {
            requestOptions = new RequestOptions();
            requestOptions.override(wight,height);
            setRequestOptions(requestOptions,key);
            map.put(realKey,requestOptions);
        }
        return requestOptions;
    }


    public RequestOptions obtianRequestOptions(String key,int radioAd)
    {

        String realKey=key+"{"+radioAd+"}";
        RequestOptions requestOptions = map.get(realKey);
        if (requestOptions==null)
        {
            requestOptions = new RequestOptions();
//            RoundedCorners transform = new RoundedCorners(this, 30);
            requestOptions.bitmapTransform(new RoundedCorners(30));
            setRequestOptions(requestOptions,key);
            map.put(realKey,requestOptions);
        }
        return requestOptions;
    }



    public RequestOptions obtianRequestOptions(String key,int radioAd,int wight,int height)
    {

        String realKey=key+"{"+wight+","+height+","+radioAd+"}";
        RequestOptions requestOptions = map.get(realKey);
        if (requestOptions==null)
        {
            requestOptions = new RequestOptions();
            requestOptions.override(wight,height);
            requestOptions.bitmapTransform(new RoundedCorners(6));
            setRequestOptions(requestOptions,key);
            map.put(realKey,requestOptions);
        }
        return requestOptions;
    }


    private void setRequestOptions(RequestOptions requestOptions,String key)
    {
        switch (key)
        {
            case initDefalut:
                //不加载动画
                requestOptions.dontTransform()
                        //加载中图片
                        .placeholder(R.mipmap.pure_load_error)
                        //加载失败的图片
                        .error(R.mipmap.pure_load_error);
                break;
            case initCricle:
                //不加载动画
                requestOptions.dontTransform()
                        .optionalCircleCrop()
                        //占位图片
                        .placeholder(R.mipmap.pure_load_error)
                        //加载失败的图片
                        .error(R.mipmap.pure_load_error);
                break;
        }
    }
}
