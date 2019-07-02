package com.purewhite.ywc.frame.ui.activity.mine;

import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityTransitionBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

/**
 *
 * 和overridePendingTransition类似,设置跳转时候的进入动画和退出动画
 * public static ActivityOptions makeCustomAnimation(Context context,int enterResId,int exitResId);
 * 通过把要进入的Activity通过放大的效果过渡进去
 * 举一个简单的例子来理解source=view,startX=view.getWidth(),startY=view.getHeight(),startWidth=0,startHeight=0
 * 表明新的Activity从view的中心从无到有慢慢放大的过程
 * public static ActivityOptions makeScaleUpAnimation(View source,int startX,int startY,int width,int height);
 * 通过放大一个图片过渡到新的Activity
 * public static ActivityOptions makeThumbnailScaleUpAnimation(View source,Bitmap thumbnail,int startX,int startY);
 * 场景动画，体现在两个Activity中的某些view协同去完成过渡动画效果，等下在例子中能更好的看到效果
 * public static ActivityOptions makeSceneTransitionAnimation(Activity activity,View sharedElement,String sharedElementName);
 * 场景动画，同上是对多个View同时起作用
 * public static ActivityOptions makeSceneTransitionAnimation(Activity activity,android.util.Pair<View, String>...sharedElements);
 */
public class TransitionActivity extends MvpActivity<ActivityTransitionBinding,PresenterImp> implements View.OnClickListener {

    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    @Override
    public void onClick(View view) {
        if (!ClickUtils.clickable(view))
            return;
        switch (view.getId())
        {
            case R.id.explode:
                break;
            case R.id.fade:
                break;
            case R.id.slide:
                break;
        }
    }

    @Override
    protected void beforeTransitionAnim() {
        super.beforeTransitionAnim();
        setTransitionAnimStatue(3);
    }

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_transition;
    }

    @Override
    protected void initView() {
        mDataBinding.explode.setOnClickListener(this);
        mDataBinding.fade.setOnClickListener(this);
        mDataBinding.slide.setOnClickListener(this);
    }


}
