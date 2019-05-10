package com.purewhite.ywc.purewhitelibrary.view.bottom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.purewhite.ywc.purewhitelibrary.R;


/**
 *
 * @author yuwenchao
 * @date 2018/11/2
 */

public class BottomMenu extends FrameLayout {
    //是否选中
    private boolean isCheck;
    //图片
    private ImageView bottomImg;
    //内容
    private TextView bottomTv;
    //消息数
    private TextView bottomNum;

    private boolean anmiable;

    private String text;
    private int text_check_true;
    private int text_check_false;
    private String text_content;
    private int text_size;
    private int img_check_true;
    private int img_check_false;
    private float img_size;
    private float center_distance;

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        View view = View.inflate(context,R.layout.view_bottom_menu , this);
        bottomImg = ((ImageView) view.findViewById(R.id.bottomImg));
        bottomTv = ((TextView) view.findViewById(R.id.bottomTv));
        bottomNum = ((TextView) view.findViewById(R.id.bottomNum));
        //获取xml属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.BottomMenu);

        text_check_true = typedArray.getColor(R.styleable.BottomMenu_text_check_true, 0Xff333333);
        text_check_false = typedArray.getColor(R.styleable.BottomMenu_text_check_false, 0Xff666666);
        text_content = typedArray.getString(R.styleable.BottomMenu_text_content);
        text_size = typedArray.getDimensionPixelSize(R.styleable.BottomMenu_text_size, -1);

        img_check_true = typedArray.getResourceId(R.styleable.BottomMenu_img_check_true, R.mipmap.icon_load_error);
        img_check_false = typedArray.getResourceId(R.styleable.BottomMenu_img_check_false, R.mipmap.icon_load_error);
        img_size = typedArray.getDimension(R.styleable.BottomMenu_img_size, -1);
        anmiable = typedArray.getBoolean(R.styleable.BottomMenu_anmiable, true);

        center_distance = typedArray.getDimension(R.styleable.BottomMenu_center_distance, -1);
        //设置数值
        setMessageNum(0);
        if (text_size>0)
        {
            bottomTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,text_size);
        }

        if (img_size>0)
        {

            ViewGroup.LayoutParams layoutParams = bottomImg.getLayoutParams();
            layoutParams.width= ((int) img_size);
            layoutParams.height= ((int) img_size);
        }

        if (center_distance>0)
        {
            LinearLayout.LayoutParams layoutParams = ( LinearLayout.LayoutParams)bottomTv.getLayoutParams();
            layoutParams.topMargin= ((int) center_distance);
        }

        if (!TextUtils.isEmpty(text_content))
        {
            bottomTv.setText(text_content);
        }

        setData();

    }


    //动画
    private void animBig()
    {
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        setPivotX(getWidth()/2);
        setPivotY(getHeight()/2);
        valueAnimator.setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                setScaleX(1+progress*0.1f);
                setScaleY(1+progress*0.1f);
            }
        });
        valueAnimator.start();

    }



    //    设置是否选中
    public void setCheck(boolean check)
    {
        if (isCheck==check)
            return;
        isCheck=check;
        setData();
        if (isCheck)
        {
            if (anmiable)
                animBig();
            setMessageNum(0);
        }
        else
        {
            setScaleX(1);
            setScaleY(1);
        }
    }

    /**
     * 初始化被选中
     */
    public void setInitCheck() {
        isCheck=true;
        setData();
        setScaleX(1.1f);
        setScaleY(1.1f);
    }

    //设置选中状态
    private void setData()
    {
        bottomImg.setImageResource(isCheck?img_check_true:img_check_false);
        bottomTv.setTextColor(isCheck?text_check_true:text_check_false);
    }

    //设置消息数
    public void setMessageNum(int num)
    {
        if (num<=0)
        {
            bottomNum.setVisibility(GONE);
        }
        else
        {
            bottomNum.setVisibility(VISIBLE);
            if (num>99)
            {
                bottomNum.setText("99+");
            }
            else
            {
                bottomNum.setText(num+"");
            }
        }
    }

}
