package com.purewhite.ywc.purewhitelibrary.view.bannar;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.view.bannar.adapter.BasePureAdapter;
import com.purewhite.ywc.purewhitelibrary.view.bannar.adapter.StringPureAdapter;
import com.purewhite.ywc.purewhitelibrary.view.bannar.listener.OnPureChangeListener;
import com.purewhite.ywc.purewhitelibrary.view.bannar.palette.PureViewPalette;

import java.util.Arrays;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class PureViewPager extends RelativeLayout {

    private ViewPager viewPager;
    //自动播放时间
    private int autoTime=3000;
    private boolean autoPlay=true;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            int count = viewPager.getAdapter().getCount();
            int currentItem = viewPager.getCurrentItem();
            if (count>1)
            {
                if (currentItem==Integer.MAX_VALUE)
                {
                    initCurrenItem();
                }
                else
                {
                    viewPager.setCurrentItem(currentItem+1);
                }
            }
        }
    };
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
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
        if (attrs!=null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PureViewPager);
            autoPlay = typedArray.getBoolean(R.styleable.PureViewPager_autoPlay, true);
            autoTime = typedArray.getInt(R.styleable.PureViewPager_autoTime, 3000);
            if (autoTime <= 0) {
                autoPlay = false;
            }
            int child_margin = typedArray.getDimensionPixelOffset(R.styleable.PureViewPager_child_margin, 0);
            boolean pager_clip = typedArray.getBoolean(R.styleable.PureViewPager_pager_clip, true);
            if (child_margin!=0)
            {
                viewPager.setPageMargin(child_margin);
            }
            viewPager.setClipChildren(pager_clip);
            viewPager.setClipToPadding(pager_clip);
            typedArray.recycle();
        }
    }




    public void setAdapter(@NonNull final BasePureAdapter pureAdapter, final OnPureChangeListener onPureChangeListener)
    {
        if (pureAdapter!=null)
        {
            PureViewPalette.newInstance().clear();
            viewPager.setAdapter(pureAdapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    int realPosition = pureAdapter.getRealPosition(position);
                    if (onPureChangeListener!=null)
                    {
                        onPureChangeListener.onPageScrolled(realPosition,positionOffset,positionOffsetPixels);
                    }
                    if (pureAdapter.isImagexPalette())
                    {
                        int positionColor = PureViewPalette.newInstance().obtianPositionColor(realPosition);
                        int nextColor=pureAdapter.isRealTop(realPosition)? PureViewPalette.newInstance().obtianPositionColor(0):
                                PureViewPalette.newInstance().obtianPositionColor(realPosition+1);
                        int currentLastColor = (int) (argbEvaluator.evaluate(positionOffset, positionColor, nextColor));
                        setBackgroundColor(currentLastColor);
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    if (onPureChangeListener!=null)
                    {
                        onPureChangeListener.onPageSelected(pureAdapter.getRealPosition(position));
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (onPureChangeListener!=null)
                    {
                        onPureChangeListener.onPageScrollStateChanged(state);
                    }
                    final int count = pureAdapter.getCount();
                    if (count>1)
                    {
                        if (state==ViewPager.SCROLL_STATE_IDLE)
                        {
                            play(true);
                        }
                        else
                        {
                            play(false);
                        }
                    }
                }
            });
            initCurrenItem();
        }
    }


    //初始化位置
    public void initCurrenItem()
    {
        PagerAdapter adapter = viewPager.getAdapter();
        if (adapter!=null&&adapter.getCount()>1&&adapter instanceof BasePureAdapter)
        {
            int initPosition = ((BasePureAdapter) adapter).initPosition();
            viewPager.setCurrentItem(initPosition,false);
            int realPosition = ((BasePureAdapter) adapter).getRealPosition(initPosition);
            setBackgroundColor( PureViewPalette.newInstance().obtianPositionColor(realPosition));
            play(true);

        }
    }

    public void setPageTransformer(boolean reverseDrawingOrder, @Nullable ViewPager.PageTransformer transformer)
    {
        viewPager.setPageTransformer(reverseDrawingOrder,transformer);
    }







    private void play(boolean start)
    {
        if (autoPlay) {
            handler.removeCallbacks(runnable);
            if (start) {
                handler.postDelayed(runnable, autoTime);
            }
        }
    }


    public void onResume()
    {
        play(true);
    }


    public void onPause()
    {
        play(false);
    }

}
