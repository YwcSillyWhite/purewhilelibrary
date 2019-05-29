package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.databinding.AdapterPictureBinding;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

/**
 * @author yuwenchao
 */
public class PictureAdapter extends BindAdapter<ImageBean> {

    public PictureAdapter() {
        addLayout(R.layout.adapter_picture);
    }

    @Override
    protected void onData(BindHolder holder, int position, ImageBean imageBean) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof AdapterPictureBinding)
        {
            initOne(((AdapterPictureBinding) binding),position,imageBean);
        }
    }

    private void initOne(AdapterPictureBinding binding, int position, ImageBean imageBean) {
        ImageLoader.newInstance().init(binding.imageView,imageBean.getPath());
    }
}
