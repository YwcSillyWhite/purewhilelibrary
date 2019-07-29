package com.purewhite.ywc.purewhitelibrary.view.bannar;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.view.bannar.adapter.BasePureAdapter;
import com.purewhite.ywc.purewhitelibrary.view.bannar.adapter.StringPureAdapter;
import com.purewhite.ywc.purewhitelibrary.view.bannar.listener.OnPureChangeListener;
import com.purewhite.ywc.purewhitelibrary.view.bannar.palette.PureViewPalette;
import com.purewhite.ywc.purewhitelibrary.view.bannar.trans.PagerTransZoom;

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
            if (currentItem+1<count)
            {
                viewPager.setCurrentItem(currentItem+1);
            }
        }
    };
    private ArgbEvaluator evaluator = new ArgbEvaluator();
    //是否滑动变色
    private boolean run_color;

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
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.PureViewPager);
            autoPlay = typedArray.getBoolean(R.styleable.PureViewPager_autoPlay, true);
            autoTime= typedArray.getInt(R.styleable.PureViewPager_autoTime, 3000);
            run_color = typedArray.getBoolean(R.styleable.PureViewPager_run_color, false);
            if (autoTime<=0)
            {
                autoPlay=false;
            }
            int child_margin = typedArray.getDimensionPixelOffset(R.styleable.PureViewPager_child_margin, 0);
            int parent_margin = typedArray.getDimensionPixelOffset(R.styleable.PureViewPager_parent_margin, 0);

            if (child_margin>0)
            {
                if (child_margin*2>parent_margin)
                {
                    parent_margin=child_margin*3;
                }
                viewPager.setPadding(parent_margin,0,parent_margin,0);
                viewPager.setOffscreenPageLimit(2);
                viewPager.setPageMargin(child_margin);
                viewPager.setClipToPadding(false);
                setClipChildren(false);
            }
            typedArray.recycle();
        }
//        viewPager.setPageTransformer(false,new PagerTransZoom(0.8f));
    }




    public void setAdapter(@NonNull final BasePureAdapter pureAdapter, final OnPureChangeListener onPureChangeListener)
    {
        if (pureAdapter!=null)
        {
            PureViewPalette.newInstance().clear();
            viewPager.setAdapter(pureAdapter);
            pureAdapter.setPalette(true);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (pureAdapter.getCount()>1)
                    {
                        int realPosition = pureAdapter.getRealPosition(position);
                        if (onPureChangeListener!=null)
                        {
                            onPureChangeListener.onPageScrolled(realPosition,positionOffset,positionOffsetPixels);
                        }
                        if (run_color)
                        {
                            int colorNew = PureViewPalette.newInstance().obtianPositionColor(position);
                            int colorNext;
                            if (realPosition==pureAdapter.getRealCount()-1)
                            {
                                colorNext=PureViewPalette.newInstance().obtianPositionColor(0);
                            }
                            else
                            {
                                colorNext=PureViewPalette.newInstance().obtianPositionColor(realPosition+1);
                            }
                            int evaluate = (int) evaluator.evaluate(positionOffset, colorNew, colorNext);
                            setBackgroundColor(evaluate);
                        }

                    }
                }

                @Override
                public void onPageSelected(int position) {
                    if (pureAdapter.getCount()>1)
                    {
                        if (onPureChangeListener!=null)
                        {
                            onPureChangeListener.onPageSelected(pureAdapter.getRealPosition(position));
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (onPureChangeListener!=null)
                    {
                        onPureChangeListener.onPageScrollStateChanged(state);
                    }
                    int count = pureAdapter.getCount();
                    if (count>1)
                    {
                        if (state==ViewPager.SCROLL_STATE_IDLE)
                        {
                            int currentItem = viewPager.getCurrentItem();
                            if (currentItem==0)
                            {
                                viewPager.setCurrentItem(count-2,false);
                            }
                            else if (currentItem==count-1)
                            {
                                viewPager.setCurrentItem(1,false);
                            }
                            play(true);
                        }
                        else
                        {
                            play(false);
                        }
                    }
                }
            });

            if (pureAdapter.getCount()>1)
            {
                viewPager.setCurrentItem(1,false);
                play(true);
            }
        }
    }


    public void setPageTransformer(boolean reverseDrawingOrder, @Nullable ViewPager.PageTransformer transformer)
    {
        viewPager.setPageTransformer(reverseDrawingOrder,transformer);
    }




    public void setAdapter(String[] images,  OnPureChangeListener onPureChangeListener)
    {
        setAdapter(Arrays.asList(images),onPureChangeListener);
    }


    public void setAdapter(List<String> images,   OnPureChangeListener onPureChangeListener)
    {
        setAdapter(new StringPureAdapter(images),onPureChangeListener);
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
