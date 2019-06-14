package com.purewhite.ywc.frame.ui.fragment.home.child;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.bean.ShopBean;
import com.purewhite.ywc.frame.config.TagUtils;
import com.purewhite.ywc.frame.databinding.FragHomeChildBinding;
import com.purewhite.ywc.frame.ui.adapter.HomeChildAdapter;
import com.purewhite.ywc.frame.ui.mvp.MvpFragment;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnFullListener;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnLoadListenerImp;
import com.purewhite.ywc.purewhitelibrary.adapter.fullview.FullView;
import com.purewhite.ywc.purewhitelibrary.adapter.ptr.callback.OnPtrListener;
import com.purewhite.ywc.purewhitelibrary.config.ToastUtils;

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

    private OnItemListener onItemListener=new OnItemListener() {
        @Override
        public void OnClick(RecyclerView.Adapter adapter, View view, int position, boolean itemView) {
            if (adapter instanceof HomeChildAdapter)
            {
                ShopBean shopBean = ((HomeChildAdapter) adapter).obtainT(position);
                ToastUtils.show(shopBean.getItemtitle());
            }
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
        homeChildAdapter.setPageSize(10);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.recyclerView.setAdapter(homeChildAdapter);
//        mDataBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        homeChildAdapter.setOnLoadListener(onLoadListenerImp);
        homeChildAdapter.setOnFullListener(onFullListener);
        homeChildAdapter.setOnItemListener(onItemListener);
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
