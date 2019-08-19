package com.purewhite.ywc.purewhitelibrary.ui.picture.activity;

import android.content.Intent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityLookPictureBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureConfig;
import com.purewhite.ywc.purewhitelibrary.ui.picture.PictureManager;
import com.purewhite.ywc.purewhitelibrary.ui.picture.adapter.LookPictureAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;

import java.util.List;



public class LookPictureActivity extends BaseMvpActivity<PureActivityLookPictureBinding,PresenterImp>{

    private LookPictureAdapter lookPictureAdapter;

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.pure_activity_look_picture;
    }

    @Override
    protected void onClickUtils(View view) {
        final int id = view.getId();
        if (id==R.id.action_bar_right)
        {
            int currentItem = mDataBinding.viewPager.getCurrentItem();
            if (PictureManager.newInstance().alterImage(lookPictureAdapter.obtianPath(currentItem)))
                view.setSelected(PictureManager.newInstance().isSelector(lookPictureAdapter.obtianPath(currentItem)));
        }
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        boolean pictureIsPreview = intent.getBooleanExtra(PictureConfig.pictureIsPreview, false);
        List<ImageBean> imageBeanList = ((List<ImageBean>) intent.getSerializableExtra(PictureConfig.imageBean));
        int picturePagerPosition = intent.getIntExtra(PictureConfig.picturePagerPosition, 0);
        lookPictureAdapter=new LookPictureAdapter(imageBeanList,pictureIsPreview);
        mDataBinding.viewPager.setAdapter(lookPictureAdapter);
        mDataBinding.viewPager.setCurrentItem(picturePagerPosition);
        mDataBinding.actionBarRight.setSelected(PictureManager.newInstance().isSelector(lookPictureAdapter.obtianPath(picturePagerPosition)));
        mDataBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mDataBinding.actionBarRight.setSelected(PictureManager.newInstance().isSelector(lookPictureAdapter.obtianPath(position)));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
