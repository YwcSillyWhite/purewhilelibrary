package com.purewhite.ywc.frame.ui.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        implements OnItemListener {




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
                skipActivity(WheelViewActivity.class);
                break;
            case 5:
                skipActivity(PagerActivity.class);
                break;
        }
    }

    @Override
    protected void onClickUtils(View view) {
        //        if (!ClickUtils.clickable(v))
//            return;
        switch (view.getId())
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




}
