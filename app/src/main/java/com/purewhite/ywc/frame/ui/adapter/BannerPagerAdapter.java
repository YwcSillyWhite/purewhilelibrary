package com.purewhite.ywc.frame.ui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager.base.InfinitePagerAdapter;

import java.util.List;

public class BannerPagerAdapter extends InfinitePagerAdapter<String> {


    public BannerPagerAdapter(List<String> list,boolean infinitePage,int lookCard) {
        super(list,infinitePage,lookCard);
    }

    @Override
    protected int getLayoutId(int position) {
        return R.layout.adapter_pager_banner;
    }

    @Override
    protected void onData(View view, int position, String s) {
        ImageView imageView = view.findViewById(R.id.image_view);
        ImageLoader.newInstance().init(imageView,s);
    }
}
