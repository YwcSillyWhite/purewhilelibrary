package com.purewhite.ywc.frame1.ui.fragment.mine;

import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.FragMineBinding;
import com.purewhite.ywc.frame1.ui.activity.start.CustomMainActivity;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.app.ActivityUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

public class MineFragment extends MvpFragment<FragMineBinding,MinePresenter> implements MineContract.UiView {

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.bottom_navigation:
                    ActivityUtils.startActivity(CustomMainActivity.class);
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
        ImageLoader.newInstance().initCircle(mDataBinding.headImg,R.mipmap.ic_logo);
    }
}
