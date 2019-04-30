package com.purewhite.ywc.purewhitelibrary.adapter.callback;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 * @author yuwenchao
 * @date 2018/11/15
 * recycler 数据类型点击事件
 */

public interface OnItemDataListener {
    /**
     *
     * @param adapter  适配器
     * @param view     点击的view
     * @param position 点击数据的position
     * @
     */
    void OnClick(RecyclerView.Adapter adapter, View view, int position, Object object);
}
