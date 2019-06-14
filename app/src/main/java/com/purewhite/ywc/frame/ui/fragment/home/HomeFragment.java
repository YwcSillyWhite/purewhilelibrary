package com.purewhite.ywc.frame.ui.fragment.home;

import android.animation.ValueAnimator;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.FragHomeBinding;
import com.purewhite.ywc.frame.ui.adapter.HomePagerAdapter;
import com.purewhite.ywc.frame.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

import java.util.Arrays;

public class HomeFragment extends MvpFragment<FragHomeBinding,PresenterImp> {


    private TabLayout.OnTabSelectedListener talayout=new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            changeSelect(tab,true);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            changeSelect(tab,false);
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    private void changeSelect(TabLayout.Tab tab,boolean select)
    {
        final View customView = tab.getCustomView();
        if (select&&customView!=null)
        {
            ValueAnimator valueAnimator=ValueAnimator.ofFloat(1,1.1f);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (Float) animation.getAnimatedValue();
                    customView.setScaleX(animatedValue);
                    customView.setScaleY(animatedValue);
                }
            });
            valueAnimator.setDuration(200);
            valueAnimator.start();
        }
        else
        {
            ValueAnimator valueAnimator=ValueAnimator.ofFloat(1.1f,1);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (float) animation.getAnimatedValue();
                    customView.setScaleX(animatedValue);
                    customView.setScaleY(animatedValue);
                }
            });
            valueAnimator.setDuration(200);
            valueAnimator.start();
        }
    }


    @Override
    protected View onBarTitleView() {
        return mDataBinding.tabLayout;
    }

    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_home;
    }


    @Override
    protected void initView() {
        String[] home_tag_title = getResources().getStringArray(R.array.home_tab_title);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), Arrays.asList(home_tag_title));
        mDataBinding.viewPager.setAdapter(homePagerAdapter);
        mDataBinding.viewPager.setOffscreenPageLimit(home_tag_title.length);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
        mDataBinding.tabLayout.addOnTabSelectedListener(talayout);
        for (int i = 0; i < home_tag_title.length; i++) {
            mDataBinding.tabLayout.getTabAt(i).setCustomView(addTabLayout(i,home_tag_title[i]));
        }
    }


    private View addTabLayout(int index,String content) {
        //自定义View布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tablayout_view, null);
        TextView title = (TextView) view.findViewById(R.id.text_view);
        title.setText(content);
        if (index==0)
        {
            view.setScaleY(1.1f);
            view.setScaleX(1.1f);
        }
        else
        {
            view.setScaleX(1f);
            view.setScaleY(1f);
        }

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mDataBinding.tabLayout.removeOnTabSelectedListener(talayout);
    }
}
