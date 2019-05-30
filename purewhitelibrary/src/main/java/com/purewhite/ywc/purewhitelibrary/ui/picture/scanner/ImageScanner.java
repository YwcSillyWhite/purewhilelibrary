package com.purewhite.ywc.purewhitelibrary.ui.picture.scanner;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;

public class ImageScanner extends MediaStoreScanner<ImageBean> {

    public ImageScanner(Context context) {
        super(context);
    }

    @Override
    protected Uri getScanUri() {
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    @Override
    protected String[] getProjection() {
        return new String[]{ MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media._ID};
    }

    @Override
    protected String getSelection() {
        return null;
    }

    @Override
    protected String[] getSelectionArgs() {
        return new String[]{"image/jpeg", "image/png", "image/gif"};
    }

    @Override
    protected String getOrder() {
        return MediaStore.Images.Media.DATE_ADDED;
    }

    @Override
    protected ImageBean parse(Cursor cursor) {
        // 获取图片的路径
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        //获取图片名称
        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
        //获取图片时间
        long time = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));

        return new ImageBean(path,name,time);
    }
}
