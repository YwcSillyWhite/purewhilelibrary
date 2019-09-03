package com.purewhite.ywc.frame.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityMainBinding;
import com.purewhite.ywc.frame.ui.fragment.home.HomeFragment;
import com.purewhite.ywc.frame.ui.fragment.mine.MineFragment;
import com.purewhite.ywc.frame.ui.fragment.rest.RestFragment;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.adapter.pagerview.BaseFragmentAdapterImp;
import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.ReNetUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MvpActivity<ActivityMainBinding,PresenterImp> {

    private SparseArray<Fragment> sparseArray;
    private boolean handlerDestroy=false;
    private final int handlerCode=1000;
    private Handler handler=new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what)
            {
                case handlerCode:
                    handlerDestroy=false;
                    break;
            }
        }
    };

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
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void beforeView() {
        super.beforeView();
        setFinishAnimStatue(-1);
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



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK)
        {
            if (!handlerDestroy)
            {
                ToastUtils.show("再按一下一次退出");
                handlerDestroy=true;
                handler.sendEmptyMessageDelayed(handlerCode,2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
