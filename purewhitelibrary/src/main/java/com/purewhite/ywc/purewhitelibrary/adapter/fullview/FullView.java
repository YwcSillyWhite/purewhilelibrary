package com.purewhite.ywc.purewhitelibrary.adapter.fullview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnFullListener;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;

/**
 * 没有网络，没有数据，第一次加载
 * @author yuwenchao
 */
public abstract class FullView {

    protected OnFullListener onFullListener;
    public void setOnFullListener(OnFullListener onFullListener) {
        this.onFullListener = onFullListener;
    }

    private int fullState;
    //加载
    public static final int LODA=1;
    //网络
    public static final int NETWORK=2;
    //数据
    public static final int DATA=3;
    //
    public static final int REST=0;

    public abstract  int getLayoutId();

    abstract int getLoadId();

    abstract int getNetworkId();

    abstract int getDataId();

    private RecyclerView.Adapter adapter;
    private View itemView;

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public void setItemView(View itemView) {
        this.itemView = itemView;
    }

    public void onBindView(BaseViewHolder holder)
    {
        switch (fullState)
        {
            case DATA:
                holder.setVisibility(getLoadId(),false);
                holder.setVisibility(getNetworkId(),false);
                holder.setVisibility(getDataId(),true);
                break;
            case NETWORK:
                holder.setVisibility(getLoadId(),false);
                holder.setVisibility(getNetworkId(),true);
                holder.setVisibility(getDataId(),false);
                break;
            case LODA:
                holder.setVisibility(getLoadId(),true);
                holder.setVisibility(getNetworkId(),false);
                holder.setVisibility(getDataId(),false);
                break;
            default:
                holder.setVisibility(getLoadId(),false);
                holder.setVisibility(getNetworkId(),false);
                holder.setVisibility(getDataId(),false);
                break;
        }
    }

    public boolean isShow()
    {
        if (fullState==DATA||fullState==NETWORK||fullState==LODA) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param fullState  状态
     * @param flush    是否刷新
     */
    public void setFullState(int fullState,boolean flush) {
        this.fullState = fullState;
        if (flush&&adapter!=null)
        {
            adapter.notifyDataSetChanged();
        }
    }
}
