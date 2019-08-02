package com.purewhite.ywc.purewhitelibrary.view.bannar.palette;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.SparseIntArray;

import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import com.purewhite.ywc.purewhitelibrary.config.LogUtils;

public class PureViewPalette {

    private static PureViewPalette pureViewPalette;
    private SparseIntArray sparseIntArray=new SparseIntArray();
    public static PureViewPalette newInstance() {
        if (pureViewPalette==null)
        {
            synchronized (PureViewPalette.class)
            {
                if (pureViewPalette==null)
                {
                    pureViewPalette=new PureViewPalette();
                }
            }
        }
        return pureViewPalette;
    }


    public int obtianPositionColor(int position)
    {
        int color = sparseIntArray.get(position);
        if (color==0)
        {
            return Color.TRANSPARENT;
        }
        return color;
    }


    public void putColor(int position,int color)
    {
        sparseIntArray.append(position,color);
    }

    /**
     *  // 获取到柔和的深色的颜色（可传默认值）
     *  palette.getDarkMutedColor(Color.BLUE);
     *  // 获取到活跃的深色的颜色（可传默认值）
     *  palette.getDarkVibrantColor(Color.BLUE);
     *  // 获取到柔和的明亮的颜色（可传默认值）
     *  palette.getLightMutedColor(Color.BLUE);
     *  // 获取到活跃的明亮的颜色（可传默认值）
     *  palette.getLightVibrantColor(Color.BLUE);
     *  // 获取图片中最活跃的颜色（也可以说整个图片出现最多的颜色）（可传默认值）
     *  palette.getVibrantColor(Color.BLUE);
     *  // 获取图片中一个最柔和的颜色（可传默认值）
     *  palette.getMutedColor(Color.BLUE);
     * @param position
     * @param bitmap
     */
    public void putColor(final int position, Bitmap bitmap)
    {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {


                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                int rgb = vibrantSwatch.getRgb();
                LogUtils.debug(rgb+"颜色");
                sparseIntArray.append(position,rgb);
            }
        });
    }


    public void clear()
    {
        sparseIntArray.clear();
    }

}
