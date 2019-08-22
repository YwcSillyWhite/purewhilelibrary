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

import java.io.Serializable;
import java.util.List;



public class LookPictureActivity extends BaseMvpActivity<PureActivityLookPictureBinding,PresenterImp>{

    private LookPictureAdapter lookPictureAdapter;

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
            {
                view.setSelected(PictureManager.newInstance().isSelector(lookPictureAdapter.obtianPath(currentItem)));
                setViewStatue();
            }

        }
        else if (id==R.id.picture_sure)
        {

        }
    }



    //设置完成和预览的状态
    private void setViewStatue()
    {
        List<String> selectorList = PictureManager.newInstance().getSelectorList();
        if (selectorList.size()>0)
        {
            mDataBinding.pictureSure.setEnabled(true);
            if (selectorList.size()>=PictureManager.newInstance().getImageMax())
            {
                mDataBinding.pictureSure.setText("已完成");
            }
            else
            {
                mDataBinding.pictureSure.setText("已完成 "+selectorList.size()+"/"+PictureManager.newInstance().getImageMax());
            }
        }
        else
        {
            mDataBinding.pictureSure.setEnabled(false);
            mDataBinding.pictureSure.setText("请选择");
        }
    }





    @Override
    protected void initView() {
        Intent intent = getIntent();
        List<ImageBean> imageBeanList=((List<ImageBean>) intent.getSerializableExtra(PictureConfig.imageBean));
        //选中第几个
        int picturePagerPosition = intent.getIntExtra(PictureConfig.picturePagerPosition, 0);
        lookPictureAdapter=new LookPictureAdapter(imageBeanList);
        mDataBinding.viewPager.setAdapter(lookPictureAdapter);
        mDataBinding.viewPager.setCurrentItem(picturePagerPosition);
        setViewStatue();
        mDataBinding.actionBarRight.setSelected(PictureManager.newInstance().isSelector(lookPictureAdapter.obtianPath(picturePagerPosition)));
        mDataBinding.actionBarCenter.setText(picturePagerPosition+1+" / "+lookPictureAdapter.getCount());
        mDataBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mDataBinding.actionBarRight.setSelected(PictureManager.newInstance().isSelector(lookPictureAdapter.obtianPath(position)));
                mDataBinding.actionBarCenter.setText(position+1+" / "+lookPictureAdapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
