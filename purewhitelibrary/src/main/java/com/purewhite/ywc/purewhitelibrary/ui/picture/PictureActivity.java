package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.support.v7.widget.GridLayoutManager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityPictureBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.MvpPureActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.PictureAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.view.recyclerview.AroundItemDecoration;

import java.util.List;

/**
 * @author yuwenchao
 */
public class PictureActivity extends MvpPureActivity<PureActivityPictureBinding,PicturePresenter>
        implements PictureContract.ViewUi {

    private PictureAdapter pictureAdapter;
    @Override
    protected void beforeView() {
        super.beforeView();
        BarUtils.obtianBarConfig().with(this)
                .setStatusBarHideFlag()
                .setStatusBarTextColorFlag(true)
                .build();
    }

    @Override
    protected void afterView() {
        super.afterView();
        BarUtils.obtianTitleConfig().setTitleBarPadding(mDataBinding.actionLayout);
    }

    @Override
    protected PicturePresenter creartPresenter() {
        return new PicturePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.pure_activity_picture;
    }

    @Override
    protected void initView() {
        pictureAdapter = new PictureAdapter();
        mDataBinding.recyclerView.setAdapter(pictureAdapter);
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mDataBinding.recyclerView.addItemDecoration(new AroundItemDecoration(getResources()
                .getDimensionPixelOffset(R.dimen.dp_1)));
    }

    @Override
    protected void initRquest() {
        super.initRquest();
        mPresenter.gainImg();
    }

    @Override
    public void obtianListFolder(List<Folder> folders) {
        if (folders!=null&&folders.size()>0)
        {
            pictureAdapter.flush(folders.get(0).getImageBeanList());

        }
    }
}
