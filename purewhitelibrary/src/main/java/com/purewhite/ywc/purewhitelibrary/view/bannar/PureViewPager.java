package com.purewhite.ywc.purewhitelibrary.view.bannar;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.purewhite.ywc.purewhitelibrary.view.bannar.adapter.BasePureAdapter;
import com.purewhite.ywc.purewhitelibrary.view.bannar.adapter.StringPureAdapter;
import com.purewhite.ywc.purewhitelibrary.view.bannar.listener.OnPureChangeListener;

import java.util.Arrays;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class PureViewPager extends FrameLayout {

    private ViewPager viewPager;
    //自动播放时间
    private int autoTime=3000;
    private boolean autoPlay=true;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter instanceof BasePureAdapter)
            {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem+1<adapter.getCount())
                {
                    viewPager.setCurrentItem(currentItem+1);
                }
                else
                {
                    int initPosition = ((BasePureAdapter) adapter).initPosition();
                    viewPager.setCurrentItem(initPosition,false);
                    play(true);
                }
            }
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
    }




    public void setAdapter(@NonNull final BasePureAdapter pureAdapter, final OnPureChangeListener onPureChangeListener)
    {
        if (pureAdapter!=null)
        {
            viewPager.setAdapter(pureAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (onPureChangeListener!=null)
                    {
                        int realPosition = pureAdapter.getRealPosition(position);
                        onPureChangeListener.onPageScrolled(realPosition,position,positionOffset,positionOffsetPixels);
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    if (onPureChangeListener!=null)
                    {
                        int realPosition = pureAdapter.getRealPosition(position);
                        onPureChangeListener.onPageSelected(realPosition,position);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (onPureChangeListener!=null)
                    {
                        onPureChangeListener.onPageScrollStateChanged(state);
                    }
                    if (state==ViewPager.SCROLL_STATE_IDLE)
                    {
                        play(true);
                    }
                    else
                    {
                        play(false);
                    }
                }
            });
            initCenter(pureAdapter);
        }
    }


    //
    public void initCenter(BasePureAdapter pureAdapter)
    {
        int initPosition = pureAdapter.initPosition();
        if (initPosition>0)
        {
            viewPager.setCurrentItem(initPosition,false);
            play(true);
        }
    }


    public void setAdapter(String[] images)
    {
        setAdapter(Arrays.asList(images));
    }


    public void setAdapter(List<String> images)
    {
        setAdapter(new StringPureAdapter(images),null);
    }


    private void play(boolean start)
    {
        if (autoPlay)
        {
            if (start)
            {
                handler.postDelayed(runnable,autoTime);
            }
            else
            {
                handler.removeCallbacks(runnable);
            }
        }
    }

    public void onDestory()
    {
        play(false);
    }
}
