package com.purewhite.ywc.purewhitelibrary.adapter.callback;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 *
 * @author yuwenchao
 * @date 2018/11/15
 * recycler 数据类型点击事件
 */

public interface OnItemLongListener {
    /**
     *
     * @param adapter  适配器
     * @param view     点击的view
     * @param position 点击数据的position
     * @param itemView 是否为itemview
     */
    boolean onClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView);
}
