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
import com.purewhite.ywc.purewhitelibrary.adapter.callback.OnItemLongListener;
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
        if (mData!=null) {
            this.mData.addAll(mData);
        }
    }

    /**
     * 数据
     */
    private List<T> mData=new ArrayList<>();
    public final T obtainT(int position)
    {
        if (position<obtainDataCount())
        {
            return mData.get(position);
        }
        return null;
    }
    protected int obtainDataCount()
    {
        return mData.size();
    }
    public final List<T> obtainData() {
        return mData;
    }






    /**
     * 布局
     */
    private SparseIntArray layoutIds=new SparseIntArray();
    protected final void addLayout(@LayoutRes int layoutId)
    {
        addLayout(0,layoutId);
    }
    protected final void addLayout(int viewType,@LayoutRes int layoutId)
    {
        if (layoutId!=0)
        {
            layoutIds.append(viewType,layoutId);
        }
    }
    protected final int obtainLayoutId(int viewType)
    {
        return layoutIds.get(viewType, R.layout.pure_adapter_error_layout);
    }




    /**
     * 点击事件
     */
    protected OnItemListener onItemListener;
    private boolean isItemClick=true;
    public final void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public final void setItemClick(boolean itemClick) {
        isItemClick = itemClick;
    }
    protected OnItemLongListener onItemLongListener;
    private boolean isItemLongClick=true;
    public final void setOnItemLongListener(OnItemLongListener onItemLongListener) {
        this.onItemLongListener = onItemLongListener;
    }
    public final void setItemLongClick(boolean itemLongClick) {
        isItemLongClick = itemLongClick;
    }
    //设置点击时间
    protected final void bindDataListener(final V viewhold) {
        if (viewhold==null)
            return;
        View itemView = viewhold.itemView;
        if (itemView==null)
            return;
        if (isItemClick&&onItemListener!=null)
        {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ClickUtils.clickable(view))
                    {
                        int layoutPosition = viewhold.getLayoutPosition();
                        onItemListener.onClick(BaseAdapter.this,view, layoutPosition-obtainDataHeadCount(),true);
                    }
                }
            });
        }
        if (isItemLongClick&&onItemLongListener!=null)
        {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int layoutPosition = viewhold.getLayoutPosition();
                    return onItemLongListener.onClick(BaseAdapter.this,view,layoutPosition-obtainDataHeadCount(),true);
                }
            });
        }
    }





    /**
     * 类型判断
     * @param viewType
     * @return
     */
    protected boolean isDataViewType(int viewType){
        return true;
    }





    /**
     * createViewholder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public final V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return isDataViewType(viewType)?onDataCreateViewHolder(parent,viewType):onRestCreateViewHolder(parent,viewType);
    }

    private final V onDataCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        int layoutId = obtainLayoutId(viewType);
        V v = onDataCreateV(parent, layoutId);
        //
        bindDataListener(v);
        return v;
    }
    protected V onDataCreateV(@NonNull ViewGroup parent,@LayoutRes int layoutId){
        return createV(parent,layoutId);
    }

    protected V onRestCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return null;
    }

    protected final V createV(@NonNull ViewGroup parent,@LayoutRes int layoutid){
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutid, parent, false);
        return createV(view);
    }

    //创建viewhold
    protected final V createV(View view) {
        return ((V) new BaseViewHolder(view));
    }







    /**
     * 绑定布局
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (isDataViewType(itemViewType)){
            onDataBindViewHolder(holder,position-obtainDataHeadCount(),itemViewType);
        }else{
            onRestBindViewHolder(holder,position,itemViewType);
        }

    }
    protected void onDataBindViewHolder(V holder,int position,int itemViewType)
    {
        onData(holder,position,obtainT(position),itemViewType);
    }

    protected void onRestBindViewHolder(V holder,int position,int itemViewType){

    }
    protected abstract void onData(V holder,int dataPosition,T t,int itemViewType);







    /**
     * recyclerview的长度
     * @return
     */
    @Override
    public int getItemCount() {
        return obtainDataCount();
    }
    //data数据上面的长度
    public int obtainDataHeadCount(){
        return 0;
    }






    /**
     *处理数据
     */

    public void flush(List<T> data){
        if (mData.size()>0){
            mData.clear();
            if (data!=null&&data.size()>0){
                mData.addAll(data);
            }
            notifyDataSetChanged();
        }else{
            if (data!=null&&data.size()>0) {
                mData.addAll(data);
                notifyDataSetChanged();
            }
        }
    }
    public void flush(T t) {
        if (mData.size()>0){
            mData.clear();
            if (t!=null){
                mData.add(t);
            }
            notifyDataSetChanged();
        }else{
            if (t!=null) {
                mData.add(t);
                notifyDataSetChanged();
            }
        }
    }
    public void flushData(int position){
        notifyItemChanged(position+obtainDataHeadCount());
    }
    public void addData(List<T> data){
        if (mData.size()>0){
            if (data!=null&&data.size()>0){
                mData.addAll(data);
                notifyItemRangeInserted(obtainDataCount()+obtainDataHeadCount()-data.size(),data.size());
            }
        }else{
            if (data!=null&&data.size()>0){
                mData.addAll(data);
                notifyDataSetChanged();
            }
        }
    }
    public void addData(T t){
        if (mData.size()>0){
            if (t!=null){
                mData.add(t);
                notifyItemInserted(obtainDataCount()+obtainDataHeadCount()-1);
            }
        }else{
            if (t!=null){
                mData.add(t);
                notifyDataSetChanged();
            }
        }
    }
    public void removePosition(int position)
    {
        if (position<obtainDataCount())
        {
            mData.remove(position);
            notifyItemRemoved(obtainDataHeadCount());
        }
    }
}
