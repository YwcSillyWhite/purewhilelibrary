package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnFullListener;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnLoadListener;
import com.purewhite.ywc.purewhitelibrary.adapter.fullview.FullView;
import com.purewhite.ywc.purewhitelibrary.adapter.fullview.FullViewImp;
import com.purewhite.ywc.purewhitelibrary.adapter.loadview.LoadView;
import com.purewhite.ywc.purewhitelibrary.adapter.loadview.LoadViewImp;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhitelibrary.config.NetWorkUtils;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * 头尾加载更多全局适配器
 * @author yuwenchao
 * @date 2018/11/15
 */

public abstract class BaseMoreAdapter<T,V extends BaseViewHolder> extends BaseAdapter<T,V>{

    //用于延迟
    private Handler handler=new Handler();
    //加载最多项
    private int pageSize=10;
    public final void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    private final int HEAD_ITEM=Integer.MIN_VALUE;
    private final int FOOT_ITEM=Integer.MIN_VALUE+1;
    private final int LOAD_ITEM=Integer.MIN_VALUE+2;
    private final int FULL_ITEM=Integer.MIN_VALUE+3;

    public BaseMoreAdapter(List<T> mData) {
        super(mData);
    }

    /**
     * 适配器长度
     * @return
     */
    @Override
    public final int getItemCount() {
        return getFullCount()>0?getFullCount():getHeadCount()+obtainDataCount()+getFootCount()+getLoadCount();
    }
    //头部数据的长度
    private final int getHeadCount() {
        if (mHeaderLayout!=null&&mHeaderLayout.getChildCount()>0) {
            return 1;
        }
        return 0;
    }
    //尾部长度
    private final int getFootCount() {
        if (mFooterLayout!=null&&mFooterLayout.getChildCount()>0) {
            return 1;
        }
        return 0;
    }


    //没有数据长度
    private final int getFullCount() {
        if (isFullView()&&obtainDataCount()==0&&fullView.isFullView())
        {
            return 1;
        }
        return 0;
    }
    //loadview的长度
    private final int getLoadCount() {
        return isLoadView()?1:0;
    }

    @Override
    protected int obtainAdapterPosition(int dataPosition) {
        return dataPosition+getHeadCount();
    }

