package com.purewhite.ywc.purewhitelibrary.view.recyclerview;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *
 * 图1：代表了getItemOffsets(),可以实现类似padding的效果
 *
 * 图2：代表了onDraw(),可以实现类似绘制背景的效果，内容在上面
 *
 * 图3：代表了onDrawOver()，可以绘制在内容的上面，覆盖内容
 * @author yuwenchao
 */
public class AroundItemDecoration extends RecyclerView.ItemDecoration {
    private int distance;
    public AroundItemDecoration(int distance) {
        this.distance = distance;
    }


    //这是item的padding
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view
            , @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top=distance;
        outRect.left=distance;
        outRect.right=distance;
        outRect.bottom=distance;
    }
}
