package com.purewhite.ywc.purewhitelibrary.ui.picture.adapter;

import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureAdapterPictureSelectBinding;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureManager;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;

import java.util.List;

public class PictureSelectAdapter extends BindAdapter<ImageBean> {

    private int itemFolder;
    public PictureSelectAdapter() {
        addLayout(0,R.layout.pure_adapter_picture_select_camera);
        addLayout(1,R.layout.pure_adapter_picture_select);
    }

    private boolean isCamera()
    {
        return PictureManager.newInstance().isCamera()&&itemFolder==0;
    }

    public int obtainPosition(int position)
    {
        return isCamera()?position-1:position;
    }

    @Override
    protected int obtainDataType(int position) {
        return obtainPosition(position)<0?0:1;
    }

    @Override
    protected int obtainDataCount() {
        return isCamera()?super.obtainDataCount()+1:super.obtainDataCount();
    }

    @Override
    protected void onDataBindView(BindHolder holder, int position, int itemViewType) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof PureAdapterPictureSelectBinding)
        {
            onData(holder,position,obtainT(obtainPosition(position)),itemViewType);
        }
    }

    @Override
    protected void onData(BindHolder holder, int position, ImageBean imageBean, int itemViewType) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof PureAdapterPictureSelectBinding)
        {
            LogUtils.debug(imageBean.getPath()+"图片地址");
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
            if (onItemListener!=null)
            {
                pictureSelectBinding.picClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemListener!=null)
                        {
                            onItemListener.onClick(PictureSelectAdapter.this,view,position,false);
                        }
                    }
                });
            }

        }
    }


    /**
     *
     * @param list
     * @param position
     * @return  相册名字
     */
    public String flush(List<Folder> list,int position)
    {
        if (list!=null&&position<list.size())
        {
            itemFolder=position;
            Folder folder = list.get(position);
            flush(folder.getImageBeanList());
            return folder.getName();
        }
        return "";
    }




}
