package com.purewhite.ywc.purewhitelibrary.view.bannar.viewpager.listener;


public interface  OnPureChangeListener  {

    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    void onPageSelected(int position);

    void onPageScrollStateChanged(int statue);
}
