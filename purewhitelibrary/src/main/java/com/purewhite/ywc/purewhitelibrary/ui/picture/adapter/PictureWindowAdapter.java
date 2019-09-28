package com.purewhite.ywc.purewhitelibrary.ui.picture.adapter;

import android.view.View;
import android.widget.ImageView;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BaseAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhitelibrary.config.StringUtils;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;

import java.util.List;

public class PictureWindowAdapter extends BaseAdapter<Folder, BaseViewHolder> {

    private int selectPosition=0;
    public PictureWindowAdapter(List<Folder> mData) {
        super(mData);
        addLayout(R.layout.pure_adapter_window_picture);
    }

    @Override
    protected void onData(BaseViewHolder holder, int position, Folder folder, int itemViewType) {
        holder.setText(R.id.picture_num,folder.getImageBeanList().size()+"张")
                .setVisibility(R.id.pure_picture_select,position==selectPosition)
                .setText(R.id.picture_title, StringUtils.obtianString(folder.getName()))
                .setImage(R.id.ratio_image_view,folder.getImageBeanList().get(0).getPath());
    }


    public boolean flushSelectPosition(int position) {
       if (position!=selectPosition)
       {
           this.selectPosition=position;
           notifyDataSetChanged();
           return true;
       }
       return false;
    }


}
