package com.purewhite.ywc.purewhitelibrary.network.imageload;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.purewhite.ywc.purewhitelibrary.R;


/**
 *
 * @author yuwenchao
 * @date 2018/11/14
 * 图片包装实现接口 根据自己的需求加入自己需要的图片框架
 *
 *
 * // 加载本地图片
 * File file = new File(getExternalCacheDir() + "/image.jpg");
 * Glide.with(this).load(file).into(imageView);
 *
 * // 加载应用资源
 * int resource = R.drawable.image;
 * Glide.with(this).load(resource).into(imageView);
 *
 * // 加载二进制流
 * byte[] image = getImageBytes();
 * Glide.with(this).load(image).into(imageView);
 *
 * // 加载Uri对象
 * Uri imageUri = getImageUri();
 * Glide.with(this).load(imageUri).into(imageView);
 *
 *
 */

public class ImageLoadWrapperImp implements ImageLoadWrapper{

    private RequestOptions options,optionsCricle;

    public RequestOptions getOptionsCricle() {
        if (optionsCricle==null)
        {
            optionsCricle=new RequestOptions();
            //不加载动画
            optionsCricle.dontTransform()
                    //占位图片
                    .placeholder(R.mipmap.icon_load_error)
                    //加载失败的图片
                    .error(R.mipmap.icon_load_error)
                    //圆形图片
                    .circleCrop();
        }
        return optionsCricle;
    }

    private RequestOptions getOptions(boolean skipMrmpry)
    {
        if (options==null)
        {
            options=new RequestOptions();
            //不加载动画
            options.dontTransform()
                    //占位图片
                    .placeholder(R.mipmap.icon_load_error)
                    //加载失败的图片
                    .error(R.mipmap.icon_load_error);
        }
        //是否禁止使用内存缓存，false是使用内存缓存，true是禁止使用.默认是开启的
        options.skipMemoryCache(skipMrmpry);

        /**
         * 磁盘缓存的使用
         * options .diskCacheStrategy(DiskCacheStrategy.NONE);
         * DiskCacheStrategy.NONE： 表示不缓存任何内容。
         * DiskCacheStrategy.DATA： 表示只缓存原始图片。
         * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
         * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
         * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
         */
        return options;
    }




    @Override
    public void init(ImageView imageView, Object url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(getOptions(false))
                .into(imageView);
    }

    @Override
    public void initCircle(ImageView imageView, Object url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(getOptionsCricle())
                .into(imageView);

                //只允许加载静态图片

    }

    @Override
    public void initHead(ImageView imageView, Object url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(getOptionsCricle())
                .into(imageView);
    }

    @Override
    public void initBig(ImageView imageView, Object url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(getOptions(true))
                .into(imageView);
    }

    @Override
    public void clear(final Context context) {
        //清理内存缓存 可以在UI主线程中进行
        Glide.get(context).clearMemory();
        //清理磁盘缓存 需要在子线程中执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        }).start();

    }

    @Override
    public void stop(Context context) {
        Glide.with(context).pauseRequests();
    }

    @Override
    public void start(Context context) {
        Glide.with(context).resumeRequests();
    }
}
