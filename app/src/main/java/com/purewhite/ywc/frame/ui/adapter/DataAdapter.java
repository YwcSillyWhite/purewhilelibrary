package com.purewhite.ywc.frame.ui.adapter;

import android.view.Gravity;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.AdapterDataBinding;
import com.purewhite.ywc.frame.databinding.AdapterDataTwoBinding;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends BindAdapter<String> {

    //内容
    private List<String> list_center;
    //标题
    public DataAdapter(List<String> list,List<String> list_center) {
        super(list);
        this.list_center=list_center==null?new ArrayList<String>():list_center;
        addLayout(R.layout.adapter_data);

    }


    @Override
    protected void onData(BindHolder holder, int position, String s) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof AdapterDataBinding)
        {
            initBind(((AdapterDataBinding) binding),position,s);
        }
        else if (binding instanceof AdapterDataTwoBinding)
        {
            initBindTwo(((AdapterDataTwoBinding) binding),position,s);
        }
    }

    private void initBindTwo(AdapterDataTwoBinding binding, int position, String s) {
        binding.title.setText(s);
        if (position<list_center.size())
        {
            binding.center.setText(list_center.get(position));
        }
        else
        {
            binding.center.setText("");
        }

    }

    private void initBind(AdapterDataBinding binding, int position, String s) {
        binding.title.setText(s);
        if (position<list_center.size())
        {
            binding.center.setText(list_center.get(position));
        }
        else
        {
            binding.center.setText("");
        }
        binding.title.setGravity(position+1==obtianDataCount()?Gravity.RIGHT:Gravity.LEFT);
    }
}
