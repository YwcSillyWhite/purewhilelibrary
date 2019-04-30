package com.purewhite.ywc.purewhitelibrary.config.file;

import android.content.Context;

import com.purewhite.ywc.purewhitelibrary.network.imageload.down.ImageDownCall;
import com.purewhite.ywc.purewhitelibrary.network.imageload.down.ImageDownLoader;

import java.io.File;

/**
 * @author yuwenchao
 */
public class ImgFileUtils {
    /**
     * 生成文件夹路径
     */
    public  static String Address;

    //下载图片
    public static void downImg(Context context,String uri, String name,ImageDownCall imageDownCall)
    {
        if (FileUtils.sdCan())
        {
            Address=context.getExternalCacheDir().getPath()+"/Pure/";
        }
        else
        {
            Address=context.getCacheDir()+"/Pure/";
        }
        if (FileUtils.createOrExistsDir(Address))
        {
            File file=new File(Address,name+".JPEG");
            if (FileUtils.createOrExistsFile(file))
            {
                ImageDownLoader.newInstance(context,uri,file,imageDownCall);
            }
            else
            {
                //文件不存在
            }
        }
        else
        {
            //文件目录不存在
        }
    }
}
