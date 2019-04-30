package com.purewhite.ywc.purewhitelibrary.network.imageload.down;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * glide图片下载到指定的地方
 * @author yuwenchao
 */
public class ImageDownLoader implements Runnable{

    private Context mContext;
    private Object mUrl;
    private File mFile;
    private Handler handler;
    private ImageDownCall imageDownCall;

    public static ImageDownLoader newInstance(Context context,Object url,File file,ImageDownCall imageDownCall) {
        ImageDownLoader imageDownLoader = new ImageDownLoader(context,url,file,imageDownCall);
        return imageDownLoader;
    }

    public ImageDownLoader(Context context,Object url,File file,ImageDownCall imageDownCall) {
        mContext=context;
        mUrl=url;
        mFile=file;
        handler=new Handler();
        this.imageDownCall=imageDownCall;
    }

    @Override
    public void run() {
        Bitmap bitmap = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            bitmap= Glide.with(mContext)
                .asBitmap()
                .load(mUrl)
                .submit().get();
            if (bitmap!=null)
            {
                fileOutputStream=new FileOutputStream(mFile);
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                fileOutputStream.flush();
            }
        }
        catch (Exception e)
        {

        }
        finally {
            if (fileOutputStream!=null)
            {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bitmap!=null&&mFile.exists())
            {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (imageDownCall!=null)
                            imageDownCall.onSuccess(mFile.getAbsolutePath());
                    }
                });
            }
            else
            {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (imageDownCall!=null)
                            imageDownCall.onFail();
                    }
                });
            }
        }

    }
}
