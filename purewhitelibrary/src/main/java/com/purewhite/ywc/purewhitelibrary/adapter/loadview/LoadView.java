package com.purewhite.ywc.purewhitelibrary.adapter.loadview;


import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;

/**
 *
 * @author yuwenchao
 * @date 2018/11/19
 */

public abstract class LoadView {
    private int state;
    //加载  （加载中）
    public static final int LOAD=1;
    //网络  （网络不好，加载失败）
    public static final int NETWORK=2;
    //完成  （加载完成）
    public static final int FINISH=3;
    //数据  （没有更多）
    public static final int DATA=4;
    //其他
    public static final int REST=0;


    public abstract  int getLayoutId();

    abstract int getLoadId();

    abstract int getNetworkId();

    abstract int getDataId();


    public void onBindView(BaseViewHolder holder)
    {
        switch (state)
        {
            case LOAD:
                holder.setVisibility(getLoadId(),true);
                holder.setVisibility(getNetworkId(),false);
                holder.setVisibility(getDataId(),false);
                break;
            case NETWORK:
                holder.setVisibility(getLoadId(),false);
                holder.setVisibility(getNetworkId(),true);
                holder.setVisibility(getDataId(),false);
                break;
            case DATA:
                holder.setVisibility(getLoadId(),false);
                holder.setVisibility(getNetworkId(),false);
                holder.setVisibility(getDataId(),true);
                break;
                default:
                    holder.setVisibility(getLoadId(),false);
                    holder.setVisibility(getNetworkId(),false);
                    holder.setVisibility(getDataId(),false);
                    break;
        }
    }


    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }


}
