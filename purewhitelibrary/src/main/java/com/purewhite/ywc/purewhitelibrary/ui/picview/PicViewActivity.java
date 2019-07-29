package com.purewhite.ywc.purewhitelibrary.ui.picview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.databinding.PureActivityPicViewBinding;
import com.purewhite.ywc.purewhitelibrary.mvp.activity.BaseMvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.ui.picture.bean.ImageBean;
import com.purewhite.ywc.purewhitelibrary.ui.picture.config.PictureStype;
import com.purewhite.ywc.purewhitelibrary.ui.picture.manager.PicSeletorManager;
import com.purewhite.ywc.purewhitelibrary.ui.picview.adapter.PicViewAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picview.adapter.PicViewPagerAdapter;
import com.purewhite.ywc.purewhitelibrary.ui.picview.io.PhotoClickListener;

import java.util.List;

public class PicViewActivity extends BaseMvpActivity<PureActivityPicViewBinding,PresenterImp>
        implements ViewPager.OnPageChangeListener,View.OnClickListener, PhotoClickListener {

    private List<ImageBean>  imageBeanList;
    private PicViewAdapter picViewAdapter;

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mDataBinding.actionBar.actionBarLeftText.setText((i+1)+" / "+imageBeanList.size());
        //刷新
        boolean flush = picViewAdapter.flush(imageBeanList.get(i).getPath());
        mDataBinding.picViewSelectorImg.setSelected(flush);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    @Override
    protected void beforeView() {
        super.beforeView();
        BarUtils.obtianBarConfig().with(this)
                .setStatusBar(1)
                .setStatusBarTextColorFlag(true)
                .build();
    }

    @Override
    protected int getTitleBarId() {
        return R.id.action_bar_layout;
    }


    @Override
    public void onClick(View v) {
        if (!ClickUtils.clickable(v))
            return;
        final int id = v.getId();
        if (id==R.id.action_bar_left_img)
        {
            backActivity();
        }
        else if (id==R.id.pic_selector)
        {
            final int currentItem = mDataBinding.viewPager.getCurrentItem();
            if (currentItem<imageBeanList.size())
            {
                ImageBean imageBean = imageBeanList.get(currentItem);
                if (PicSeletorManager.newInstance().solvePic(imageBean.getPath()))
                {
                    picViewAdapter.flush(imageBean.getPath());
                    boolean selected = mDataBinding.picViewSelectorImg.isSelected();
                    mDataBinding.picViewSelectorImg.setSelected(!selected);
                    upDataSure();
                }
            }

        }
    }


    @Override
    public void click(boolean selected) {
        if (selected)
        {
            TranslateAnimation translateAnimationTop = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0
                    ,Animation.RELATIVE_TO_SELF,-1,Animation.RELATIVE_TO_SELF,0);
            translateAnimationTop.setDuration(200);
            translateAnimationTop.setFillAfter(true);
            mDataBinding.actionBar.actionBarLayout.startAnimation(translateAnimationTop);


            TranslateAnimation translateAnimationBottom = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0
                    ,Animation.RELATIVE_TO_SELF,1,Animation.RELATIVE_TO_SELF,0);
            translateAnimationBottom.setDuration(200);
            translateAnimationBottom.setFillAfter(true);
            mDataBinding.bottomLinearLayout.startAnimation(translateAnimationBottom);
        }
        else
        {
            TranslateAnimation translateAnimationTop = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0
                    ,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1);
            translateAnimationTop.setDuration(200);
            translateAnimationTop.setFillAfter(true);
            mDataBinding.actionBar.actionBarLayout.startAnimation(translateAnimationTop);


            TranslateAnimation translateAnimationBottom = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0
                    ,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,1);
            translateAnimationBottom.setDuration(200);
            translateAnimationBottom.setFillAfter(true);
            mDataBinding.bottomLinearLayout.startAnimation(translateAnimationBottom);
        }
    }



    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.pure_activity_pic_view;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        imageBeanList = (List<ImageBean>) bundle.getSerializable(PictureStype.SKIP_PIC_LIST);
        int position = bundle.getInt(PictureStype.SKIP_PIC_LIST_POSITION, 0);
        if (imageBeanList==null||imageBeanList.size()==0)
        {
            finish();
        }
        else
        {
            //viewpager
            PicViewPagerAdapter picViewPagerAdapter = new PicViewPagerAdapter(imageBeanList,this);
            mDataBinding.viewPager.setAdapter(picViewPagerAdapter);
            mDataBinding.viewPager.setCurrentItem(position);
            mDataBinding.viewPager.addOnPageChangeListener(this);
            mDataBinding.actionBar.actionBarLeftImg.setOnClickListener(this);
            mDataBinding.picSelector.setOnClickListener(this);
            mDataBinding.actionBar.actionBarLayout.setBackgroundColor(Color.parseColor("#55444444"));
            mDataBinding.actionBar.actionBarLeftText.setText(position+" / "+imageBeanList.size());

            //recyclerview
            picViewAdapter = new PicViewAdapter();
            mDataBinding.recyclerView.setAdapter(picViewAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mDataBinding.recyclerView.setLayoutManager(linearLayoutManager);
            boolean flush = picViewAdapter.flush(imageBeanList.get(position).getPath());
            mDataBinding.picViewSelectorImg.setSelected(flush);

        }


    }


    @Override
    protected void initRquest() {
        super.initRquest();
        upDataSure();
    }

    //更新sure空间
    private void upDataSure()
    {
        if (PicSeletorManager.newInstance().obtainPicCount()>0)
        {
            mDataBinding.actionBar.actionBarSure.setEnabled(true);
            mDataBinding.actionBar.actionBarSure.setText(PicSeletorManager.newInstance().obtainPicContent()+"完成");
        }
        else
        {
            mDataBinding.actionBar.actionBarSure.setEnabled(false);
            mDataBinding.actionBar.actionBarSure.setText(PicSeletorManager.newInstance().obtainPicContent()+"完成");
        }

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataBinding.viewPager.removeOnPageChangeListener(this);
    }



}
