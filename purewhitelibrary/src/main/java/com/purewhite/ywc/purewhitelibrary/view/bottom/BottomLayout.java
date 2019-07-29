package com.purewhite.ywc.purewhitelibrary.view.bottom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.purewhite.ywc.purewhitelibrary.R;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;


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

    /***** 事件监听 *****/
    private OnClickListener onClickListener=new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (ClickUtils.clickable(view)&&view instanceof BottomMenu)
            {
                if (lastView!=null&&lastView==view)
                    return;
                BottomMenu bottomMenu = (BottomMenu) view;
                if (lastView!=null)
                {
                    lastView.setCheck(false);
                }
                lastView=bottomMenu;
                bottomMenu.setCheck(true);
                if (onBottomLayoutChageListener!=null)
                {
                    onBottomLayoutChageListener.onCheckChange(bottomMenu);
                }
            }
        }
    };

    //添加绑定
    public void addOnBottomLayoutChageListener(OnBottomLayoutChageListener onBottomLayoutChageListener) {
        this.onBottomLayoutChageListener = onBottomLayoutChageListener;
    }

    //解除绑定
    public void removeOnBottomLayoutChageListener()
    {
        this.onBottomLayoutChageListener=null;
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
        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BottomLayout);
            bottomLayout_checkPosition = typedArray.getInteger(R.styleable.BottomLayout_checkPosition, 0);
            //释放资源
            typedArray.recycle();
        }

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
                view.setOnClickListener(onClickListener);
                position++;
            }
        }
        lastView = viewArray.get(bottomLayout_checkPosition,null)==null ?viewArray.get(0)
                :viewArray.get(bottomLayout_checkPosition,null);
        if (lastView!=null)
        {
            lastView.setInitCheck();
        }
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




    /***** io接口  *****/
    public interface OnBottomLayoutChageListener
    {
        void onCheckChange(BottomMenu view);
    }
    
}
