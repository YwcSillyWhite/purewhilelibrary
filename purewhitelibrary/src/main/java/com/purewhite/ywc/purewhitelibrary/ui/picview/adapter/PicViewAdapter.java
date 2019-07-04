package com.purewhite.ywc.purewhitelibrary.ui.picview.adapter;

import android.databinding.ViewDataBinding;
import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;
import com.purewhite.ywc.purewhitelibrary.databinding.PureAdapterPicViewBinding;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.picture.manager.PicSeletorManager;

import java.util.List;

/**
 * @author yuwenchao
 */
public class PicViewAdapter extends BindAdapter<String> {
    private String path;
    public PicViewAdapter() {
        addLayout(R.layout.pure_adapter_pic_view);
    }

    @Override
    protected void onData(BindHolder holder, int position, String imageBean) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof PureAdapterPicViewBinding)
        {
            initOne(((PureAdapterPicViewBinding) binding),position,imageBean);
        }
    }

    private void initOne(PureAdapterPicViewBinding binding, final int position, final String imageBean) {
        ImageLoader.newInstance().init(binding.imageView,imageBean);
        if (TextUtils.equals(imageBean,path))
        {
            binding.fragmeLayout.setSelected(true);
        }
        else
        {
            binding.fragmeLayout.setSelected(false);
        }
    }


    //返回值代表是否选中
    public boolean flush(String path)
    {
        final List<String> listPath = PicSeletorManager.newInstance().getListPath();
        this.path=path;
        super.flush(listPath,true);
        return listPath.contains(path);
    }
}
