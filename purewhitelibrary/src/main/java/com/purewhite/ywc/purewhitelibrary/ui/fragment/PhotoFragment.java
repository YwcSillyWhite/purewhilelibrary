package com.purewhite.ywc.purewhitelibrary.ui.fragment;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.databinding.FragmentPhotoBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.fragment.MvpPureFragment;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

/**
 * 相机fragment
 * @author yuwenchao
 */
public class PhotoFragment extends MvpPureFragment<FragmentPhotoBinding,PresenterImp>{
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_photo;
    }

    @Override
    protected void initView() {

    }
}
