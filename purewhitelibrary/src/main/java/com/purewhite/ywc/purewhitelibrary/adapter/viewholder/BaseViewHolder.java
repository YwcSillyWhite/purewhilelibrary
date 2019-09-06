package com.purewhite.ywc.purewhitelibrary.adapter.viewholder;


import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 *
 * @author yuwenchao
 * @date 2018/11/15
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> sparseArray;
    public BaseViewHolder(View itemView) {
        super(itemView);
        sparseArray=new SparseArray<>();
    }


    public <T extends View>T findViewId(@NonNull @IdRes int id)
    {
        View view = sparseArray.get(id);
        if (view==null)
        {
            view=itemView.findViewById(id);
            sparseArray.put(id,view);
        }
        return (T) view;
    }


    public final BaseViewHolder setTextView(@NonNull@IdRes int id, @NonNull String content)
    {
        View view = findViewId(id);
        if (view instanceof TextView)
        {
            ((TextView) view).setText(content);
        }
        return this;
    }


    public final BaseViewHolder setButton(@NonNull@IdRes int id, @NonNull String content)
    {
        View view = findViewId(id);
        if (view instanceof Button)
        {
            ((Button) view).setText(content);
        }
        return this;
    }

    public final BaseViewHolder setEditView(@NonNull@IdRes int id, @NonNull String content)
    {
        View view = findViewId(id);
        if (view instanceof EditText)
        {
            ((EditText) view).setText(content);
        }
        return this;
    }

    public final BaseViewHolder setRecycler(@NonNull@IdRes int id,@NonNull RecyclerView.Adapter adapter,@NonNull RecyclerView.LayoutManager layoutManager)
    {
        View view = findViewId(id);
        if (view instanceof RecyclerView)
        {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }
        return this;
    }



    public final BaseViewHolder setVisibility(@NonNull@IdRes int id,int visibility)
    {
        View view = findViewId(id);
        view.setVisibility(visibility);
        return this;
    }

    public final BaseViewHolder setVisibility(@NonNull@IdRes int id,boolean show)
    {
        setVisibility(id,show?View.VISIBLE:View.GONE);
        return this;
    }

}
