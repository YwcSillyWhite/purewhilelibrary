package com.purewhite.ywc.purewhitelibrary.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by yuwenchao on 2018/11/15.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> sparseArray;
    public BaseViewHolder(View itemView) {
        super(itemView);
        sparseArray=new SparseArray<>();
    }


    public <T extends View>T findViewId(int id)
    {
        View view = sparseArray.get(id);
        if (view==null)
        {
            view=itemView.findViewById(id);
            sparseArray.put(id,view);
        }
        return (T) view;
    }

    public void setVisibility(int id,int visibility)
    {
        View view = findViewId(id);
        view.setVisibility(visibility);
    }

    public void setVisibility(int id,boolean show)
    {
        setVisibility(id,show?View.VISIBLE:View.GONE);
    }

}
