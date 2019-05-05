package com.purewhite.ywc.purewhitelibrary.view.bottom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.purewhite.ywc.purewhitelibrary.R;


/**
 *
 * @author yuwenchao
 * @date 2018/11/2
 */

public class BottomMenu extends RelativeLayout{
    //是否选中
    private boolean isCheck;
    //图片
    private ImageView bottomImg;
    //内容
    private TextView bottomTv;
    //消息数
    private TextView bottomNum;
    private int checkTv_true;
    private int checkTv_flase;
    private int checkImg_flase;
    private int checkImg_true;
    private float dimensionPixelSize;
    private boolean anmiable;
    private String text;

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);

    }

    private void initView(Context context, AttributeSet attrs) {
        //初始化布局
        View view = View.inflate(context, R.layout.view_bottom_menu, this);
        bottomImg = ((ImageView) view.findViewById(R.id.bottomImg));
        bottomTv = ((TextView) view.findViewById(R.id.bottomTv));
        bottomNum = ((TextView) view.findViewById(R.id.bottomNum));
        //获取xml属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.BottomMenu);
        checkTv_true = typedArray.getColor(R.styleable.BottomMenu_checkTv_true, 0Xffffffff);
        checkTv_flase = typedArray.getColor(R.styleable.BottomMenu_checkTv_flase, 0Xff333333);
        checkImg_flase = typedArray.getResourceId(R.styleable.BottomMenu_checkImg_flase, R.mipmap.icon_load_error);
        checkImg_true = typedArray.getResourceId(R.styleable.BottomMenu_checkImg_true,  R.mipmap.icon_load_error);
        dimensionPixelSize = typedArray.getDimension(R.styleable.BottomMenu_Tvsize, 15);
        anmiable = typedArray.getBoolean(R.styleable.BottomMenu_anmiable, true);
        text = typedArray.getString(R.styleable.BottomMenu_text);
        //设置数值
        setMessageNum(0);
        bottomTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,dimensionPixelSize);
        bottomTv.setText(text!=null&&!text.isEmpty()?text:"设置初始值");
        setData();

    }


    //动画
    private void anim()
    {
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        setPivotX(getWidth()/2);
        setPivotY(getHeight()/2);
        valueAnimator.setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                if(progress < 0.3f){
                    setScaleX(1-progress*0.2f/0.3f);
                    setScaleY(1-progress*0.2f/0.3f);
                } else {
                    setScaleX(0.7f+progress*0.3f);
                    setScaleY(0.7f+progress*0.3f);
                }
            }
        });
        valueAnimator.start();
    }

    //设置是否选中
    public void setCheck(boolean check)
    {
        if (isCheck==check)
            return;
        isCheck=check;
        setData();
        if (isCheck)
        {
            if (anmiable)
                anim();
            setMessageNum(0);
        }
    }

    /**
     * 初始化被选中
     */
    public void setInitCheck() {
        isCheck=true;
        setData();
    }

    //设置选中状态
    private void setData()
    {
        bottomImg.setImageResource(isCheck?checkImg_true:checkImg_flase);
        bottomTv.setTextColor(isCheck?checkTv_true:checkTv_flase);
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
