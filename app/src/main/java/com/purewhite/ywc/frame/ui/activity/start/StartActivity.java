package com.purewhite.ywc.frame.ui.activity.start;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityStartBinding;
import com.purewhite.ywc.frame.ui.activity.MainActivity;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.bar.BarUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePurePresenter;

public class StartActivity extends MvpActivity<ActivityStartBinding, BasePurePresenter> {


    private Animation.AnimationListener animationListener=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            skipActivity(MainActivity.class,-1,null,-1);
            backActivity();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };





    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initBar() {
        BarUtils.obtianBarConfig()
                .with(this)
                .setStatusBar(6)
                .build();
    }

    @Override
    protected void beforeView() {
        super.beforeView();
        setFinishAnimStatue(-1);
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
