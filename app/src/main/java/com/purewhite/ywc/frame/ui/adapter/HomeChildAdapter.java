package com.purewhite.ywc.frame.ui.adapter;


import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.bean.ShopBean;
import com.purewhite.ywc.frame.databinding.AdapterFragHomeChildBinding;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

public class HomeChildAdapter extends BindAdapter<ShopBean> {

    public HomeChildAdapter() {
        addLayout(R.layout.adapter_frag_home_child);
    }

    @Override
    protected void onData(BindHolder holder, int position, ShopBean shopBean,int itemType) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof AdapterFragHomeChildBinding)
        {
            initAFHC(((AdapterFragHomeChildBinding) binding),position,shopBean);
        }
    }

    private void initAFHC(AdapterFragHomeChildBinding binding, int position, ShopBean shopBean) {
        ImageLoader.newInstance().initRadio(binding.shopImg,shopBean.getItempic(),100);
        binding.shopTitle.setText(shopBean.getItemtitle());
    }


}
