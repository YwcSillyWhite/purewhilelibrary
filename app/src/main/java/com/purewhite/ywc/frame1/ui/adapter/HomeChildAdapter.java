package com.purewhite.ywc.frame1.ui.adapter;

import android.databinding.ViewDataBinding;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.bean.ShopBean;
import com.purewhite.ywc.frame1.databinding.AdapterFragHomeChildBinding;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

public class HomeChildAdapter extends BindAdapter<ShopBean> {

    public HomeChildAdapter() {
        addLayout(R.layout.adapter_frag_home_child);
    }

    @Override
    protected void onData(BindHolder holder, int position, ShopBean shopBean) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof AdapterFragHomeChildBinding)
        {
            initAFHC(((AdapterFragHomeChildBinding) binding),position,shopBean);
        }
    }

    private void initAFHC(AdapterFragHomeChildBinding binding, int position, ShopBean shopBean) {
        ImageLoader.newInstance().init(binding.shopImg,shopBean.getItempic());
        binding.shopTitle.setText(shopBean.getItemtitle());
    }


}
