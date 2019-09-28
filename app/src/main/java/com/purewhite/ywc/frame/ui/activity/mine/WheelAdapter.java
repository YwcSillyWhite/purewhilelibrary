package com.purewhite.ywc.frame.ui.activity.mine;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;

import java.util.Arrays;
import java.util.List;

public class WheelAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<String> list;
    private String item[]={"item1","itemt2","item3","item4","item5","item6","item1","itemt2","item3","item4","item5","item6","item1","itemt2","item3","item4","item5","item6"};
    private int layoutId;

    public WheelAdapter() {
        this.list = Arrays.asList(item);
        this.layoutId = R.layout.adapter_wheelview;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setText(R.id.text_view,list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
