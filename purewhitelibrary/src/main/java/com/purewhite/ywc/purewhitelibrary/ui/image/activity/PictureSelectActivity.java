package com.purewhite.ywc.purewhitelibrary.ui.image.activity;

import androidx.recyclerview.widget.GridLayoutManager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityPictureSelectBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.ui.image.PictureConfig;
import com.purewhite.ywc.purewhitelibrary.ui.image.adapter.PictureSelectAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.Folder;
import com.purewhite.ywc.purewhitelibrary.ui.image.bean.PictureBean;
import com.purewhite.ywc.purewhitelibrary.ui.image.contract.PictureSelectContract;
import com.purewhite.ywc.purewhitelibrary.ui.image.presenter.PictureSelectPresenter;

import java.util.List;

public class PictureSelectActivity extends BaseMvpActivity<PureActivityPictureSelectBinding, PictureSelectPresenter>
        implements PictureSelectContract.UiView {

    private PictureBean pictureBean;
    private PictureSelectAdapter pictureSelectAdapter;

    @Override
    protected PictureSelectPresenter creartPresenter() {
        return new PictureSelectPresenter();
    }


    @Override
    protected int getLayout() {
        return R.layout.pure_activity_picture_select;
    }

    @Override
    protected void initView() {
        pictureBean = ((PictureBean) getIntent().getSerializableExtra(PictureConfig.pictureBean));
        pictureSelectAdapter = new PictureSelectAdapter();
        pictureSelectAdapter.setSelectList(pictureBean.getImageList());
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,pictureBean.getLineNum()));
        mDataBinding.recyclerView.setAdapter(pictureSelectAdapter);
    }


    @Override
    protected void initRquest() {
        super.initRquest();
        mPresenter.requestList();
    }


    @Override
    public void responList(List<Folder> folderList) {
        pictureSelectAdapter.flush(folderList,0);
    }
}
