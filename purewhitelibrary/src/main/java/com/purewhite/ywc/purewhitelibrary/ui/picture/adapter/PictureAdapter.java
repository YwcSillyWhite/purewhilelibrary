package com.purewhite.ywc.purewhitelibrary.ui.picture.adapter;

import android.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.databinding.PureAdapterPictureBinding;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;

/**
 * @author yuwenchao
 */
public class PictureAdapter extends BindAdapter<ImageBean> {

    public PictureAdapter() {
        addLayout(R.layout.pure_adapter_picture);
    }

    @Override
    protected void onData(BindHolder holder, int position, ImageBean imageBean) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof PureAdapterPictureBinding)
        {
            initOne(((PureAdapterPictureBinding) binding),position,imageBean);
        }
    }

    private void initOne(PureAdapterPictureBinding binding, int position, ImageBean imageBean) {
        ImageLoader.newInstance().init(binding.imageView,imageBean.getPath());
    }
}
