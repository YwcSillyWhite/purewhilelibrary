package com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.SizeUtils;
import com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager.listener.OnPureChangeListener;

import io.reactivex.annotations.NonNull;

/**
 * 带下标的banner
 */
public class PureViewPagerLayout extends RelativeLayout {

    public PureViewPager pureViewPager;
    private LinearLayout.LayoutParams paramsView;
    private LinearLayout linearLayout;
    private int cricleDraw = R.drawable.pure_selector_cricle;

    public PureViewPagerLayout(Context context) {
        this(context, null);
    }

    public PureViewPagerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PureViewPagerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //viewpager
        pureViewPager = new PureViewPager(getContext());
        setParamsViewPager(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(pureViewPager);


        //linearLayout
        linearLayout = new LinearLayout(getContext());
        LayoutParams paramsLinear = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsLinear.addRule(ALIGN_PARENT_BOTTOM);
        paramsLinear.addRule(CENTER_HORIZONTAL);
        paramsLinear.bottomMargin= SizeUtils.dip2px(10);
        setParamsLinearLayout(paramsLinear,LinearLayout.HORIZONTAL);
        addView(linearLayout);


        setParamsLinearView(SizeUtils.dip2px(15), SizeUtils.dip2px(4), SizeUtils.dip2px(4),0,0);


        pureViewPager.setOnPureChangeListener(new OnPureChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int childCount = linearLayout.getChildCount();
                if (position < childCount) {
                    for (int i = 0; i < childCount; i++) {
                        View viewChild = linearLayout.getChildAt(i);
                        viewChild.setSelected(position == i);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int statue) {

            }
        });
    }


    public PureViewPagerLayout setCricleDraw(int cricleDraw) {
        this.cricleDraw = cricleDraw;
        return this;
    }

    /**
     * 设置viewpager
     * @param paramsView
     */
    public PureViewPagerLayout setParamsViewPager(@NonNull ViewGroup.LayoutParams paramsView) {
        pureViewPager.setLayoutParams(paramsView);
        return this;
    }


    /**
     * 设置LinearLayout
     * @param paramsView
     */
    public PureViewPagerLayout setParamsLinearLayout(@NonNull ViewGroup.LayoutParams paramsView, int orientation) {
        linearLayout.setLayoutParams(paramsView);
        linearLayout.setOrientation(orientation);
        return this;
    }


    /**
     * 设置下标view
     * @param diameter 直接
     * @param left   左
     * @param right  右
     * @param top    上
     * @param bottom 下
     */
    public PureViewPagerLayout setParamsLinearView(int diameter, int left, int right, int top, int bottom) {
        paramsView=new LinearLayout.LayoutParams(diameter, diameter);
        paramsView.leftMargin=left;
        paramsView.rightMargin=right;
        paramsView.topMargin=top;
        paramsView.bottomMargin=bottom;
        return this;
    }



    //设置下标布局
    public PureViewPagerLayout setLayoutView(int size, int selectorPosition) {
        linearLayout.removeAllViews();
        if (size>1)
        {
            for (int i = 0; i < size; i++)
            {
                View view = new View(getContext());
                view.setSelected(i==selectorPosition);
                view.setBackgroundResource(cricleDraw);
                linearLayout.addView(view,paramsView);
            }
        }
        return this;
    }



}
