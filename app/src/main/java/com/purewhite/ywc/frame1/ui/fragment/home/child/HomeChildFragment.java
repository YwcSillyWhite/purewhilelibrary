package com.purewhite.ywc.frame1.ui.fragment.home.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.config.TagUtils;
import com.purewhite.ywc.frame1.databinding.FragHomeChildBinding;
import com.purewhite.ywc.frame1.ui.adapter.HomeChildAdapter;
import com.purewhite.ywc.frame1.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnFullListener;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnLoadListenerImp;
import com.purewhite.ywc.purewhitelibrary.adapter.fullview.FullView;
import com.purewhite.ywc.purewhitelibrary.adapter.ptr.callback.OnPtrListener;

public class HomeChildFragment extends MvpFragment<FragHomeChildBinding,HomeChildPresenter>
        implements HomeChildContract.UiView {

    private int position;
    private HomeChildAdapter homeChildAdapter;

    private OnLoadListenerImp onLoadListenerImp=new OnLoadListenerImp() {
        @Override
        public void pullUp() {
            mPresenter.obtianShopList(position,false);
        }

        @Override
        public void loadAgain() {
            mPresenter.obtianShopList(position,false);
        }
    };

    private OnFullListener onFullListener=new OnFullListener() {
        @Override
        public void again() {
            mPresenter.obtianShopList(position,true);
        }
    };

    private OnPtrListener onPtrListener=new OnPtrListener() {
        @Override
        public void pullDown() {
            mPresenter.obtianShopList(position,true);
        }
    };

    @Override
    protected HomeChildPresenter creartPresenter() {
        return new HomeChildPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_home_child;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        position = arguments.getInt(TagUtils.home_child_tag, 0);
        homeChildAdapter = new HomeChildAdapter();
        homeChildAdapter.setFullState(FullView.LODA);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.recyclerView.setAdapter(homeChildAdapter);
        homeChildAdapter.setOnLoadListener(onLoadListenerImp);
        homeChildAdapter.setOnFullListener(onFullListener);

        mDataBinding.ptrLayout.setOnPtrListener(onPtrListener);
    }


    @Override
    protected void soleLoad() {
        super.soleLoad();
        mPresenter.obtianShopList(position,true);
    }

    @Override
    public HomeChildAdapter getAdapter() {
        return homeChildAdapter;
    }

    @Override
    public void respon(boolean flush) {
        mDataBinding.ptrLayout.refreshComplete(flush);
    }
}