    @Override
    protected int obtainDataPosition(int adapterPosition) {
        return adapterPosition-getHeadCount();
    }

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
        else if (position<getHeadCount()+obtainDataCount())
        {
            return obtainDataType(position-getHeadCount());
        }
        else if(position<getHeadCount()+obtainDataCount()+getFootCount())
        {
            return FOOT_ITEM;
        }
        else
        {
            return LOAD_ITEM;
        }
    }
    //data数据类型
    protected int obtainDataType(int position) {
        return super.getItemViewType(position+getHeadCount());
    }



    /************  onCreateViewHolder ****************/
    @Override
    public final V onCreateViewHolder(ViewGroup parent, int viewType) {
        V viewhold;
        if (viewType==FULL_ITEM)
        {
            View view =obtainView(parent,fullView.getLayoutId());
            fullView.setItemView(view);
            viewhold=createV(view);
            if (fullView.getClickLoadId()!=0)
            {
                viewhold.findViewId(fullView.getClickLoadId()).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (NetWorkUtils.isConnected()&&onFullListener!=null&&ClickUtils.clickable(v))
                                {
                                    setFullState(FullView.LODA,true);
                                    onFullListener.loadAgain();
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
            View view = obtainView(parent,loadView.getLayoutId());
            viewhold=createV(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (loadView.getLoadStatue()==LoadView.NETWORK &&NetWorkUtils.isConnected()&&ClickUtils.clickable(view))
                    {
                        setLoadState(LoadView.LOAD,true);
                        onLoadListener.loadAgain();
                    }
                }
            });
        }
        else
        {
            return onDataCreateViewHolder(parent,viewType,obtianLayoutId(viewType));
        }
        return viewhold;
    }

    /************  item的类型 ****************/





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
            onDataBindView(holder,obtainDataPosition(position),itemViewType);
        }
    }


    //判断是不是加载更多
    private void loadMore(int position) {
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

    //判断是不是data数据类型
    public boolean dataType(RecyclerView.ViewHolder viewhold) {
        return dataType(viewhold.getItemViewType());
    }

    public boolean dataType(int viewType) {
        return viewType!=HEAD_ITEM&&viewType!=FOOT_ITEM &&viewType!=LOAD_ITEM&&viewType!=FULL_ITEM;
    }

    protected int obtainDataSpanSize(int position,GridLayoutManager gridManager) {
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







    /**        fullview         **/
    //全局布局
    private FullView fullView=new FullViewImp();
    private OnFullListener onFullListener;
    //设置全局布局
    public final void setFullView(FullView fullView) {
        if (fullView==null) {
            throw new UnsupportedOperationException("fullview can not null");
        }
        this.fullView = fullView;
    }

    public final void setFullState(int state) {
        setFullState(state,false);
    }
    //设置full状态，并且是否刷新
    public final void setFullState(int statue,boolean flush) {
        fullView.setFullState(statue);
        if (isFullView()&&flush) {
            notifyDataSetChanged();
        }
    }

    //是否启动fullview
    private boolean isFullView()
    {
        return onFullListener!=null;

    }
    //设置全局布局的监听
    public final void setOnFullListener(OnFullListener onFullListener) {
        this.onFullListener=onFullListener;
    }
    /**        fullview         **/









    /**        loadview         **/
    //加载布局
    private LoadView loadView=new LoadViewImp();
    //loadview滑动监听
    protected OnLoadListener onLoadListener;
    //设置加载布局
    public final void setLoadView(LoadView loadView) {
        if (loadView==null) {
            throw new UnsupportedOperationException("loadview can not null");
        }
        this.loadView = loadView;
    }
    //是否启动loadview
    private boolean isLoadView()
    {
        return onLoadListener!=null;
    }
    //设置loadview的状态
    private final void setLoadState(int statue,boolean flush) {
        loadView.setLoadStatue(statue);
        if (isLoadView()&&flush) {
            notifyItemChanged(getItemCount()-1);
        }
    }
    //设置滑动监听
    public final void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }
    /**        loadview       **/





    /************  添加删除头尾   ****************/
    //头部
    private LinearLayout mHeaderLayout;
    //尾部
    private LinearLayout mFooterLayout;
    //添加头尾
    public final void addHeadView(View header) {
        addHeadView(header,-1);
    }

    public final void addHeadView(View header,int indext) {
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


    public final void removeHeadView(View head) {
        if (mHeaderLayout!=null)
        {
            mHeaderLayout.removeView(head);
            notifyItemInserted(0);
        }
    }

    public final void removeHeadView(int position) {
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
    public final void addFootView(View foot) {
        addFootView(foot,-1);
    }

    public final void addFootView(View foot,int index) {
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
            notifyItemInserted(getHeadCount()+obtainDataCount());
        }
    }

    public final void removeFootView(View head) {
        if (mFooterLayout!=null)
        {
            mFooterLayout.removeView(head);
            notifyItemInserted(getHeadCount()+obtainDataCount());
        }
    }

    public final void removeFootView(int position) {
        if (mFooterLayout!=null)
        {
            int childCount = mFooterLayout.getChildCount();
            if (position<childCount)
            {
                mFooterLayout.removeViewAt(position);
                notifyItemInserted(getHeadCount()+obtainDataCount());
            }
        }
    }

    /************  添加删除头尾   ****************/










    /**
     * 数据处理
     * @param page
     * @param list
     * @param network
     */
    public void addDataFlush(int page,List<T> list,boolean network) {
        addDataFlush(page==1,list,network);
    }


    public void addDataFlush(boolean flush,List<T> list,boolean network) {
        if (flush)
        {
            flush(list,network);
        }
        else
        {
            addDatas(list,network);
        }
    }


    //刷新数据
    public void flush(List<T> list,boolean network) {
        if (list!=null&&list.size()>0)
        {
            setLoadState(list.size()>=pageSize?LoadView.FINISH:LoadView.REST,false);
        }
        else
        {
            setFullState(network?FullView.DATA:FullView.NETWORK,false);
        }
        super.flush(list);
    }


    //添加数据
    public  void addDatas(List<T> list,boolean network) {
        if (obtainDataCount()==0)
        {
            flush(list,network);
        }
        else
        {
            if (list!=null&&list.size()>0)
            {
                setLoadState(list.size()>=pageSize?LoadView.FINISH:LoadView.DATA,false);
            }
            else
            {
                setLoadState(network?LoadView.DATA:LoadView.NETWORK,true);
            }
            super.addDatas(list);
        }
    }


    public void flush(List<T> list) {
        flush(list,true);
    }

    public void addDatas(List<T> list) {
        addDatas(list,true);
    }
}
