package com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager.listener.OnPureChangeListener;
import com.purewhite.ywc.purewhitelibrary.window.utils.WindowPureUtils;

public class PureViewPagerLayout extends RelativeLayout {

    public PureViewPager pureViewPager;
    private LayoutParams paramsViewPager, paramsLinear;
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
        pureViewPager = new PureViewPager(getContext());
        paramsViewPager = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(pureViewPager, paramsViewPager);

        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        paramsLinear = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsLinear.addRule(ALIGN_PARENT_BOTTOM);
        paramsLinear.bottomMargin=5;
        addView(linearLayout, paramsLinear);



        paramsView=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsView.width=50;
        paramsView.height=50;
        paramsView.rightMargin=5;
        paramsView.leftMargin=5;


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


    public void setLayoutView(int size,int selectorPosition) {
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

    }



}
