package com.purewhite.ywc.purewhitelibrary.config.file;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * @author yuwenchao
 */
public class FileManagerUtils {


    public static File createPicFile(Context context)
    {
        File file=null;
        if (FileUtils.sdCan())
        {
            //获取私有相册
            File filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            //创建文件
            File createFile = new File(filesDir, System.currentTimeMillis() + ".jpg");

            if (FileUtils.createOrExistsFile(createFile))
            {
                file=createFile;
            }
        }
        return file;
    }
}
