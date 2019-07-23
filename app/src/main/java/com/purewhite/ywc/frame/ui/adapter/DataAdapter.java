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
    private int[] type;
    //标题
    public DataAdapter(List<String> list,List<String> list_center,int[] type) {
        super(list);
        this.list_center=list_center==null?new ArrayList<String>():list_center;
        this.type=type;
        addLayout(0,R.layout.adapter_data);
        addLayout(1,R.layout.adapter_data_two);
    }

    @Override
    protected int obtianDataType(int position) {
        return type!=null&&type.length>position?type[position]:0;
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
    }
}
