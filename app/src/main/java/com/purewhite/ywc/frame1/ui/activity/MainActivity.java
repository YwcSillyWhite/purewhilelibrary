package com.purewhite.ywc.frame1.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.ActivityMainBinding;
import com.purewhite.ywc.frame1.ui.fragment.home.HomeFragment;
import com.purewhite.ywc.frame1.ui.fragment.mine.MineFragment;
import com.purewhite.ywc.frame1.ui.fragment.rest.RestFragment;
import com.purewhite.ywc.frame1.ui.mvp.MvpActivity;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

public class MainActivity extends MvpActivity<ActivityMainBinding,PresenterImp> {

    private SparseArray<MvpFragment> sparseArray;
    private int lastPosition=-1;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    replaceFrag(0);
                    return true;
                case R.id.home_found:
                    replaceFrag(1);
                    return true;
                case R.id.home_me:
                    replaceFrag(2);
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
        mDataBinding.bottomView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        mDataBinding.bottomView.setItemIconTintList(null);
        replaceFrag(0);
    }


    /**
     * 切换fragment
     * @param position
     */
    private void replaceFrag(int position)
    {
        if (position!=lastPosition&&position>=0&&position<sparseArray.size())
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            MvpFragment mvpFragment = sparseArray.get(position);
            if (mvpFragment.isAdded())
            {
                fragmentTransaction.show(mvpFragment);
                fragmentTransaction.hide(sparseArray.get(lastPosition));
            }
            else
            {
                fragmentTransaction.add(R.id.frag_layout,mvpFragment);
                if (lastPosition>=0)
                {
                    fragmentTransaction.hide(sparseArray.get(lastPosition));
                }
            }
            fragmentTransaction.commit();
            lastPosition=position;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
