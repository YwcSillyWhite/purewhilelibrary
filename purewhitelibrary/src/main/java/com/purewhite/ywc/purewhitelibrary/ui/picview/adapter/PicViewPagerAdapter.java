package com.purewhite.ywc.purewhitelibrary.ui.picview.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.pagerview.BasePagerAdapter;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.picview.io.PhotoClickListener;

import java.util.List;

public class PicViewPagerAdapter extends BasePagerAdapter<ImageBean> {

    private PhotoClickListener photoClickListener;
    public PicViewPagerAdapter(List<ImageBean> list, PhotoClickListener photoClickListener) {
        super(list);
        this.photoClickListener=photoClickListener;
    }

    @Override
    protected View obtainView(ViewGroup container, int position, ImageBean imageBean) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.pure_adapter_pager_pic_view, container, false);
        final PhotoView photoView = (PhotoView) view.findViewById(R.id.photo_view);
        ImageLoader.newInstance().init(photoView,imageBean.getPath());
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean selected = v.isSelected();
                photoClickListener.click(selected);
                v.setSelected(!selected);
            }
        });
        return view;
    }
}
