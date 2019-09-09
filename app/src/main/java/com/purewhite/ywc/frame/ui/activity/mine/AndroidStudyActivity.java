package com.purewhite.ywc.frame.ui.activity.mine;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.config.TagUtils;
import com.purewhite.ywc.frame.databinding.ActivityAndroidStudyBinding;
import com.purewhite.ywc.frame.ui.adapter.AndroidStudyAdapter;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.config.bundle.BundleUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.BasePresenter;

import java.util.Arrays;

public class AndroidStudyActivity extends MvpActivity<ActivityAndroidStudyBinding, BasePresenter> {

    private AndroidStudyAdapter androidStudyAdapter;
    private OnItemListener onItemListener=new OnItemListener() {
        @Override
        public void onClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView) {
            if (adapter instanceof AndroidStudyAdapter)
            {
                String uri = ((AndroidStudyAdapter) adapter).obtainUri(position);
                skipActivity(WebActivity.class, BundleUtils.buidler().put(TagUtils.web_uri,uri).build());
            }
        }
    };

    private OnSingleListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View v) {
            switch (v.getId())
            {
                case R.id.left_img:
                    finish();
                    break;
            }
        }
    };



    @Override
    protected int getLayout() {
        return R.layout.activity_android_study;
    }

    @Override
    protected void initView() {
        mDataBinding.titleBarLayout.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.leftImg.setOnClickListener(onSingleListener);
        mDataBinding.titleBarLayout.centerText.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.centerText.setText("纯白学习");
        androidStudyAdapter = new AndroidStudyAdapter(Arrays.asList(getResources().getStringArray(R.array.android_study_text))
                ,Arrays.asList(getResources().getStringArray(R.array.android_study_uri)));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.setAdapter(androidStudyAdapter);
        androidStudyAdapter.setOnItemListener(onItemListener);
    }
}
