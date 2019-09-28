package com.purewhite.ywc.purewhitelibrary.adapter.viewholder;


import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;


/**
 *
 * @author yuwenchao
 * @date 2018/11/15
 */

public class BaseViewHolder<V extends BaseViewHolder> extends RecyclerView.ViewHolder {

    private SparseArray<View> sparseArray=new SparseArray<>();
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public View fdbyid(@NonNull @IdRes int id)
    {
        View view = sparseArray.get(id);
        if (view==null)
        {
            view=itemView.findViewById(id);
            sparseArray.put(id,view);
        }
        return  view;
    }


    public final V setText(@NonNull @IdRes int id, @NonNull String content){
        View view = fdbyid(id);
        if (view!=null&&view instanceof TextView){
            ((TextView) view).setText(content);
        }
        return ((V) this);
    }

    public final V setRecycler(@NonNull@IdRes int id,@NonNull RecyclerView.Adapter adapter,@NonNull RecyclerView.LayoutManager layoutManager){
        View view = fdbyid(id);
        if (view!=null&&view instanceof RecyclerView){
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(layoutManager);
        }
        return ((V) this);
    }

    public final V setVisibility(@NonNull@IdRes int id,int visibility){
        View view = fdbyid(id);
        if (view!=null){
            view.setVisibility(visibility);
        }
        return ((V) this);
    }

    public final V setVisibility(@NonNull@IdRes int id,boolean visible)
    {
        return setVisibility(id,visible?View.VISIBLE:View.GONE);
    }

    public final V setSelected(@NonNull@IdRes int id,boolean selected){
        View view = fdbyid(id);
        if (view!=null){
            view.setSelected(selected);
        }
        return ((V) this);
    }

    public final V setEnable(@NonNull@IdRes int id,boolean enable){
        View view = fdbyid(id);
        if (view!=null){
            view.setEnabled(enable);
        }
        return ((V) this);
    }

    public final V setImage(@NonNull@IdRes int id,Object object){
        View view = fdbyid(id);
        if (view!=null&&view instanceof ImageView){
            ImageLoader.newInstance().init(((ImageView) view),object);
        }
        return ((V) this);
    }

}
