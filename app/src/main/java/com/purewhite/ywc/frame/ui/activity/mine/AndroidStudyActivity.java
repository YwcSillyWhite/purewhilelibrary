package com.purewhite.ywc.frame.ui.activity.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.config.TagUtils;
import com.purewhite.ywc.frame.databinding.ActivityAndroidStudyBinding;
import com.purewhite.ywc.frame.ui.adapter.AndroidStudyAdapter;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.config.bundle.BundleUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

import java.util.Arrays;

public class AndroidStudyActivity extends MvpActivity<ActivityAndroidStudyBinding,PresenterImp> {

    private AndroidStudyAdapter androidStudyAdapter;
    private OnItemListener onItemListener=new OnItemListener() {
        @Override
        public void OnClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView) {
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
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected View onBarTitleView() {
        return mDataBinding.actionBar.barLayout;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_android_study;
    }

    @Override
    protected void initView() {
        mDataBinding.actionBar.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.leftImg.setOnClickListener(onSingleListener);
        mDataBinding.actionBar.centerText.setVisibility(View.VISIBLE);
        mDataBinding.actionBar.centerText.setText("纯白学习");
        androidStudyAdapter = new AndroidStudyAdapter(Arrays.asList(getResources().getStringArray(R.array.android_study_text))
                ,Arrays.asList(getResources().getStringArray(R.array.android_study_uri)));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.setAdapter(androidStudyAdapter);
        androidStudyAdapter.setOnItemListener(onItemListener);
    }
}
