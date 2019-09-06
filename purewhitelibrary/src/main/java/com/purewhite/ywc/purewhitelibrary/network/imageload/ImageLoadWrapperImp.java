package com.purewhite.ywc.purewhitelibrary.network.imageload;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;


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

    @Override
    public void init(ImageView imageView, Object url) {
        init(ImageLoadType.initDefalut,imageView,url);
    }

    @Override
    public void initCircle(ImageView imageView, Object url) {
        init(ImageLoadType.initCricle,imageView,url);
    }


    @Override
    public void init(String key, ImageView imageView, Object url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(ImageLoadType.newInstance().obtianRequestOptions(key))
                .into(imageView);
    }

    @Override
    public void init(String key, ImageView imageView, Object url, int wight, int height) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(ImageLoadType.newInstance().obtianRequestOptions(key,wight,height))
                .into(imageView);
    }

    @Override
    public void initRadio(ImageView imageView, Object url, int radioAd) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(ImageLoadType.newInstance().obtianRequestOptions(ImageLoadType.initDefalut,radioAd))
                .into(imageView);
    }






    @Override
    public void clear() {
        //清理内存缓存 可以在UI主线程中进行
        Glide.get(AppUtils.getContext()).clearMemory();
        //清理磁盘缓存 需要在子线程中执行
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Glide.get(AppUtils.getContext()).clearDiskCache();
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }






    @Override
    public void stop() {
        Glide.with(AppUtils.getContext()).pauseRequests();
    }

    @Override
    public void start() {
        Glide.with(AppUtils.getContext()).resumeRequests();
    }





    public void obtianBitmap(String uri, BitmapImageViewTarget bitmapImageViewTarget) {
         Glide.with(AppUtils.getContext())
                .asBitmap()
                .load(uri)
                .centerCrop()
                .into(bitmapImageViewTarget);
    }
}
