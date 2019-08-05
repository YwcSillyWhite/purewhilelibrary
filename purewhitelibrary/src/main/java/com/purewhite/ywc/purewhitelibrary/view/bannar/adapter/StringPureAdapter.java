package com.purewhite.ywc.purewhitelibrary.view.bannar.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

import java.util.List;

public class StringPureAdapter extends BasePureAdapter<String>{


    public StringPureAdapter(List<String> list) {
        super(list);
    }

    public StringPureAdapter(List<String> list, int cardNum) {
        super(list, cardNum);
    }

    @Override
    public View obtianView(ViewGroup container, int position, String s) {
        LogUtils.debug("obtianView"+position);
        ImageView imageView = new ImageView(container.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setImageView(imageView,s,position);
        return imageView;
    }
}
