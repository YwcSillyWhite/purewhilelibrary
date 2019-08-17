package com.purewhite.ywc.purewhitelibrary.ui.picture.adapter;



import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.databinding.PureAdapterWindowPictureBinding;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.Folder;

public class PictureWindowAdapter extends BindAdapter<Folder> {

    private int seleterPosition=0;
    public int getSeleterPosition() {
        return seleterPosition;
    }

    public void setSeleterPosition(int seleterPosition) {
        this.seleterPosition = seleterPosition;
    }

    public PictureWindowAdapter() {
        addLayout(R.layout.pure_adapter_window_picture);
    }


    @Override
    protected void onData(BindHolder holder, int position, Folder folder,int itemType) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof PureAdapterWindowPictureBinding)
        {
            initBindOne(((PureAdapterWindowPictureBinding) binding),position,folder);
        }
    }

    private void initBindOne(PureAdapterWindowPictureBinding binding, int position, Folder folder) {
        binding.textView.setText(folder.getName());
        binding.viewLine.setVisibility(position==obtainDataCount()-1? View.GONE:View.VISIBLE);
        if (folder.getImageBeanList()!=null)
        {
            ImageLoader.newInstance().init(binding.ratioImageView,folder.getImageBeanList().get(0).getPath());
        }


    }
}
