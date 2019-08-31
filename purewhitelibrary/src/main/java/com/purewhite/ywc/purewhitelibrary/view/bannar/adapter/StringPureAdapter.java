package com.purewhite.ywc.purewhitelibrary.view.bannar.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

import java.util.List;

public class StringPureAdapter extends BasePureAdapter<String>{


    private float scale;
    public StringPureAdapter(List<String> list) {
        super(list);
    }

    public StringPureAdapter(List<String> list, int cardNum,boolean isImagexPalette,float scale) {
        super(list, cardNum,isImagexPalette);
        this.scale=scale;
    }

    @Override
    public View obtianView(ViewGroup container, int realPosition, String s) {
        ImageView imageView = new ImageView(container.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setImageView(imageView,s,realPosition);
        if (scale>0&&scale<1)
        {
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
        }
        return imageView;
    }


}
