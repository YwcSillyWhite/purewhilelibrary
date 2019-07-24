package com.purewhite.ywc.purewhitelibrary.view.bannar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.viewpager.widget.ViewPager;

import com.purewhite.ywc.purewhitelibrary.view.bannar.adapter.BasePureAdapter;
import com.purewhite.ywc.purewhitelibrary.view.bannar.adapter.StringPureAdapter;

import java.util.Arrays;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class PureViewPager extends FrameLayout {

    private ViewPager viewPager;
    //自动播放时间
    private int autoTime=3000;
    private boolean autoPlay=true;
    private BasePureAdapter pureAdapter;


    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
//            viewPager.setCurrentItem();
        }
    };

    public PureViewPager(Context context) {
        this(context,null);
    }

    public PureViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PureViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        viewPager = new ViewPager(getContext());
        addView(viewPager,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (attrs!=null)
        {

        }
    }










    public void setAdapter(@NonNull final BasePureAdapter adapter)
    {
        if (adapter!=null)
        {

            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                private int nextCurrentItem1;
                private int nextCurrentItem=-1;
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    int count = adapter.getCount();
                    nextCurrentItem=-1;
                    if (count>1)
                    {
                        if (position==0)
                        {
                            nextCurrentItem=count-2;
                        }
                        else if (position==count-1)
                        {
                            nextCurrentItem=1;
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state==ViewPager.SCROLL_STATE_IDLE)
                    {
                        if (nextCurrentItem>0)
                        {
                            viewPager.setCurrentItem(nextCurrentItem,false);
                        }
                    }
                    else
                    {

                    }
                }
            });
        }
    }


    public void setAdapter(String[] images)
    {
        setAdapter(Arrays.asList(images));
    }


    public void setAdapter(List<String> images)
    {
        setAdapter(new StringPureAdapter(images));
    }
}
