package com.purewhite.ywc.purewhitelibrary.network.imageload.round;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;

import java.security.MessageDigest;

public class GlideRoundTransform extends BitmapTransformation {

    private float round;

    public GlideRoundTransform(float round) {
        this.round = SizeUtils.dpToPxFloat(round);
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return obtianBitmap(pool, toTransform);
    }




    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }


    private Bitmap obtianBitmap(BitmapPool pool, Bitmap toTransform)
    {
        if (toTransform == null) {
            return null;
        }
        Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, toTransform.getWidth(), toTransform.getHeight());
        canvas.drawRoundRect(rectF, round, round, paint);
        return result;
    }
}
