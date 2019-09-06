package com.purewhite.ywc.purewhitelibrary.adapter.fullview;


import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;

/**
 * 没有网络，没有数据，第一次加载
 * @author yuwenchao
 */
public abstract class FullView {

    private int fullState;
    //加载
    public static final int LODA=1;
    //没有网络
    public static final int NO_NETWORK=2;
    //没有数据
    public static final int NO_DATA=3;
    //
    public static final int REST=0;

    @LayoutRes
    public abstract  int getLayoutId();

    @IdRes
    abstract int getLoadId();

    @IdRes
    abstract int getNetworkId();

    @IdRes
    abstract int getDataId();

    @IdRes
    public abstract int getClickLoadId();




    public void onBindView(BaseViewHolder holder)
    {
        switch (fullState)
        {
            case NO_DATA:
                holder.setVisibility(getLoadId(),false);
                holder.setVisibility(getNetworkId(),false);
                holder.setVisibility(getDataId(),true);
                break;
            case NO_NETWORK:
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


    public abstract void setItemView(View itemView);

    public final  boolean isFullView()
    {
        if (fullState==NO_DATA||fullState==NO_NETWORK||fullState==LODA) {
            return true;
        }
        return false;
    }



    public int getFullState() {
        return fullState;
    }

    public void setFullState(int fullState) {
        this.fullState = fullState;
    }
}
