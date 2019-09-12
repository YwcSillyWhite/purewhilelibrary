package com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager.base;

import com.purewhite.ywc.purewhitelibrary.adapter.pagerview.BasePagerAdapter;

import java.util.List;

/**
 * 无限pageradpater
 * @param <T>
 */
public abstract class InfinitePagerAdapter<T> extends BasePagerAdapter<T> {

    private boolean infinitePage;
    private int lookCard;
    public InfinitePagerAdapter(List<T> list,boolean infinitePage,int lookCard) {
        super(list);
        this.infinitePage=infinitePage;
        this.lookCard=lookCard;
    }

    /**
     * 无限是否已开启
     * @return
     */
    protected boolean isInfinitePage() {
        int size = super.getCount();
        return size>1&&infinitePage;
    }

    //view的总长度
    protected int viewCount() {
        int size = super.getCount();
        return isInfinitePage()?(1+lookCard/size)*size:size;
    }


    @Override
    protected int viewPosition(int position) {
        return isInfinitePage()?position%viewCount():position;
    }

    @Override
    public int dataPosition(int position) {
        return isInfinitePage()?position%super.getCount():position;
    }

    @Override
    public int getCount() {
        return isInfinitePage()?Integer.MAX_VALUE:super.getCount();
    }

    //初始化位置
    public int initCurrentItem() {
        return isInfinitePage()?super.getCount()*400:0;
    }
}
