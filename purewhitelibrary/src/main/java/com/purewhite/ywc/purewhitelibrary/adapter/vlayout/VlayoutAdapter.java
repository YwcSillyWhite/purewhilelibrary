package com.purewhite.ywc.purewhitelibrary.adapter.vlayout;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnLoadListener;
import com.purewhite.ywc.purewhitelibrary.adapter.fullview.FullView;
import com.purewhite.ywc.purewhitelibrary.adapter.fullview.FullViewImp;
import com.purewhite.ywc.purewhitelibrary.adapter.loadview.LoadView;
import com.purewhite.ywc.purewhitelibrary.adapter.loadview.LoadViewImp;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhitelibrary.config.NetWorkUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;

/**
 * @author yuwenchao
 */
public class VlayoutAdapter extends DelegateAdapter
{
    //加载更多load itemtype
    private final int LOAD_VIEW=Integer.MIN_VALUE;
    private final int FULL_VIEW=Integer.MIN_VALUE+1;
    //加载更多监听
    private OnLoadListener onLoadListener;
    public final void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    //数据长度
    private int mPagesize=10;
    public final void setmPagesize(int mPagesize) {
        this.mPagesize = mPagesize;
    }

    //全屏布局
    private FullView fullView=new FullViewImp();
    //设置full状态，并且是不刷新
    public final void setFullSate(int statue)
    {
        fullView.setFullState(statue,false);
    }
    //设置full状态，并且是否刷新
    public final void setFullSate(int statue,boolean flush)
    {
        fullView.setFullState(statue,flush);
    }
    public final void setFullView(FullView fullView) {
        if (fullView==null) {
            throw new UnsupportedOperationException("fullview can not null");
        }
        this.fullView = fullView;
    }


    private final void setLoadState(int statue,boolean flush)
    {
        loadView.setState(statue);
        if (flush) {
            notifyItemChanged(getItemCount()-1);
        }
    }

    //加载布局
    private LoadView loadView=new LoadViewImp();
    public  final void setLoadView(LoadView loadView) {
        if (loadView==null) {
            throw new UnsupportedOperationException("loadview can not null");
        }
        this.loadView = loadView;
    }
    private Handler handler=new Handler();

    public VlayoutAdapter(VirtualLayoutManager layoutManager) {
        super(layoutManager);
    }

    public VlayoutAdapter(VirtualLayoutManager layoutManager, boolean hasConsistItemType) {
        super(layoutManager, hasConsistItemType);
    }

    @Override
    public final int getItemCount() {
        if (obtianDataCount()==0) {
            return getFullCount();
        }
        return obtianDataCount()+getLoadCount();
    }

    //全局布局长度
    private final int getFullCount()
    {
        if (obtianDataCount()>0) {
            return 0;
        }
        return fullView.isShow()?1:0;
    }

    public int obtianDataCount()
    {
        return super.getItemCount();
    }

    //加载更多布局的item
    private final int getLoadCount()
    {
        if (onLoadListener==null) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (getFullCount()==1) {
            return FULL_VIEW;
        }
        if (position<getItemCount()-getLoadCount())
        {
            return obtianDataViewType(position);
        }
        else
        {
            return LOAD_VIEW;
        }
    }

    protected int obtianDataViewType(int position)
    {
        return super.getItemViewType(position);
    }


    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        if (viewType == LOAD_VIEW)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(loadView.getLayoutId(), parent, false);
            viewHolder = new BaseViewHolder(view);
            viewHolder.itemView.setOnClickListener(new OnSingleListener() {
                @Override
                public void onSingleClick(View v) {
                    //加载失败，点击重新加载  没有网络不允许加载
                    if (loadView.getState()==LoadView.NETWORK&&NetWorkUtils.isConnected())
                    {
                        setLoadState(LoadView.LOAD,true);
                        onLoadListener.loadAgain();
                    }
                }
            });
        }
        else if (viewType==FULL_VIEW)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(fullView.getLayoutId(),
                    parent, false);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height=parent.getHeight();
            viewHolder = new BaseViewHolder(view);
        }
        else
        {
            viewHolder = super.onCreateViewHolder(parent, viewType);
        }
        return viewHolder;
    }



    @Override
    public  final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        judgeLoadMore(position);
        int itemViewType = holder.getItemViewType();
        if (itemViewType == LOAD_VIEW) {
            loadView.onBindView((BaseViewHolder) holder);
        }
        else if (itemViewType==FULL_VIEW)
        {
            fullView.onBindView(((BaseViewHolder) holder));
        }
    }


    //判断是否能加载更多
    private final void judgeLoadMore(int position) {
        if (getLoadCount()==0||position<getItemCount()-1||position==getLoadCount()-1) {
            return;
        }
        if (loadView.getState()==LoadView.FINISH)
        {
            //如果网络不可用，就直接显示加载失败
            if (NetWorkUtils.isConnected())
            {
                setLoadState(LoadView.LOAD,false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoadListener.loadMore();
                    }
                }, 200);
            }
            else
            {
                setLoadState(LoadView.NETWORK,false);
            }

        }
    }

    /**
     * @param network  有网络
     * @param page   当前第几页
     * @param pagesize  数据长度
     */
    public void refreshComplete(boolean network,int page,int pagesize)
    {
        //如果item的长度等于fullview的长度，并且返回的数据长度等于的0的时候
        refreshComplete(network,page==1,pagesize);
    }

    public void refreshComplete(boolean network,boolean flush,int pagesize)
    {
        //如果item的长度等于fullview的长度，并且返回的数据长度等于的0的时候
        if (flush)
        {
            if (pagesize<mPagesize)
            {
                setLoadState(LoadView.REST,false);
                if (obtianDataCount()==0)
                {
                    setFullSate(network?FullView.DATA:FullView.NETWORK,true);
                }
            }
            else
            {
                setLoadState(LoadView.FINISH,true);
            }
        }
        else
        {
            if (pagesize<mPagesize)
            {
                setLoadState(network||pagesize>0?LoadView.DATA:LoadView.NETWORK,true);
            }
            else
            {
                setLoadState(LoadView.FINISH,true);
            }
        }
    }

}
