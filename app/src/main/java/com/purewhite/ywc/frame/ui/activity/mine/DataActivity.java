package com.purewhite.ywc.frame.ui.activity.mine;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityDataBinding;
import com.purewhite.ywc.frame.ui.adapter.DataAdapter;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

import java.util.Arrays;
import java.util.List;

public class DataActivity extends MvpActivity<ActivityDataBinding,PresenterImp> {

    private String title[]={"标题1","标题2","标题3","标题4","纯白框架"};
    private String center[]={"内容：1\n地址：我加","内容：1\n地址：我加\n小区：可爱","内容：1\n地址：我加\n小区：可爱","内容：1\n地址：我加\n小区：可爱","人称：ywc"};
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_data;
    }

    @Override
    protected void initView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.setAdapter(new DataAdapter(Arrays.asList(title),Arrays.asList(center)));
    }
}
