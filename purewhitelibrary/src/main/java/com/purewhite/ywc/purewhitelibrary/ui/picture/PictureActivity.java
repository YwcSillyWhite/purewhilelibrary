package com.purewhite.ywc.purewhitelibrary.ui.picture;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityPictureBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.PictureAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.picture.config.PictureStype;
import com.purewhite.ywc.purewhitelibrary.ui.picture.manager.PicSeletorManager;
import com.purewhite.ywc.purewhitelibrary.view.recyclerview.AroundItemDecoration;

import java.util.List;

/**
 * @author yuwenchao
 */
public class PictureActivity extends BaseMvpActivity<PureActivityPictureBinding,PicturePresenter>
        implements PictureContract.ViewUi {

    private PictureAdapter pictureAdapter;
    //获取类型
    private int style_request;
    //选择最大图片
    private int stype_pic_num;

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
        Intent intent = getIntent();
        style_request = intent.getIntExtra(PictureStype.STYPE_REQUEST, PictureStype.STYPE_PIC_REQUEST);
        if (style_request==PictureStype.STYPE_PIC_REQUEST)
        {
            stype_pic_num = intent.getIntExtra(PictureStype.STYPE_PIC_NUM, 0);
            if (stype_pic_num>0)
            {
                PicSeletorManager.newInstance().setPicSize(stype_pic_num);
            }
        }


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
