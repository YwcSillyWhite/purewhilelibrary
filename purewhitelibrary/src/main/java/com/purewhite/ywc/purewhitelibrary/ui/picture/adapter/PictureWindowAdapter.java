package com.purewhite.ywc.purewhitelibrary.ui.picture.adapter;

import android.view.View;
import android.widget.ImageView;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BaseAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;

import java.util.List;

public class PictureWindowAdapter extends BaseAdapter<Folder, BaseViewHolder> {

    public PictureWindowAdapter(List<Folder> mData) {
        super(mData);
        addLayout(R.layout.pure_adapter_window_picture);
    }

    @Override
    protected void onData(BaseViewHolder holder, int position, Folder folder, int itemViewType) {
        ImageView imageView = holder.findViewId(R.id.ratio_image_view);
        if (folder!=null&&folder.getImageBeanList()!=null&&folder.getImageBeanList().size()>0)
        {
            ImageLoader.newInstance().init(imageView,folder.getImageBeanList().get(0));
            holder.setTextView(R.id.text_view,folder.getName()+"("+folder.getImageBeanList().size()+")");
        }
        else
        {
            ImageLoader.newInstance().init(imageView,R.mipmap.leak_canary_icon);
            holder.setTextView(R.id.text_view,"");
        }

    }
}
