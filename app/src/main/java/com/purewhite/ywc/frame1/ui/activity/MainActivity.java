package com.purewhite.ywc.frame1.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.MenuItem;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.ActivityMainBinding;
import com.purewhite.ywc.frame1.ui.fragment.home.HomeFragment;
import com.purewhite.ywc.frame1.ui.fragment.mine.MineFragment;
import com.purewhite.ywc.frame1.ui.fragment.rest.RestFragment;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.adapter.pagerview.BaseFragmentAdapterImp;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class MainActivity extends MvpActivity<ActivityMainBinding,PresenterImp> {

    private SparseArray<Fragment> sparseArray;
    private int lastPosition=-1;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    mDataBinding.viewPager.setCurrentItem(0);
                    return true;
                case R.id.home_found:
                    mDataBinding.viewPager.setCurrentItem(1);
                    return true;
                case R.id.home_me:
                    mDataBinding.viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        sparseArray=new SparseArray<>();
        sparseArray.append(0,new HomeFragment());
        sparseArray.append(1,new RestFragment());
        sparseArray.append(2,new MineFragment());
        BaseFragmentAdapterImp baseFragmentAdapterImp =
                new BaseFragmentAdapterImp(getSupportFragmentManager(), sparseArray);
        mDataBinding.viewPager.setAdapter(baseFragmentAdapterImp);
        mDataBinding.viewPager.setOffscreenPageLimit(sparseArray.size()-1);
        mDataBinding.bottomView.setItemIconTintList(null);
        mDataBinding.bottomView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }

}
