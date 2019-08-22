package com.purewhite.ywc.purewhitelibrary.ui.picture.scanner;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuwenchao
 */
public abstract class MediaStoreScanner<T> {

    /**
     * 查询URI
     *
     * @return
     */
    protected abstract Uri getScanUri();

    /**
     * 查询列名
     *
     * @return
     */
    protected abstract String[] getProjection();

    /**
     * 查询条件
     *
     * @return
     */
    protected abstract String getSelection();

    /**
     * 查询条件值
     *
     * @return
     */
    protected abstract String[] getSelectionArgs();

    /**
     * 查询排序
     *
     * @return
     */
    protected abstract String getOrder();




    /**
     * 根据查询条件进行媒体库查询，隐藏查询细节，让开发者更专注业务
     *
     * @return
     */
    public List<T> queryMedia() {
        List<T> list = new ArrayList<>();
        ContentResolver contentResolver = AppUtils.getContext().getContentResolver();
        Cursor cursor = contentResolver.query(getScanUri(), getProjection(), getSelection(), getSelectionArgs(), getOrder());
        if (cursor != null&&cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                T t = parse(cursor);
                list.add(t);
            }
            cursor.close();
        }
        return list;
    }




    /**
     * 对外暴露游标，让开发者灵活构建对象
     *
     * @param cursor
     * @return
     */
    protected abstract T parse(Cursor cursor);
}
