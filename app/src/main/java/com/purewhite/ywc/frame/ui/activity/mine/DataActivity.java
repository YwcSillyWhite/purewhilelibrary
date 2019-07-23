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
        String[] title = getResources().getStringArray(R.array.data_title);
        String[] center = getResources().getStringArray(R.array.data_center);
        center[0]=String.format(center[0],"目标1","目标2");

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.setAdapter(new DataAdapter(Arrays.asList(title),Arrays.asList(center)));
    }
}
