package com.purewhite.ywc.frame1.ui.activity;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.ActivityStartBinding;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.app.ActivityUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class StartActivity extends MvpActivity<ActivityStartBinding,PresenterImp> {

    private Animation.AnimationListener animationListener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            ActivityUtils.startActivity(MainActivity.class);
            ActivityUtils.finish(StartActivity.this);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        initStartAnim();
    }



    /**
     * 启动动画
     */
    private void initStartAnim() {
        // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.4f, 1.0f);
        aa.setDuration(2000);
        aa.setAnimationListener(animationListener);
        mDataBinding.relativeLayout.startAnimation(aa);


        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(1000);
        mDataBinding.imageView.startAnimation(sa);

        RotateAnimation ra = new RotateAnimation(180, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1000);
        mDataBinding.textView.startAnimation(ra);
    }
}
