package com.purewhite.ywc.purewhitelibrary.config;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;

/**
 *
 * 跳转获取照片
 * @author yuwenchao
 */
public class PhotoUtils {

    /**
     *
     * @param activity
     * @param providerFileContent
     * @param file
     * @param requestCode
     */
    public  static void intentCamera(Activity activity, String providerFileContent
            , File file, int requestCode)
    {
        if (file!=null&&activity!=null)
        {
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri uri;
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                /**
                 * 如果设置了，此意图的接收者将被授予对意图数据中的URI及其剪贴数据中指定的任何URI执行读取操作的权限。
                 * 当应用于意图的剪贴数据时，所有uri以及对意图项中的数据或其他剪贴数据的递归遍历都将被授予;
                 * 只使用顶层意图的grant标志。
                 */
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                uri= FileProvider.getUriForFile(activity,providerFileContent,file);
            }
            else
            {
                uri=Uri.fromFile(file);
            }
            //Intent-extra的名称，用于指示用于存储所请求的图像或视频的内容解析器Uri。
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            activity.startActivityForResult(intent,requestCode);
        }
    }


    /**
     *
     * @param fragment
     * @param providerFileContent
     * @param file
     * @param requestCode
     */
    public  static void intentCamera(Fragment fragment, String providerFileContent
            , File file, int requestCode)
    {
        if (file!=null&&fragment!=null)
        {
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri uri;
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                /**
                 * 如果设置了，此意图的接收者将被授予对意图数据中的URI及其剪贴数据中指定的任何URI执行读取操作的权限。
                 * 当应用于意图的剪贴数据时，所有uri以及对意图项中的数据或其他剪贴数据的递归遍历都将被授予;
                 * 只使用顶层意图的grant标志。
                 */
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                uri=FileProvider.getUriForFile(fragment.getContext(),providerFileContent,file);
            }
            else
            {
                uri=Uri.fromFile(file);
            }
            //Intent-extra的名称，用于指示用于存储所请求的图像或视频的内容解析器Uri。
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            fragment.startActivityForResult(intent,requestCode);
        }
    }
}
