package com.purewhite.ywc.purewhitelibrary.network.imageload;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.purewhite.ywc.purewhitelibrary.R;

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

    private RequestOptions options,optionsCricle;
    private final int cricle=1;
    private final int defalut=0;

    public RequestOptions getOptions(int what) {
        RequestOptions requestOptions;
        if (what==cricle)
        {
            if (optionsCricle == null) {
                optionsCricle = new RequestOptions();
                //不加载动画
                optionsCricle.dontTransform()
                        //圆形图片
                        .circleCrop();
            }
            requestOptions=optionsCricle;
        }
        else
        {
            if (options == null) {
                options = new RequestOptions();
                //不加载动画
                options.dontTransform()
                        //占位图片
                        .placeholder(R.mipmap.icon_load_error)
                        //加载失败的图片
                        .error(R.mipmap.icon_load_error);
            }
            requestOptions=options;

        }
        return requestOptions;
    }




    @Override
    public void init(ImageView imageView, Object url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(getOptions(defalut))
                .into(imageView);
    }

    @Override
    public void initCircle(ImageView imageView, Object url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(getOptions(cricle))
                .into(imageView);
                //只允许加载静态图片
    }

    @Override
    public void initHead(ImageView imageView, Object url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(getOptions(cricle))
                .into(imageView);
    }

    @Override
    public void initBig(ImageView imageView, Object url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(getOptions(defalut))
                .into(imageView);
    }

    @Override
    public void clear(final Context context) {
        //清理内存缓存 可以在UI主线程中进行
        Glide.get(context).clearMemory();
        //清理磁盘缓存 需要在子线程中执行
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Glide.get(context).clearDiskCache();
            }
        }).subscribeOn(Schedulers.io()).subscribe();
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
