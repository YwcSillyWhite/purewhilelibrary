package com.purewhite.ywc.frame1.ui.fragment.mine;

import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.FragMineBinding;
import com.purewhite.ywc.frame1.ui.activity.mine.CustomMainActivity;
import com.purewhite.ywc.frame1.ui.activity.mine.DialogActivity;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.app.activity.ActivitySkipUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

public class MineFragment extends MvpFragment<FragMineBinding,MinePresenter> implements MineContract.UiView {

    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.bottom_navigation:
                    ActivitySkipUtils.startActivity(CustomMainActivity.class);
                    break;
                case R.id.dialog:
                    ActivitySkipUtils.startActivity(DialogActivity.class);
                    break;
            }
        }
    };
    @Override
    protected MinePresenter creartPresenter() {
        return new MinePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_mine;
    }

    @Override
    protected void initView() {
        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("个人中心");
        mDataBinding.bottomNavigation.setOnClickListener(onSingleListener);
        mDataBinding.dialog.setOnClickListener(onSingleListener);
        ImageLoader.newInstance().initCircle(mDataBinding.headImg,R.mipmap.ic_logo);
    }
}
