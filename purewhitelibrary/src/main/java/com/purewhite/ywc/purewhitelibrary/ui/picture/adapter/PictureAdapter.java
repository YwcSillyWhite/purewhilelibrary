package com.purewhite.ywc.purewhitelibrary.ui.picture.adapter;


import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.databinding.PureAdapterPictureBinding;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.picture.config.PictureStype;
import com.purewhite.ywc.purewhitelibrary.ui.picture.manager.PicSeletorManager;

/**
 * @author yuwenchao
 */
public class PictureAdapter extends BindAdapter<ImageBean> {

    private int skip_stype;

    public int getSkip_stype() {
        return skip_stype;
    }

    public PictureAdapter(int skip_stype) {
        this.skip_stype=skip_stype;
        addLayout(R.layout.pure_adapter_picture);
    }

    @Override
    protected void onData(BindHolder holder, int position, ImageBean imageBean,int itemType) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof PureAdapterPictureBinding)
        {
            initOne(((PureAdapterPictureBinding) binding),position,imageBean);
        }
    }

    private void initOne(PureAdapterPictureBinding binding, final int position, final ImageBean imageBean) {
        ImageLoader.newInstance().init(binding.picImg,imageBean.getPath());
        if (skip_stype== PictureStype.SKIP_STYPE_PIC_ONLY)
        {
            binding.picClick.setVisibility(View.GONE);
        }
        else if (skip_stype==PictureStype.SKIP_STYPE_PIC_LIST)
        {
            binding.picClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PicSeletorManager.newInstance().solvePic(imageBean.getPath()))
                    {
                        flushPosition(position);
                        if (onItemListener!=null)
                            onItemListener.onClick(PictureAdapter.this,v,position,false);
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
}
