package com.purewhite.ywc.purewhitelibrary.adapter.loadview;


import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;

/**
 *
 * @author yuwenchao
 * @date 2018/11/19
 */

public abstract class LoadView {
    private int loadStatue;

    //加载  （加载中）
    public static final int LOAD=1;
    //没有网络
    public static final int NO_NETWORK=2;
    //数据  （没有更多）
    public static final int NO_DATA=3;
    //完成  （加载完成）
    public static final int FINISH=4;
    //其他
    public static final int REST=0;


    public abstract  int getLayoutId();

    abstract int getLoadId();

    abstract int getNetworkId();

    abstract int getDataId();


    public void onBindView(BaseViewHolder holder)
    {
        switch (loadStatue)
        {
            case LOAD:
                holder.setVisibility(getLoadId(),true);
                holder.setVisibility(getNetworkId(),false);
                holder.setVisibility(getDataId(),false);
                break;
            case NO_NETWORK:
                holder.setVisibility(getLoadId(),false);
                holder.setVisibility(getNetworkId(),true);
                holder.setVisibility(getDataId(),false);
                break;
            case NO_DATA:
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


    public int getLoadStatue() {
        return loadStatue;
    }

    public void setLoadStatue(int loadStatue) {
        this.loadStatue = loadStatue;
    }


}
