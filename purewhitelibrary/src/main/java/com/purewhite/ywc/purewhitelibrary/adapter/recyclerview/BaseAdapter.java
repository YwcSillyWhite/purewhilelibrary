package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnFullListener;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnLoadListener;
import com.purewhite.ywc.purewhitelibrary.adapter.fullview.FullView;
import com.purewhite.ywc.purewhitelibrary.adapter.fullview.FullViewImp;
import com.purewhite.ywc.purewhitelibrary.adapter.loadview.LoadView;
import com.purewhite.ywc.purewhitelibrary.adapter.loadview.LoadViewImp;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhitelibrary.config.NetWorkUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 *
 * @author yuwenchao
 * @date 2018/11/15
 */

public abstract class BaseAdapter<T,V extends BaseViewHolder> extends RecyclerView.Adapter<V>{

    public BaseAdapter getAdapter()
    {
        return this;
    }
    //用于延迟
    private Handler handler=new Handler();
    //加载最多项
    private int pageSize=10;
    public final void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    private List<T> mData;
    //头部
    private LinearLayout mHeaderLayout;
    //尾部
    private LinearLayout mFooterLayout;
    //全局布局
    private FullView fullView=new FullViewImp();
    //设置全局布局
    public final void setFullView(FullView fullView) {
        if (loadView==null) {
            throw new UnsupportedOperationException("fullview can not null");
        }
        this.fullView = fullView;
    }

    public final void setFullState(int state)
    {
        setFullState(state,false);
    }

    //设置full状态，并且是否刷新
    public final void setFullState(int statue,boolean flush)
    {
        fullView.setFullState(statue);
        if (flush) {
            notifyDataSetChanged();
        }
    }
    private OnFullListener onFullListener;
    //设置全局布局的监听
    public final void setOnFullListener(OnFullListener onFullListener)
    {
        this.onFullListener=onFullListener;
    }

    //加载布局
    private LoadView loadView=new LoadViewImp();
    //设置加载布局
    public final void setLoadView(LoadView loadView) {
        if (loadView==null) {
            throw new UnsupportedOperationException("loadview can not null");
        }
        this.loadView = loadView;
    }
    //设置加载布局状态，加载布局是否刷新
    private final void setLoadState(int statue,boolean flush)
    {
        loadView.setLoadStatue(statue);
        if (flush) {
            notifyItemChanged(getItemCount()-1);
        }
    }
    //滑动监听
    protected OnLoadListener onLoadListener;
    //设置滑动监听
    public final void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    //父View是否可以点击
    private boolean parentClick=true;
    public final void setParentClick(boolean parentClick) {
        this.parentClick = parentClick;
    }
    //点击事件
    protected OnItemListener onItemListener;
    public final void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    private final int HEAD_ITEM=Integer.MIN_VALUE;
    private final int FOOT_ITEM=Integer.MIN_VALUE+1;
    private final int LOAD_ITEM=Integer.MIN_VALUE+2;
    private final int FULL_ITEM=Integer.MIN_VALUE+3;


    public BaseAdapter(List<T> list) {
        this.mData = list!=null?list:new ArrayList<T>();
    }
    /**
     * 获取数据
     * @return
     */
    protected final List<T> obtainData()
    {
        return mData;
    }

    /**
     * 根据index获取list对应的数据
     * @param position
     * @return
     */
    public final T obtainT(int position)
    {
        if (position<obtianDataCount())
        {
            return mData.get(position);
        }
        return null;
    }


    /************  item的长度  ****************/
    /**
     * data数据的长度
     * @return
     */
    public int obtianDataCount()
    {
        return mData!=null?mData.size():0;
    }

    /**
     * 让方法变成final 不让子类集成
     * @return
     */
    @Override
    public final int getItemCount() {
        return getFullCount()>0?getFullCount():getHeadCount()+obtianDataCount()+getFootCount()+getLoadCount();
    }

    /**
     * 没有数据的长度
     * @return
     */
    private int getFullCount()
    {
        if (obtianDataCount()>0) {
            return 0;
        }
        return fullView.isShow()?1:0;
    }

    /**
     * 头部数据的长度
     * @return
     */
    private int getHeadCount()
    {
        if (mHeaderLayout!=null&&mHeaderLayout.getChildCount()>0) {
            return 1;
        }
        return 0;
    }

    /**
     * 尾部长度
     * @return
     */
    private int getFootCount()
    {
        if (mFooterLayout!=null&&mFooterLayout.getChildCount()>0) {
            return 1;
        }
        return 0;
    }

    /**
     * 加载更多的长度
     * @return
     */
    private int getLoadCount()
    {
        if (onLoadListener==null) {
            return 0;
        }
        return 1;
    }
    /************  item的长度  ****************/















    /************  item的类型 ****************/
    /**
     * item类型，final化避免重写
     * @param position
     * @return
     */
    @Override
    public final int getItemViewType(int position) {
        if (getFullCount()>0) {
            return FULL_ITEM;
        }
        if (position==0&&getHeadCount()!=0)
        {
            return HEAD_ITEM;
        }
        else if (position<getHeadCount()+obtianDataCount())
        {
            return obtianDataType(position-getHeadCount());
        }
        else if(position<getHeadCount()+obtianDataCount()+getFootCount())
        {
            return FOOT_ITEM;
        }
        else
        {
            return LOAD_ITEM;
        }
    }

