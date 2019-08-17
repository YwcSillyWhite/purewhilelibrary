package com.purewhite.ywc.frame.ui.adapter;

import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.AdapterFragMineBinding;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;

import java.util.List;

public class MineAdapter extends BindAdapter<String> {

    public MineAdapter(List<String> list) {
        super(list);
        addLayout(R.layout.adapter_frag_mine);
    }

    @Override
    protected void onData(BindHolder holder, int position, String s,int itemType) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof AdapterFragMineBinding)
        {
            initData(((AdapterFragMineBinding) binding),position,s);
        }
    }

    private void initData(AdapterFragMineBinding binding, int position, String s) {
        binding.textView.setText(s);
    }
}
