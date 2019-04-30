package com.purewhite.ywc.purewhitelibrary.config.img;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 在Android应用中，图片的主要存在方式：
 * 以File的形式存在于SD卡中
 * 以Stream的形式存在于内存中
 * 以Bitmap形式存在于内存中
 *
 *
 * Android中图片是以位图（BitMap）形式存在的，位图常见的文件格式有：
 * .bmp
 * .jpg
 * .gif
 * .png
 *
 *
 *在SD卡中图片占用的内存与以Stream形式大小一样，均小于Bitmap形式下内存占比
 *既当图片从SD卡中以流的形式加载到内存中时大小是不会发生变化的，但是stream转化为Bitmap时，其大小会突然变大
 *
 *
 *
 *
 * @author yuwenchao
 */ //图片utils
public class ImgUtils {

    /**
     * view转换成图片
     * @param view
     */
    public static Bitmap obtainBitmap(View view)
    {
        if (view==null)
            return null;
        //创建一个bitmap的画板
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return bitmap;
    }



    /**尺寸压缩
     * @param bitmap 要压缩的图片
     * @param ratio 压缩比例，值越大，图片的尺寸就越小
     * @param file 压缩的图片保存地址
     */
    public static void sizeCompressBitmap(Bitmap bitmap,int ratio,File file){
        if (ratio<=0){
            return;
        }
        Bitmap result=Bitmap.createBitmap(bitmap.getWidth()/ratio,
                bitmap.getHeight()/ratio, Bitmap.Config.ARGB_8888);
        Canvas canvas =new Canvas();
        Rect rect=new Rect(0,0,bitmap.getWidth()/ratio,bitmap.getHeight()/ratio);
        canvas.drawBitmap(bitmap,null,rect,null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        result.compress(Bitmap.CompressFormat.JPEG, 100 ,baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param bitmap
     * @param file
     * @param format
     * @param quality
     */
    public static void qualityCompressBitmap(Bitmap bitmap, File file,Bitmap.CompressFormat format, int quality)
    {
        //字节数组输出流
        ByteArrayOutputStream stream =new ByteArrayOutputStream();
        //图片压缩后把数据放在stream中
        bitmap.compress(format,quality, stream);
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            //不断把stream的数据写文件输出流中去
            fileOutputStream.write(stream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void compress(File file,int inSampleSize) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置此参数是仅仅读取图片的宽高到options中，不会将整张图片读到内存中，防止oom
        options.inJustDecodeBounds = true;
        Bitmap emptyBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap resultBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {

        }
    }

    /** 采样率压缩
     * @param filePath 压缩图
     * @param file 压缩的图片保存地址
     */
    public  static void pixeCompressBitmap(String filePath, File file){
        //采样率，数值越高，图片像素越低
        int inSampleSize=8;
        BitmapFactory.Options osts=new BitmapFactory.Options();
        osts.inSampleSize=inSampleSize;
        //inJustDecodeBounds设为True时，不会真正加载图片，而是得到图片的宽高信息。
        osts.inJustDecodeBounds=false;
        Bitmap bitmap= BitmapFactory.decodeFile(filePath,osts);
        ByteArrayOutputStream stream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        try {
            if (file.exists()){
                file.delete();
            }else{
                file.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(stream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    public static void compress(Bitmap bitmap,int bitmapSize,File file)
    {
        int quality=100;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,quality,baos);
        int sizeK = baos.toByteArray().length / 1024;
        if (sizeK<bitmapSize)
        {
            return;
        }
        else if (sizeK>bitmapSize*10)
        {
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inSampleSize=sizeK/(bitmapSize*10);
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            //inJustDecodeBounds设为True时，不会真正加载图片，而是得到图片的宽高信息。
            options.inJustDecodeBounds=false;
            bitmap= BitmapFactory.decodeFile(file.getName(),options);
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,baos);
            while (baos.toByteArray().length/1024>sizeK)
            {
                baos.reset();
                quality-=20;
                bitmap.compress(Bitmap.CompressFormat.WEBP,quality,baos);
            }
        }
        else
        {

            while (baos.toByteArray().length/1024>sizeK)
            {
                baos.reset();
                quality-=20;
                bitmap.compress(Bitmap.CompressFormat.JPEG,quality,baos);
            }
        }

        try {
            if (file.exists()){
                file.delete();
            }else{
                file.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(baos.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void bitmap(Bitmap bitmap,File file)
    {
//        Observable.just(bitmap,file).map(new Function<Object, Object>() {
//            @Override
//            public Object apply(Object o) throws Exception {
//                return null;
//            }
//        }).
    }






    
}
