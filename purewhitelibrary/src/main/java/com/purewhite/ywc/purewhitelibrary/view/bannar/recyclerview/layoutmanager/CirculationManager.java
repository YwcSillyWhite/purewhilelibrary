package com.purewhite.ywc.purewhitelibrary.view.bannar.recyclerview.layoutmanager;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 无限循环manger
 */
public class CirculationManager extends LinearLayoutManager {

    //是否玄幻
    private boolean circulation=false;

    public CirculationManager(Context context,boolean circulation) {
        super(context);
        this.circulation=circulation;
    }

    public CirculationManager(Context context, int orientation, boolean reverseLayout,boolean circulation) {
        super(context, orientation, reverseLayout);
        this.circulation=circulation;
    }


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int itemCount = getItemCount();
        if (itemCount==0)
        {
            //清空view
            detachAndScrapAttachedViews(recycler);
            return;
        }
        super.onLayoutChildren(recycler, state);
    }


    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (circulation)
        {
            int orientation = getOrientation();
//            if (or)
            //填充
//            fill(dy,recycler,state);
            //滚动
            offsetChildrenVertical(dy*-1);

            //回收已经离开界面的
//            recycleOut(dy,recycler,state);

            return dy;
        }
        else
        {
            return super.scrollVerticallyBy(dy, recycler, state);
        }

    }
}
