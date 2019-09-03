package com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager.trans;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class PagerTransZoom implements ViewPager.PageTransformer {

    private float zoom;
    public PagerTransZoom(float zoom) {
        this.zoom = zoom;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (zoom>0&&zoom<1)
        {
            if (position < -1) {
                position = -1;
            } else if (position > 1) {
                position = 1;
            }
            float tempScale = position < 0 ? -position :  position;
            float scaleValue = 1 + tempScale*(zoom-1);
            page.setScaleX(scaleValue);
            page.setScaleY(scaleValue);
        }
    }

}
