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
        ImageView imageView = holder.findViewId(R.id.ratio_image_view);
        holder.setTextView(R.id.picture_title, StringUtils.obtianString(folder.getName()));
        ImageLoader.newInstance().init(imageView,folder.getImageBeanList().get(0).getPath());
        holder.setTextView(R.id.picture_num,folder.getImageBeanList().size()+"张");
        holder.findViewId(R.id.pure_picture_select).setVisibility(position==selectPosition? View.VISIBLE:View.GONE);
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
