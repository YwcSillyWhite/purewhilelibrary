package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
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

public abstract class BaseLoadAdapter<T,V extends BaseViewHolder> extends BaseAdapter<T,V> {
    //用于延迟
    private Handler handler=new Handler();
    //加载最多项
    private int pageSize=10;
    public final void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    //头部
    private final int HEAD_ITEM=Integer.MIN_VALUE;
    //尾部
    private final int FOOT_ITEM=Integer.MIN_VALUE+1;
    //加载更多
    private final int LOAD_ITEM=Integer.MIN_VALUE+2;
    //全局布局
    private final int FULL_ITEM=Integer.MIN_VALUE+3;
    public BaseLoadAdapter(List<T> mData) {
        super(mData);
    }


    /**
     * 全局布局
     */
    private FullView fullView=new FullViewImp();
    private OnFullListener onFullListener;
    //是否启动fullview
    private boolean isFullView()
    {
        return onFullListener!=null;
    }
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
    public final void setOnFullListener(OnFullListener onFullListener) {
        this.onFullListener=onFullListener;
    }









    /**
     * 加载布局
     */
    private LoadView loadView=new LoadViewImp();
    protected OnLoadListener onLoadListener;
    //是否启动loadview
    private boolean isLoadView()
    {
        return onLoadListener!=null;
    }
    public final void setLoadView(LoadView loadView) {
        if (loadView==null) {
            throw new UnsupportedOperationException("loadview can not null");
        }
        this.loadView = loadView;
    }
    private final void setLoadState(int statue,boolean flush) {
        loadView.setLoadStatue(statue);
        if (isLoadView()&&flush) {
            notifyItemChanged(getItemCount()-1);
        }
    }
    public final void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }








    /**
     * 适配器长度
     * @return
     */
    @Override
    public final int getItemCount() {
        return getFullCount()>0?getFullCount():getHeadCount()+obtainDataCount()+getFootCount()+getLoadCount();
    }
    //全局布局长度
    private final int getFullCount() {
        if (isFullView()&&obtainDataCount()==0&&fullView.isFullView())
        {
            return 1;
        }
        return 0;
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
    //loadview的长度
    private final int getLoadCount() {
        return isLoadView()?1:0;
    }
    @Override
    public int obtainDataHeadCount() {
        return getHeadCount();
    }






    /**
     * item类型
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
            return obtainDataType(position-obtainDataHeadCount());
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
        return super.getItemViewType(position+obtainDataHeadCount());
    }
    @Override
    protected boolean isDataViewType(int viewType) {
        return viewType!=HEAD_ITEM&&viewType!=FOOT_ITEM&&viewType!=LOAD_ITEM&&viewType!=FULL_ITEM;
    }


    /**
     * 创建布局
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    protected V onRestCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        V viewhold=null;
        if (viewType==FULL_ITEM)
        {
            viewhold=createV(parent,fullView.getLayoutId());
            fullView.setItemView(viewhold.itemView);
            if (fullView.getClickLoadId()!=0)
            {
                viewhold.fdbyid(fullView.getClickLoadId()).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (NetWorkUtils.isConnected()&& ClickUtils.clickable(v)) {
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
            viewhold = createV(parent,loadView.getLayoutId());
            viewhold.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (loadView.getLoadStatue()== LoadView.NO_NETWORK && NetWorkUtils.isConnected()&& ClickUtils.clickable(view)) {
                        setLoadState(LoadView.LOAD,true);
                        onLoadListener.loadAgain();
                    }
                }
            });
        }
        return viewhold;
    }



    /**
     * 绑定数据
     * @param holder
     * @param position
     * @param itemViewType
     */
    @Override
    protected void onRestBindViewHolder(V holder, int position, int itemViewType) {
        loadMore(position);
        if (itemViewType==LOAD_ITEM)
        {
            loadView.onBindView(holder);
        }
        else if (itemViewType==FULL_ITEM)
        {
            fullView.onBindView(holder);
        }
    }
    //判断是不是加载更多
    private void loadMore(int position) {
        //loadview长度不能为0，position等于最后一个，position不能为loadview的position
        if (getLoadCount()==0||position<getItemCount()-1||getLoadCount()-1==position) {
            return;
        }
        //加载结束
        if (loadView.getLoadStatue()== LoadView.FINISH) {
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
                setLoadState(LoadView.NO_NETWORK,false);
            }

        }
    }






    /**
     * 设置itemview占用的长度
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager)
        {
            GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    if (isDataViewType(itemViewType)) {
                        return obtainDataGrideSpanSize(position-getHeadCount(),gridManager,itemViewType);
                    }else{
                        return gridManager.getSpanCount();
                    }
                }
            });
        }
    }


    @Override
    public void onViewAttachedToWindow(V holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if(layoutParams != null ) {
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)
            {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                int itemViewType = holder.getItemViewType();
                if (isDataViewType(itemViewType)) {
                    params.setFullSpan(obtianDataStaggered(itemViewType));
                }else {
                    params.setFullSpan(true);
                }
            }
        }
    }

    //GridLayoutManager 数据data占用的长度
    protected int obtainDataGrideSpanSize(int position,GridLayoutManager gridManager,int viewType) {
        return 1;
    }

    protected boolean obtianDataStaggered(int viewType){
        return false;
    }







    /**
     * 头尾
     */
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
            addData(list,network);
        }
    }


    //刷新数据
    public void flush(List<T> list,boolean network) {
        if (list!=null&&list.size()>0)
        {
            setLoadState(list.size()>=pageSize? LoadView.FINISH: LoadView.REST,false);
        }
        else
        {
            setFullState(network? FullView.NO_DATA: FullView.NO_NETWORK,false);
        }
        super.flush(list);
    }


    //添加数据
    public  void addData(List<T> list,boolean network) {
        if (obtainDataCount()==0)
        {
            flush(list,network);
        }
        else
        {
            if (list!=null&&list.size()>0)
            {
                setLoadState(list.size()>=pageSize? LoadView.FINISH: LoadView.NO_DATA,false);
            }
            else
            {
                setLoadState(network? LoadView.NO_DATA: LoadView.NO_NETWORK,true);
            }
            super.addData(list);
        }
    }


    public void flush(List<T> list) {
        flush(list,true);
    }

    public void addData(List<T> list) {
        addData(list,true);
    }
}
