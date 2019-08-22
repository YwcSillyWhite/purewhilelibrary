package com.purewhite.ywc.purewhitelibrary.config.file;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.config.file.FileUtils;

import java.io.File;

/**
 * @author yuwenchao
 */
public class FileManagerUtils {

    public final static int FILE_SD=0x10001;
    public final static int FILE_SD_PICTURE=0x10002;

    public static File createTimeFile(Context context,int tagRes)
    {
        File file=null;
        if (FileUtils.sdCan())
        {
            File filesDir = null;
            switch (tagRes)
            {


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


    public static File createTimeFile(String name,int tag)
    {
        File file=null;
        if (!TextUtils.isEmpty(name))
        {
            switch (tag)
            {
                case FILE_SD:
                    if (FileUtils.sdCan())
                    {
                        File sdFile = Environment.getExternalStorageDirectory();
                        File createFile = new File(sdFile, name+"/" + System.currentTimeMillis() + ".jpg");
                        if (FileUtils.createOrExistsFile(createFile))
                        {
                            file=createFile;
                        }
                    }
                    break;
            }
        }
        return file;
    }



//    Environment.getExternalStorageDirectory() + File.separator + "images";


    public static void removeFile(Context context,int tagRes)
    {
        switch (tagRes)
        {
//            case FILE_PICTURES:
//                File filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//                FileUtils.deleteDirOrFile(filesDir);
//                break;
        }
    }
}