    /**
     * 获取当前数据类型
     * @param position
     * @return
     */
    protected int obtianDataType(int position)
    {
        return super.getItemViewType(position+getHeadCount());
    }

    //判断是不是data数据类型
    public boolean dataType(RecyclerView.ViewHolder viewhold)
    {
        return dataType(viewhold.getItemViewType());
    }

    public boolean dataType(int viewType)
    {
        return viewType!=HEAD_ITEM&&viewType!=FOOT_ITEM
                &&viewType!=LOAD_ITEM&&viewType!=FULL_ITEM;
    }
    /************  item的类型 ****************/













    /************  onCreateViewHolder ****************/
    @Override
    public final V onCreateViewHolder(ViewGroup parent, int viewType) {
        V viewhold;
        if (viewType==FULL_ITEM)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(fullView.getLayoutId(), parent, false);
            fullView.setItemView(view);
            viewhold=createV(view);
            if (fullView.getClickLoadId()!=0)
            {
                viewhold.findViewId(fullView.getClickLoadId())
                        .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (NetWorkUtils.isConnected()&&onFullListener!=null)
                        {
                            setFullState(FullView.LODA,true);
                            onFullListener.again();
                        }
                    }
                });
            }
        }
        else if (viewType==HEAD_ITEM)
        {
            viewhold=createV(mHeaderLayout);
        }
        else if (viewType==FOOT_ITEM)
        {
            viewhold=createV(mFooterLayout);
        }
        else if (viewType==LOAD_ITEM)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(loadView.getLayoutId(),
                    parent, false);
            viewhold=createV(view);
            view.setOnClickListener(new OnSingleListener() {
                @Override
                public void onSingleClick(View v) {
                    //加载失败，点击重新加载  没有网络不允许加载
                    if (loadView.getLoadStatue()==LoadView.NETWORK &&NetWorkUtils.isConnected())
                    {
                        setLoadState(LoadView.LOAD,true);
                        onLoadListener.loadAgain();
                    }
                }
            });
        }
        else
        {
            viewhold = onCreateData(parent,viewType);
            //设置监听
            bindDataListener(viewhold);
        }
        return viewhold;
    }

    //创建viewhold
    private final V createV(View view)
    {
        return ((V) new BaseViewHolder(view));
    }

    protected abstract V onCreateData(ViewGroup parent, int viewType);


    private final void bindDataListener(final V viewhold)
    {
        if (viewhold == null&&!parentClick&&onItemListener==null) {
            return;
        }
        final View view = viewhold.itemView;
        if (view == null) {
            return;
        }
        view.setOnClickListener(new OnSingleListener() {
            @Override
            public void onSingleClick(View v) {
                if (onItemListener!=null&&parentClick)
                {
                    int position=viewhold.getLayoutPosition() - getFootCount();
                    onItemListener.OnClick(BaseAdapter.this,view, position,true);
                }
            }
        });
    }
    /************  onCreateViewHolder ****************/










    /************  onBindViewHolder ****************/
    @Override
    public final void onBindViewHolder(V holder, int position) {
        loadMore(position);
        int itemViewType = holder.getItemViewType();
        if (itemViewType==LOAD_ITEM)
        {
            loadView.onBindView(holder);
        }
        else if (itemViewType==FULL_ITEM)
        {
            fullView.onBindView(holder);
        }
        else if (itemViewType!=HEAD_ITEM&&itemViewType!=FOOT_ITEM)
        {
            onData(holder,position,obtainT(position-getHeadCount()));
        }
    }

    //判断是不是加载更多
    private void loadMore(int position)
    {
        //loadview长度不能为0，position等于最后一个，position不能为loadview的position
        if (getLoadCount()==0||position<getItemCount()-1||getLoadCount()-1==position) {
            return;
        }
        //加载结束
        if (loadView.getLoadStatue()==LoadView.FINISH) {
            //网络判断
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

    //赋值数据
    protected abstract void onData(V holder,int position,T t);

    /************  onBindViewHolder ****************/












    /************  添加删除头尾   ****************/
    //添加头尾
    public final void addHeadView(View header)
    {
        addHeadView(header,-1);
    }

    public final void addHeadView(View header,int indext)
    {
        addHeadView(header,indext,LinearLayout.VERTICAL);
    }

    public final void addHeadView(View header, int index, int orientation) {
        if (header.getParent()==null&&mHeaderLayout==null)
        {
            mHeaderLayout=new LinearLayout(header.getContext());
            if (orientation==LinearLayout.VERTICAL)
            {
                mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
                mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
            }
            else
            {
                mHeaderLayout.setOrientation(LinearLayout.HORIZONTAL);
                mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT,MATCH_PARENT));
            }
        }
        final int childCount = mHeaderLayout.getChildCount();
        if (index<0||index>childCount)
        {
            index=childCount;
        }
        mHeaderLayout.addView(header,index);
        if (mHeaderLayout.getChildCount()==1)
        {
            notifyItemInserted(0);
        }
    }


    public final void removeHeadView(View head)
    {
        if (mHeaderLayout!=null)
        {
            mHeaderLayout.removeView(head);
            notifyItemInserted(0);
        }
    }

    public final void removeHeadView(int position)
    {
        if (mHeaderLayout!=null)
        {
            int childCount = mHeaderLayout.getChildCount();
            if (position<childCount)
            {
                mHeaderLayout.removeViewAt(position);
                notifyItemInserted(0);
            }
        }
    }


    //添加尾部
    public final void addFootView(View foot)
    {
        addFootView(foot,-1);
    }

    public final void addFootView(View foot,int index)
    {
        addFootView(foot,index,LinearLayout.VERTICAL);
    }

    public final void addFootView(View header, int index, int orientation) {
        if (mFooterLayout==null)
        {
            mFooterLayout=new LinearLayout(header.getContext());
            if (orientation==LinearLayout.VERTICAL)
            {
                mFooterLayout.setOrientation(LinearLayout.VERTICAL);
                mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
            }
            else
            {
                mFooterLayout.setOrientation(LinearLayout.HORIZONTAL);
                mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT,MATCH_PARENT));
            }
        }
        final int childCount = mFooterLayout.getChildCount();
        if (index<0||index>childCount)
        {
            index=childCount;
        }
        mFooterLayout.addView(header,index);
        if (mFooterLayout.getChildCount()==1)
        {
            notifyItemInserted(getHeadCount()+obtianDataCount());
        }
    }

    public final void removeFootView(View head)
    {
        if (mFooterLayout!=null)
        {
            mFooterLayout.removeView(head);
            notifyItemInserted(getHeadCount()+obtianDataCount());
        }
    }

    public final void removeFootView(int position)
    {
        if (mFooterLayout!=null)
        {
            int childCount = mFooterLayout.getChildCount();
            if (position<childCount)
            {
                mFooterLayout.removeViewAt(position);
                notifyItemInserted(getHeadCount()+obtianDataCount());
            }
        }
    }

    /************  添加删除头尾   ****************/










    /************  解决Layoutmanager影响full，head，foot的宽度bug  ****************/
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager)
        {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return dataType(getItemViewType(position))
                            ? obtainDataSpanSize(position-getHeadCount(),gridManager)
                            :gridManager.getSpanCount();
                }
            });
        }
    }

    protected int obtainDataSpanSize(int position,GridLayoutManager gridManager)
    {
        return 1;
    }

    @Override
    public void onViewAttachedToWindow(V holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if(layoutParams != null ) {
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)
            {
                StaggeredGridLayoutManager.LayoutParams params =
                        (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                params.setFullSpan(!dataType(holder));
            }
        }
    }











    /************  数据处理   ****************/
    //添加数据
    public  void  refreshComplete(boolean network,int page,List<T> list)
    {
        refreshComplete(network,page==1,list);

    }

    public void  refreshComplete(boolean network,boolean flush,List<T> list)
    {
        if (list!=null&&list.size()>0)
        {
            addDataFlush(flush,list);
        }
        else
        {
            if (flush)
            {
                setLoadState(LoadView.REST,false);
                if (obtianDataCount()==0)
                {
                    setFullState(network?FullView.DATA:FullView.NETWORK,true);
                }
            }
            else
            {
                setLoadState(network?LoadView.DATA:LoadView.NETWORK,true);
            }
        }

    }


    public void addDataFlush(int page,List<T> list)
    {
        addDataFlush(page==1,list);
    }


    public void addDataFlush(boolean flush,List<T> list)
    {
        if (flush)
        {
            flush(list);
        }
        else
        {
            addData(list);
        }
    }


    //刷新数据
    public void flush(List<T> list)
    {
        if (obtianDataCount()>0)
        {
            obtainData().clear();
        }
        if (list!=null&&list.size()>0)
        {
            obtainData().addAll(list);
            setLoadState(list.size()>=pageSize?LoadView.FINISH:LoadView.REST,false);
        }
        else
        {
            setLoadState(LoadView.REST,false);
        }
        notifyDataSetChanged();
    }

    //添加数据
    public  void addData(List<T> list)
    {
        if (obtianDataCount()==0)
        {
            flush(list);
        }
        else
        {
            if (list!=null&&list.size()>0)
            {
                if (list.size()>=pageSize)
                {
                    setLoadState(LoadView.FINISH,false);
                }
                else
                {
                    setLoadState(LoadView.DATA,true);
                }
                obtainData().addAll(list);
                notifyItemRangeInserted(obtianDataCount()-list.size() + getHeadCount(), list.size());
            }
            else
            {
                setLoadState(LoadView.DATA,true);
            }
        }
    }


    //删除数据
    public void remove(int position)
    {
        if (obtianDataCount()>position)
        {
            obtainData().remove(position);
            notifyItemRemoved(position);
        }
    }


}
