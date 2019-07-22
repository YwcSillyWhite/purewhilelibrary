package com.purewhite.ywc.frame.ui.activity.mine;

import android.view.View;

import com.contrarywind.listener.OnItemSelectedListener;
import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityWheelBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.frame.wheel.adapter.BaseWheelAdapter;
import com.purewhite.ywc.frame.wheel.adapter.BaseWheelAdapterImp;
import com.purewhite.ywc.frame.wheel.callback.WheelCallBack;
import com.purewhite.ywc.purewhitelibrary.config.LogUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

import java.util.Arrays;
import java.util.List;

public class WheelViewActivity extends MvpActivity<ActivityWheelBinding,PresenterImp> {
    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wheel;
    }

    @Override
    protected void initView() {
        mDataBinding.morePickerView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mDataBinding.morePickerView.setWheelAdapterPosition(3,null);
                mDataBinding.morePickerView.setWheelAdapterPosition(4,null);

            }
        }, new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        }, new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        }, new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        }, new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });


        mDataBinding.morePickerView.setWheelAdapterPosition(0
                ,new BaseWheelAdapterImp(Arrays.asList(getResources().getStringArray(R.array.home_tab_title))));
        mDataBinding.morePickerView.setWheelAdapterPosition(1
                ,new BaseWheelAdapterImp(Arrays.asList(getResources().getStringArray(R.array.home_tab_title))));
        mDataBinding.morePickerView.setWheelAdapterPosition(2
                ,new BaseWheelAdapterImp(Arrays.asList(getResources().getStringArray(R.array.home_tab_title))));
        mDataBinding.morePickerView.setWheelAdapterPosition(3
                ,new BaseWheelAdapterImp(Arrays.asList(getResources().getStringArray(R.array.home_tab_title))));
        mDataBinding.morePickerView.setWheelAdapterPosition(4
                ,new BaseWheelAdapterImp(Arrays.asList(getResources().getStringArray(R.array.home_tab_title))));

        mDataBinding.morePickerView.setWheelCallBack(new WheelCallBack() {
            @Override
            public void callBack(List<Integer> list) {
                LogUtils.debug(list.toString());
            }
        });


        mDataBinding.sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataBinding.morePickerView.obtianData();
            }
        });
    }
}
