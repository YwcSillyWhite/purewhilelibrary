package com.purewhite.ywc.purewhitelibrary.adapter.viewholder;


import androidx.databinding.ViewDataBinding;

/**
 *
 * @author yuwenchao
 * @date 2018/11/17
 */

public class BindHolder extends BaseViewHolder{

    private ViewDataBinding binding;

    public final ViewDataBinding getBinding() {
        return binding;
    }

    public BindHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }


}
