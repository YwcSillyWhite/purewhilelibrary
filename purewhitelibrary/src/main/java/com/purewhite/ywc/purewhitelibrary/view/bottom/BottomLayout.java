package com.purewhite.ywc.purewhitelibrary.view.bottom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.click.OnSingleListener;


/**
 *这次优化准要是解决，如果底部view显示在当前fragment，那么子bottom该如何变化
 * @author yuwenchao
 * @date 2018/11/3
 */

public class BottomLayout extends LinearLayout{

    private OnBottomLayoutChageListener onBottomLayoutChageListener;
    private int bottomLayout_checkPosition;
    private SparseArray<BottomMenu> viewArray;
    //上次选中bottom
    private BottomMenu lastView;
    private BottomMenu lastLastView;

    public void addOnBottomLayoutChageListener(OnBottomLayoutChageListener onBottomLayoutChageListener) {
        this.onBottomLayoutChageListener = onBottomLayoutChageListener;
    }

    public BottomLayout(Context context) {
        super(context,null);
    }

    public BottomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);

    }

    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    //初始化
    private void initView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BottomLayout);
        bottomLayout_checkPosition = typedArray.getInteger(R.styleable.BottomLayout_checkPosition, 0);
    }


    //布局初始化
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        int childCount = getChildCount();
        if (childCount==0)
            return;
        viewArray=new SparseArray<>();
        int position=0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view instanceof BottomMenu)
            {
                viewArray.put(position, ((BottomMenu) view));
                view.setOnClickListener(onSingleListener);
                position++;
            }
        }
        lastView = viewArray.get(bottomLayout_checkPosition,null)==null
                ?viewArray.get(0):viewArray.get(bottomLayout_checkPosition,null);
        lastView.setInitCheck();
    }


    //清空选中
    public void clearCheck()
    {
        if (lastView!=null)
        {
            lastView.setCheck(false);
            lastView=null;
        }
    }



    /***** 事件监听 *****/

    private OnClickListener onSingleListener=new OnSingleListener() {
        @Override
        public void onSingleClick(View view) {
            childChange(((BottomMenu) view));
        }
    };

    private void childChange(BottomMenu view)
    {
        if (onBottomLayoutChageListener==null||lastView==view)
            return;
        if (lastView!=null)
        {
            lastLastView=lastView;
            lastView.setCheck(false);
        }
        lastView=view;
        view.setCheck(true);
        onBottomLayoutChageListener.onCheckChange(view);
    }



    /***** io接口  *****/
    public interface OnBottomLayoutChageListener
    {
        void onCheckChange(View view);
    }
    
}
