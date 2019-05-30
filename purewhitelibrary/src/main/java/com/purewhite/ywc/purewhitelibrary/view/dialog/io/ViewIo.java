package com.purewhite.ywc.purewhitelibrary.view.dialog.io;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author yuwenchao
 */
public interface ViewIo<V> {

    //查找view的方法
     <T extends View> T fdId( @IdRes int id);

     boolean viewSelected(@IdRes int id);

     //view 设置点击
     V setView(@IdRes int id,boolean click);



     V setSelecte(@IdRes int id,boolean selecte);

     //textview id,文本，是否可以点击
     V setTextView(@IdRes int id,String context,boolean click);

     //button
     V setButton(@IdRes int id,String context,boolean click);


     V setImageView(@IdRes int id,Object object,boolean click);

     V  setRecycler(@IdRes int id, RecyclerView.Adapter adapter,RecyclerView.LayoutManager layoutManager);
}
