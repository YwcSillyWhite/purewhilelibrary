package com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager.base.InfinitePagerAdapter;
import com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager.listener.OnPureChangeListener;

/**
 * 自定义banner ，这里面最主要添加了自动播放时间和是否自动播放，做了一些性能上的优化
 * ，无限放在适配器里做了
 */
public class PureViewPager extends ViewPager {

    private InfinitePagerAdapter infinitePagerAdapter;
    //自动播放时间
    private int autoTime=3000;
    //是否可以自动播放
    private boolean autoPlay=true;
    public void setAutoTime(int autoTime) {
        this.autoTime = autoTime;
    }

    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
    }
    private OnPureChangeListener onPureChangeListener;
    public void setOnPureChangeListener(OnPureChangeListener onPureChangeListener) {
        this.onPureChangeListener = onPureChangeListener;
    }
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (autoPlay)
            {
                nextCurrentItem();
            }
        }
    };

    public PureViewPager(@NonNull Context context) {
        this(context,null);
    }

    public PureViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PureViewPager);
            autoTime=typedArray.getInt(R.styleable.PureViewPager_autoTime,autoTime);
            autoPlay=typedArray.getBoolean(R.styleable.PureViewPager_autoPlay,autoPlay);
            typedArray.recycle();
        }


        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (onPureChangeListener!=null&&isMove())
                {
                    onPureChangeListener.onPageScrolled(infinitePagerAdapter.dataPosition(position),positionOffset,positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (onPureChangeListener!=null&&isMove())
                {
                    onPureChangeListener.onPageSelected(infinitePagerAdapter.dataPosition(position));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                statueAuto(state==ViewPager.SCROLL_STATE_IDLE);
                if (onPureChangeListener!=null&&isMove())
                {
                    onPureChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
    }


    @Override
    public void setAdapter(@Nullable PagerAdapter adapter) {
        if (adapter!=null&&adapter instanceof InfinitePagerAdapter)
        {
            this.infinitePagerAdapter= ((InfinitePagerAdapter) adapter);
            super.setAdapter(adapter);
        }

    }


    //下一个position
    public void nextCurrentItem() {
        if (isMove())
        {
            int currentItem = getCurrentItem();
            int nextPosition = currentItem+1<infinitePagerAdapter.getCount()?currentItem+1:0;
            setCurrentItem(nextPosition);
        }

    }



    public void initCurrentIemt(){
        if (isMove())
        {
            int position = infinitePagerAdapter.initCurrentItem();
            setCurrentItem(position,false);
            statueAuto(true);
        }
    }

    //改变自动状态
    public void statueAuto(boolean isStart) {
        if (isMove()&&autoPlay)
        {
            handler.removeCallbacks(runnable);
            if (isStart) {
                handler.postDelayed(runnable, autoTime);
            }
        }
    }

    //根据activity生命周期变化，也是一种优化
    public void onResume() {
        statueAuto(true);
    }

    //跟哭activity生命周期变化，也是一种优化
    public void onPause() {
        statueAuto(false);
    }


    //判断是否可以移动
    private boolean isMove() {
        if (infinitePagerAdapter!=null) {
            int count = infinitePagerAdapter.getCount();
            return count>1;
        }
        return false;
    }

}
