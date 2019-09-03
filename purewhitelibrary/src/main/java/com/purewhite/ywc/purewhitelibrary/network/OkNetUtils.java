package com.purewhite.ywc.purewhitelibrary.network;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.purewhite.ywc.purewhitelibrary.network.okhttp.OkHttpUtils;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.call.OkCallBack;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;

/**
 * @author yuwenchao
 */
public class OkNetUtils {


    public static <T> void get(Activity activity,String url,Map<String,String> paramsRequest,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.get().addParams(paramsRequest)
                .tag(activity)
                .url(url)
                .enqueue(okCallBack);
    }

    public static <T> void get(Fragment fragment, String url, Map<String,String> paramsRequest, OkCallBack<T> okCallBack)
    {
        OkHttpUtils.get().addParams(paramsRequest)
                .tag(fragment)
                .url(url)
                .enqueue(okCallBack);

    }



    public static <T> void postForm(Activity activity,String url,Map<String,String> paramsRequest,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postForm().addParams(paramsRequest)
                .tag(activity)
                .url(url)
                .enqueue(okCallBack);
    }

    public static <T> void postForm(Fragment fragment, String url, Map<String,String> paramsRequest,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postForm().addParams(paramsRequest)
                .tag(fragment)
                .url(url)
                .enqueue(okCallBack);
    }


    public static <T> void postForm(Activity activity,String url,Map<String,String> paramsRequest,String fileKey,Map<String, File> fileMap,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postForm().addParams(paramsRequest)
                .addFiles(fileKey,fileMap)
                .tag(activity)
                .url(url)
                .enqueue(okCallBack);
    }

    public static <T> void postForm(Fragment fragment, String url, Map<String,String> paramsRequest, String fileKey,Map<String, File> fileMap, OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postForm().addParams(paramsRequest)
                .addFiles(fileKey,fileMap)
                .tag(fragment)
                .url(url)
                .enqueue(okCallBack);
    }



    public static <T> void postForm(Activity activity,String url,Map<String,String> paramsRequest,String fileKey,String fileName,File file,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postForm().addParams(paramsRequest)
                .addFile(fileKey,fileName,file)
                .tag(activity)
                .url(url)
                .enqueue(okCallBack);
    }

    public static <T> void postForm(Fragment fragment, String url, Map<String,String> paramsRequest, String fileKey,String fileName,File file, OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postForm().addParams(paramsRequest)
                .addFile(fileKey,fileName,file)
                .tag(fragment)
                .url(url)
                .enqueue(okCallBack);
    }


    public static <T> void postString(Activity activity, String url, MediaType mediaType, String contnet,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postString()
                .replaceMediaType(mediaType)
                .addString(contnet)
                .tag(activity)
                .url(url)
                .enqueue(okCallBack);
    }

    public static <T> void postString(Fragment fragment, String url, MediaType mediaType, String contnet,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postString()
                .replaceMediaType(mediaType)
                .addString(contnet)
                .tag(fragment)
                .url(url)
                .enqueue(okCallBack);
    }


    public static <T> void postString(Activity activity, String url, String contnet,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postString()
                .addString(contnet)
                .tag(activity)
                .url(url)
                .enqueue(okCallBack);
    }

    public static <T> void postString(Fragment fragment, String url, String contnet,OkCallBack<T> okCallBack)
    {
        OkHttpUtils.postString()
                .addString(contnet)
                .tag(fragment)
                .url(url)
                .enqueue(okCallBack);
    }

}
