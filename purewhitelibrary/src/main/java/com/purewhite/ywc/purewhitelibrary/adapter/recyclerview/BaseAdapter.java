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
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemDataListener;
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
 * Created by yuwenchao on 2018/11/15.
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
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    private List<T> mData;
    //头部
    private LinearLayout mHeaderLayout;
    //尾部
    private LinearLayout mFooterLayout;
    //全局布局
    private FullView fullView=new FullViewImp();
    //加载布局
    private LoadView loadView=new LoadViewImp();

    private final int HEAD_ITEM=Integer.MIN_VALUE;
    private final int FOOT_ITEM=Integer.MIN_VALUE+1;
    private final int LOAD_ITEM=Integer.MIN_VALUE+2;
    private final int FULL_ITEM=Integer.MIN_VALUE+3;

    public BaseAdapter(List<T> list) {
        this.mData = list!=null?list:new ArrayList<T>();
        fullView.setAdapter(this);
    }

    //获取数据对象
    public T obtainT(int position)
    {
        if (position<mData.size())
        {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        V viewhold;
        if (viewType==FULL_ITEM)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(fullView.getLayoutId(), parent, false);
            fullView.setItemView(view);
            viewhold=createV(view);
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
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(loadView.getLayoutId(), parent, false);
            viewhold=createV(view);
            view.setOnClickListener(new OnSingleListener() {
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
        else
        {
            viewhold = onCreateData(parent,viewType);
            //设置监听
            bindDataListener(viewhold);
        }
        return viewhold;
    }


    //创建viewhold
    private V createV(View view)
    {
        return ((V) new BaseViewHolder(view));
    }

    protected abstract V onCreateData(ViewGroup parent, int viewType);


    @Override
    public void onBindViewHolder(V holder, int position) {
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

    //赋值数据
    protected abstract void onData(V holder,int position,T t);









    /************  数据处理   ****************/
    //添加数据
    public void  refreshComplete(boolean network,int page,List<T> list)
    {
        if (list!=null&&list.size()>0)
        {
            addDataFlush(page,list);
        }
        else
        {
            if (page==1)
            {
                setLoadState(LoadView.REST,false);
                if (getDataCount()==0)
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
        if (page==1)
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
        if (mData.size()>0)
        {
            mData.clear();
        }
        if (list!=null&&list.size()>0)
        {
            mData.addAll(list);
            setLoadState(list.size()>=pageSize?LoadView.FINISH:LoadView.REST,false);
        }
        else
        {
            setLoadState(LoadView.REST,false);
        }
        notifyDataSetChanged();
    }

    //添加数据
    public void addData(List<T> list)
    {
        if (mData==null&&mData.size()==0)
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
                mData.addAll(list);
                notifyItemRangeInserted(mData.size()-list.size() + getHeadCount(), list.size());
            }
            else
            {
                setLoadState(LoadView.DATA,true);
            }
        }
    }

    //删除数据
    public void removeFlush(int position)
    {
        if (mData!=null&&mData.size()>position)
        {
            mData.remove(position);
            notifyDataSetChanged();
        }
    }

    //删除数据
    public void remove(int position)
    {
        if (mData!=null&&mData.size()>position)
        {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }






    /************  滑动监听   ****************/
    //滑动监听
    protected OnLoadListener onLoadListener;
    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }
    //判断是不是加载更多
    protected  void loadMore(int position)
    {
        //loadview长度不能为0，position等于最后一个，position不能为loadview的position
        if (getLoadCount()==0||position<getItemCount()-1||getLoadCount()-1==position) {
            return;
        }
        //加载结束
        if (loadView.getState()==LoadView.FINISH) {
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



    /************  点击事件和设置   ****************/
    protected OnItemDataListener onItemDataListener;
    //数据点击过事件
    protected OnItemListener onItemListener;
    public void setOnItemDataListener(OnItemDataListener onItemDataListener) {
        this.onItemDataListener = onItemDataListener;
    }

    private boolean parentClick=true;
    public void setParentClick(boolean parentClick) {
        this.parentClick = parentClick;
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    protected void bindDataListener(final V viewhold)
    {
        if (viewhold == null&&!parentClick&&onItemListener==null) {
            return;
        }
        final View view = viewhold.itemView;
        //这里给view设置一个id ,这样可以和子类点击一起使用
        view.setId(Integer.MAX_VALUE);
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





    /************  fullview  ****************/
    //全部布局

    public FullView getFullView() {
        return fullView;
    }
    public void setFullView(FullView fullView) {
        if (loadView==null) {
            throw new UnsupportedOperationException("fullview can not null");
        }
        this.fullView = fullView;
    }
    public void setOnFullListener(OnFullListener onFullListener)
    {
        fullView.setOnFullListener(onFullListener);
    }

    public void setFullState(int statue)
    {
        fullView.setFullState(statue,true);
    }

    public void setFullState(int statue,boolean flush)
    {
        fullView.setFullState(statue,flush);
    }

    /************  loadview  ****************/

    public void setLoadView(LoadView loadView) {
        if (loadView==null) {
            throw new UnsupportedOperationException("loadview can not null");
        }
        this.loadView = loadView;
    }
    private void setLoadState(int statue,boolean flush)
    {
        loadView.setState(statue);
        if (flush) {
            notifyItemChanged(getItemCount()-1);
        }
    }





    /************  item的长度  ****************/
    //adapter的item数量
    @Override
    public int getItemCount() {
        return getFullCount()>0?getFullCount():getHeadCount()+getDataCount()+getFootCount()+getLoadCount();
    }

    private int getFullCount()
    {
        if (mData!=null&&mData.size()>0) {
            return 0;
        }
        return fullView.isShow()?1:0;
    }
    //head长度
    public int getHeadCount()
    {
        if (mHeaderLayout!=null&&mHeaderLayout.getChildCount()>0) {
            return 1;
        }
        return 0;
    }
    //data长度
    public int getDataCount()
    {
        return mData!=null?mData.size():0;
    }
    //foot长度
    private int getFootCount()
    {
        if (mFooterLayout!=null&&mFooterLayout.getChildCount()>0) {
            return 1;
        }
        return 0;
    }
    //加载长度
    private int getLoadCount()
    {
        if (onLoadListener==null) {
            return 0;
        }
        return 1;
    }



    /************  item的类型 ****************/
    @Override
    public int getItemViewType(int position) {
        if (getFullCount()>0) {
            return FULL_ITEM;
        }
        if (position==0&&getHeadCount()!=0)
        {
            return HEAD_ITEM;
        }
        else if (position<getHeadCount()+getDataCount())
        {
            return getDataType(position-getHeadCount());
        }
        else if(position<getHeadCount()+getDataCount()+getFootCount())
        {
            return FOOT_ITEM;
        }
        else
        {
            return LOAD_ITEM;
        }
    }

    //list的数据item类型
    protected int getDataType(int position)
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


    /************  添加头尾   ****************/
    //添加头尾
    public void addHeadView(View header)
    {
        addHeadView(header,-1);
    }

    public void addHeadView(View header,int indext)
    {
        addHeadView(header,indext,LinearLayout.VERTICAL);
    }

    public void addHeadView(View header, int index, int orientation) {
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


    //添加尾部
    public void addFootView(View foot)
    {
        addFootView(foot,-1);
    }

    public void addFootView(View foot,int index)
    {
        addFootView(foot,index,LinearLayout.VERTICAL);
    }

    public void addFootView(View header, int index, int orientation) {
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
            notifyItemInserted(getHeadCount()+getDataCount());
        }
    }
}
