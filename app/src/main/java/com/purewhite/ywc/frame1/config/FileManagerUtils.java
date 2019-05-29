package com.purewhite.ywc.frame1.config;

import android.content.Context;
import android.os.Environment;

import com.purewhite.ywc.purewhitelibrary.config.file.FileUtils;

import java.io.File;

/**
 * @author yuwenchao
 */
public class FileManagerUtils {

    public final static int FILE_PICTURES=0x10001;
    public static File createTimeFile(Context context,int tagRes)
    {
        File file=null;
        if (FileUtils.sdCan())
        {
            File filesDir = null;
            switch (tagRes)
            {
                case FILE_PICTURES:
                    //获取私有相册
                    filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    break;
            }
            if (filesDir!=null)
            {
                //创建文件
                File createFile = new File(filesDir, System.currentTimeMillis() + ".jpg");
                if (FileUtils.createOrExistsFile(createFile))
                {
                    file=createFile;
                }
            }

        }
        return file;
    }



    public static void removeFile(Context context,int tagRes)
    {
        switch (tagRes)
        {
            case FILE_PICTURES:
                File filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                FileUtils.deleteDirOrFile(filesDir);
                break;
        }
    }
}
