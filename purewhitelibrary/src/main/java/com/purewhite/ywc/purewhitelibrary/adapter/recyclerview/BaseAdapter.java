package com.purewhite.ywc.purewhitelibrary.adapter.recyclerview;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemListener;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BaseViewHolder;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter基础类
 * @param <T>
 * @param <V>
 */
public abstract class BaseAdapter<T,V extends BaseViewHolder> extends RecyclerView.Adapter<V> {

    public BaseAdapter(List<T> mData) {
        this.mData = mData!=null?mData:new ArrayList<>();
    }

    //数据
    private List<T> mData;
    //获取对应position的t
    public final T obtainT(int position)
    {
        if (position<obtainDataCount())
        {
            return mData.get(position);
        }
        return null;
    }
    //数据长度
    protected  int obtainDataCount()
    {
        return mData.size();
    }

    /**
     * 获取数据
     * @return
     */
    public final List<T> obtainData() {
        return mData;
    }



    //布局集合
    private SparseIntArray layoutIds=new SparseIntArray();
    //添加布局
    protected final void addLayout(@LayoutRes int layoutId)
    {
        addLayout(0,layoutId);
    }
    protected final void addLayout(int itemType,@LayoutRes int layoutId)
    {
        if (layoutId!=0)
        {
            layoutIds.append(itemType,layoutId);
        }
    }
    protected final int obtianLayoutId(int itemType)
    {
        return layoutIds.get(itemType, R.layout.pure_adapter_error_layout);
    }



    //点击事件
    protected OnItemListener onItemListener;
    //是否item存在点击事件，默认是true
    private boolean isItemClick=true;
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public void setItemClick(boolean itemClick) {
        isItemClick = itemClick;
    }


    /**
     * 创建布局
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onDataCreateViewHolder(parent,viewType,obtianLayoutId(viewType));
    }

    protected V onDataCreateViewHolder(@NonNull ViewGroup parent, int viewType,@LayoutRes int layoutIds)
    {
        View itemView = obtainView(parent,layoutIds);
        V v = createV(itemView);
        bindDataListener(v);
        return v;
    }

    //创建viewhold
    protected final V createV(View view) {
        return ((V) new BaseViewHolder(view));
    }

    //获取布局
    protected final View obtainView(ViewGroup viewGroup,int layoutIds)
    {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutIds,viewGroup,false);
    }
    //设置点击时间
    protected final void bindDataListener(final BaseViewHolder viewhold) {
        if (viewhold == null||!isItemClick||onItemListener==null) {
            return;
        }
        final View view = viewhold.itemView;
        if (view == null) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ClickUtils.clickable(view))
                {
                    onItemListener.onClick(BaseAdapter.this,view, obtainDataPosition(viewhold.getLayoutPosition()),true);
                }
            }
        });
    }
    //获取data数据
    protected int obtainDataPosition(int adapterPosition)
    {
        return adapterPosition;
    }
    //获取adapter数据
    protected int obtainAdapterPosition(int dataPosition)
    {
        return dataPosition;
    }



    /**
     * 绑定布局
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        onDataBindView(holder,position,holder.getItemViewType());
    }

    protected void onDataBindView(V holder,int position,int itemViewType)
    {
        onData(holder,position,obtainT(position),itemViewType);
    }
    //赋值数据
    protected abstract void onData(V holder,int position,T t,int itemViewType);




    /**
     * recyclerview的长度
     * @return
     */
    @Override
    public int getItemCount() {
        return obtainDataCount();
    }





    //刷新
    public void flush(List<T> list)
    {
        if (mData.size()>0)
        {
            mData.clear();
        }
        if (list!=null&&list.size()>0)
        {
            mData.addAll(list);
            notifyDataSetChanged();
        }
    }
    //刷新某个数据
    public void flushPosition(int position)
    {
        notifyItemChanged(obtainAdapterPosition(position));
    }
    //添加数据
    public void addDatas(List<T> list)
    {
        if (list!=null&&list.size()>0)
        {
            mData.addAll(list);
            notifyItemRangeInserted(obtainAdapterPosition(obtainDataCount()-list.size()), list.size());
        }
    }
    //添加单个数据
    public void addData(T t)
    {
        if (t!=null)
        {
            mData.add(t);
            notifyItemRangeInserted(obtainAdapterPosition(obtainDataCount()-1),1);
        }
    }
    //删除某个数据
    public void removePosition(int position)
    {
        if (position<obtainDataCount())
        {
            mData.remove(position);
            notifyItemRemoved(obtainAdapterPosition(position));
        }
    }
}
