package com.purewhite.ywc.purewhitelibrary.mvp.activity;


import android.os.Build;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;

/**
 *过渡动画
 */
public abstract class BaseTransitionActivity extends BaseSkipActivity{


    protected int transitionAnimStatue;
    public void setTransitionAnimStatue(int transitionAnimStatue) {
        this.transitionAnimStatue = transitionAnimStatue;
    }

    @Override
    protected void beforeView() {
        super.beforeView();
        beforeTransitionAnim();
        initTransitionAnim();
    }

    protected void beforeTransitionAnim() {

    }


    private void initTransitionAnim() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP&&transitionAnimStatue>0)
        {
            switch (transitionAnimStatue)
            {
                case 1:
                    Explode explode = new Explode();
                    explode.setDuration(1000L);
                    getWindow().setEnterTransition(explode);
                    break;
                case 2:
                    Slide slide = new Slide(Gravity.BOTTOM);
                    slide.setDuration(1000L);
                    getWindow().setEnterTransition(slide);
                    break;
                case 3:
                    Fade fade = new Fade();
                    fade.setDuration(1000L);
                    getWindow().setEnterTransition(fade);
                    break;
            }
        }
    }
}
