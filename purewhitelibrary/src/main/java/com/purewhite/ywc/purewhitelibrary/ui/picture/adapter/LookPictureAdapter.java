package com.purewhite.ywc.purewhitelibrary.ui.picture.adapter;

import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.pagerview.BasePagerAdapter;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureManager;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;

public class LookPictureAdapter extends BasePagerAdapter<ImageBean> {

    //预览图片
    private List<String> previewImage=new ArrayList<>();

    private boolean isPreview()
    {
        List<ImageBean> imageBeanList = obtainData();
        return imageBeanList==null||imageBeanList.size()==0;
    }

    public LookPictureAdapter() {
        super(PictureManager.newInstance().getList());
        if (isPreview())
        {
            this.previewImage.addAll(PictureManager.newInstance().getSelectorList());
        }
    }

    @Override
    public int getCount() {
        return isPreview()?previewImage.size():super.getCount();
    }


    public String obtianPath(int position)
    {
        if (position<getCount())
        {
            return isPreview()?previewImage.get(position):obtainT(position).getPath();
        }
        return "";
    }


    protected ImageBean obtainT(int position) {
        return isPreview()?null:super.obtainT(position);
    }

    @Override
    protected int getLayoutId(int position) {
        return R.layout.pure_adapter_look_picture;
    }

    @Override
    protected void onData(View view, int position, ImageBean imageBean) {
        final PhotoView photoView = (PhotoView) view.findViewById(R.id.photo_view);
        ImageLoader.newInstance().init(photoView,obtianPath(position));
    }
}
