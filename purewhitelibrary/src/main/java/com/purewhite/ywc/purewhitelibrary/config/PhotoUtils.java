package com.purewhite.ywc.purewhitelibrary.config;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import com.purewhite.ywc.purewhitelibrary.app.activity.ActivitySkipUtils;

import java.io.File;

/**
 *
 * 跳转获取照片
 * @author yuwenchao
 */
public class PhotoUtils {



    /**
     * 跳转照相机
     * @param object
     * @param providerFileContent
     * @param file
     * @param requestCode
     */
    public static void intentCamera(Object object,String providerFileContent
            ,File file,int requestCode)
    {
        if (file!=null&&(object instanceof Activity||object instanceof Fragment))
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
                if (object instanceof Activity)
                {
                    uri=FileProvider.getUriForFile(((Activity) object),providerFileContent,file);
                }
                else
                {
                    uri=FileProvider.getUriForFile(((Fragment) object).getContext(),providerFileContent,file);
                }
            }
            else
            {
                uri=Uri.fromFile(file);
            }
            //Intent-extra的名称，用于指示用于存储所请求的图像或视频的内容解析器Uri。
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            ActivitySkipUtils.startActivityAnim(intent,object,requestCode);
        }

    }
}
