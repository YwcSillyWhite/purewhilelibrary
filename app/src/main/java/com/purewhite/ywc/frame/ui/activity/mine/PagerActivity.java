package com.purewhite.ywc.frame.ui.activity.mine;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityPagerBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PagerActivity extends MvpActivity<ActivityPagerBinding,PresenterImp> {

    private Integer [] colors={0XFF199AFE,0XFFFF8080,0XFF00AB96,0XFF00ff00,0XFF001133};
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_pager;
    }

    @Override
    protected void initView() {
        String[] stringArray = getResources().getStringArray(R.array.view_pager);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < stringArray.length; i++) {
            list.add(stringArray[i]);
        }
        mDataBinding.titleBarLayout.setAdapter(list,null);
//        mDataBinding.titleBarLayout.setList(Arrays.asList(colors));
    }


    @Override
    protected void onResume() {
        super.onResume();
        mDataBinding.titleBarLayout.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mDataBinding.titleBarLayout.onPause();
    }
}
