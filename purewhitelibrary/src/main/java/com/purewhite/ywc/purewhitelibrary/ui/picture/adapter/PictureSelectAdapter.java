package com.purewhite.ywc.purewhitelibrary.ui.picture.adapter;

import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureAdapterPictureSelectBinding;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureManager;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;

public class PictureSelectAdapter extends BindAdapter<ImageBean> {

    private boolean isCamera=false;
    public PictureSelectAdapter() {
        addLayout(R.layout.pure_adapter_picture_select);
    }

    public int obtainPosition(int position)
    {
        return isCamera?position-1:position;
    }

    @Override
    protected int obtainDataCount() {
        return isCamera?super.obtainDataCount()+1:super.obtainDataCount();
    }

    @Override
    protected void onDataBindView(BindHolder holder, int position, int itemViewType) {
        if (isCamera)
        {
            if (position==0)
            {
                ViewDataBinding binding = holder.getBinding();
                if (binding instanceof PureAdapterPictureSelectBinding)
                {
                    PureAdapterPictureSelectBinding pictureSelectBinding = (PureAdapterPictureSelectBinding) binding;
                    ImageLoader.newInstance().init(pictureSelectBinding.picImg,R.mipmap.leak_canary_icon);
                    pictureSelectBinding.picClick.setVisibility(View.GONE);
                }
            }
            else
            {
                onData(holder,position,obtainT(position-1),itemViewType);
            }
        }
        else
        {
            onData(holder,position,obtainT(position),itemViewType);
        }
    }

    @Override
    protected void onData(BindHolder holder, int position, ImageBean imageBean, int itemViewType) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof PureAdapterPictureSelectBinding)
        {
            PureAdapterPictureSelectBinding pictureSelectBinding = (PureAdapterPictureSelectBinding) binding;
            ImageLoader.newInstance().init(pictureSelectBinding.picImg,imageBean.getPath());

            pictureSelectBinding.picClick.setVisibility(View.VISIBLE);
            if (PictureManager.newInstance().isSelector(imageBean.getPath()))
            {
                pictureSelectBinding.picClick.setSelected(true);
                pictureSelectBinding.picImg.setScaleX(1.5f);
                pictureSelectBinding.picImg.setScaleY(1.5f);
                pictureSelectBinding.picImg.setAlpha(0.5f);
            }
            else
            {
                pictureSelectBinding.picClick.setSelected(false);
                pictureSelectBinding.picImg.setScaleX(1f);
                pictureSelectBinding.picImg.setScaleY(1f);
                pictureSelectBinding.picImg.setAlpha(1f);
            }
            pictureSelectBinding.picClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ClickUtils.clickable(view))
                    {
                        if ( PictureManager.newInstance().alterImage(imageBean.getPath()))
                        {
                            flushPosition(position);
                        }
                    }
                }
            });
        }
    }



    public void flush(List<Folder> list,int position)
    {
        if (list!=null&&position<list.size())
        {
            isCamera=position==0;
            Folder folder = list.get(position);
            flush(folder.getImageBeanList());
        }
    }



}
