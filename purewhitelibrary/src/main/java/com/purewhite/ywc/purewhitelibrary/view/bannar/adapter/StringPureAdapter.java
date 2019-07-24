package com.purewhite.ywc.purewhitelibrary.view.bannar.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

import java.util.List;

public class StringPureAdapter extends BasePureAdapter<String>{

    public StringPureAdapter(List<String> list) {
        super(list);
    }

    @Override
    public View obtianView(ViewGroup container, int position, String s) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.newInstance().init(imageView,s);
        return imageView;
    }
}
