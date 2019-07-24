package com.purewhite.ywc.purewhitelibrary.view.bannar.listener;


public interface  OnPureChangeListener  {

    void onPageScrolled(int realPosition,int position,float positionOffset,int positionOffsetPixels);

    void onPageSelected(int realPosition,int position);

    void onPageScrollStateChanged(int statue);
}
