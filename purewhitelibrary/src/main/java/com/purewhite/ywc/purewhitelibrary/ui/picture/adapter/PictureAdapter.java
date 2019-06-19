package com.purewhite.ywc.purewhitelibrary.ui.picture.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.databinding.PureAdapterPictureBinding;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.picture.manager.PicSeletorManager;

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

    private void initOne(PureAdapterPictureBinding binding, final int position, final ImageBean imageBean) {
        ImageLoader.newInstance().init(binding.picImg,imageBean.getPath());
        binding.picClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PicSeletorManager.newInstance().solvePic(imageBean.getPath()))
                {
                    flushPosition(position);
                    if (onItemListener!=null)
                        onItemListener.OnClick(PictureAdapter.this,v,position,false);
                }
            }
        });
        if (PicSeletorManager.newInstance().isSelectorPic(imageBean.getPath()))
        {
            binding.picClick.setSelected(true);
            binding.picImg.setAlpha(0.5f);
        }
        else
        {
            binding.picClick.setSelected(false);
            binding.picImg.setAlpha(1f);
        }

    }
}
