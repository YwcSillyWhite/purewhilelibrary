package com.purewhite.ywc.frame.ui.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acker.simplezxing.activity.CaptureActivity;
import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.FragMineBinding;
import com.purewhite.ywc.frame.ui.activity.mine.AndroidStudyActivity;
import com.purewhite.ywc.frame.ui.activity.mine.CameraActivity;
import com.purewhite.ywc.frame.ui.activity.mine.CustomMainActivity;
import com.purewhite.ywc.frame.ui.activity.mine.PagerActivity;
import com.purewhite.ywc.frame.ui.activity.mine.SocketActivity;
import com.purewhite.ywc.frame.ui.activity.mine.WheelViewActivity;
import com.purewhite.ywc.frame.ui.activity.mine.dialog.DialogHomeActiivty;
import com.purewhite.ywc.frame.ui.adapter.MineAdapter;
import com.purewhite.ywc.frame.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;
import com.purewhite.ywc.purewhitelibrary.network.imageload.ImageLoader;

import java.util.Arrays;

public class MineFragment extends MvpFragment<FragMineBinding, PresenterImp>
        implements OnItemListener,View.OnClickListener {




    @Override
    public void onClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView) {
        switch (position)
        {
            case 0:
                skipActivity(AndroidStudyActivity.class);
                break;
            case 1:
                skipActivity(CustomMainActivity.class);
                break;
            case 2:
                skipActivity(DialogHomeActiivty.class);
                break;
            case 3:
                skipActivity(CameraActivity.class);
                break;
            case 4:
                startCaptureActivityForResult();
                break;
            case 5:
                skipActivity(SocketActivity.class);
                break;
            case 6:
                skipActivity(WheelViewActivity.class);
                break;
            case 7:
                skipActivity(PagerActivity.class);
                break;
        }
    }


    @Override
    public void onClick(View v) {
//        if (!ClickUtils.clickable(v))
//            return;
        switch (v.getId())
        {
            case R.id.head_img:
                ToastUtils.show("吐血");
//                Bundle build = BundleUtils.buidler()
//                        .put(PictureStype.SKIP_STYPE, PictureStype.SKIP_STYPE_PIC_ONLY)
//                        .build();
//                skipActivity(PictureActivity.class,build);
                break;
        }
    }



    private void startCaptureActivityForResult() {
        Intent intent = new Intent(getContext(), CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        bundle.putBoolean(CaptureActivity.KEY_SCAN_AREA_FULL_SCREEN, CaptureActivity.VALUE_SCAN_AREA_FULL_SCREEN);
        bundle.putBoolean(CaptureActivity.KEY_NEED_SCAN_HINT_TEXT, CaptureActivity.VALUE_SCAN_HINT_TEXT);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
    }



    @Override
    protected int getLayout() {
        return R.layout.frag_mine;
    }

    @Override
    protected void initView() {
        mDataBinding.titleBarLayout.centerText.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.centerText.setText("个人中心");
        mDataBinding.headImg.setOnClickListener(this);
        ImageLoader.newInstance().initCircle(mDataBinding.headImg,R.mipmap.icon_logo);
        MineAdapter mineAdapter = new MineAdapter(Arrays.asList(getResources().getStringArray(R.array.home_recycler_title)));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDataBinding.recyclerView.setAdapter(mineAdapter);
        mineAdapter.setOnItemListener(this);
    }


    @Override
    protected PresenterImp creartPresenter() {
        return null;
    }


}
