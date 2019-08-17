package com.purewhite.ywc.purewhitelibrary.ui.image.adapter;

import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.databinding.PureAdapterPictureSelectBinding;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;

public class PictureSelectAdapter extends BindAdapter<ImageBean> {

    private boolean isCamera=false;
    private List<String> selectList=new ArrayList<>();

    public void setSelectList(List<String> selectList) {
        this.selectList = selectList==null?new ArrayList<>():selectList;
    }

    public PictureSelectAdapter() {
        addLayout(R.layout.pure_adapter_picture_select);
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
        }
        //是否以选中
        if (selectList.contains(imageBean))
        {

        }
        else
        {

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
