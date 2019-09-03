package com.purewhite.ywc.frame.ui.activity.mine;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.google.gson.Gson;
import com.purewhite.ywc.frame.R;
import com.purewhite.ywc.frame.databinding.ActivityWheelBinding;
import com.purewhite.ywc.frame.ui.mvp.MvpActivity;
import com.purewhite.ywc.frame.wheel.FourWheelAdapter;
import com.purewhite.ywc.frame.wheel.OneWheelAdapter;
import com.purewhite.ywc.frame.wheel.ThreeWheelAdapter;
import com.purewhite.ywc.frame.wheel.TwoWheelAdapter;
import com.purewhite.ywc.frame.wheel.bean.WheelBean;
import com.purewhite.ywc.frame.wheel.callback.WheelCallBack;
import com.purewhite.ywc.purewhitelibrary.config.click.ClickUtils;
import com.purewhite.ywc.purewhitelibrary.mvp.presenter.PresenterImp;

import java.util.List;

public class WheelViewActivity extends MvpActivity<ActivityWheelBinding,PresenterImp> implements View.OnClickListener {

    @Override
    protected int getLayout() {
        return R.layout.activity_wheel;
    }


    WheelBean wheelBean;
    @Override
    protected void initView() {

        mDataBinding.titleBarLayout.centerText.setText("wheelview");
        mDataBinding.titleBarLayout.centerText.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.leftImg.setVisibility(View.VISIBLE);
        mDataBinding.titleBarLayout.leftImg.setOnClickListener(this);

        mDataBinding.morePickerView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                WheelView wheelView = mDataBinding.morePickerView.obtianPisitonWheelView(0);
                if (wheelView!=null)
                {
                    WheelAdapter adapter = wheelView.getAdapter();
                    if (adapter instanceof OneWheelAdapter)
                    {
                        List<WheelBean.DataBean> dataBeans = ((OneWheelAdapter) adapter).obtainList();
                        setTwoWheel(dataBeans.get(index).getChildren());
                    }
                }
            }
        }, new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                WheelView wheelView = mDataBinding.morePickerView.obtianPisitonWheelView(1);
                if (wheelView!=null)
                {
                    WheelAdapter adapter = wheelView.getAdapter();
                    if (adapter instanceof TwoWheelAdapter)
                    {
                        List<WheelBean.DataBean.ChildrenBeanXX> childrenBeanXXES = ((TwoWheelAdapter) adapter).obtainList();
                        setThreeWheel(childrenBeanXXES.get(index).getChildren());
                    }
                }
            }
        }, new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                WheelView wheelView = mDataBinding.morePickerView.obtianPisitonWheelView(2);
                if (wheelView!=null)
                {
                    WheelAdapter adapter = wheelView.getAdapter();
                    if (adapter instanceof ThreeWheelAdapter)
                    {
                        List<WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX> childrenBeanXES = ((ThreeWheelAdapter) adapter).obtainList();
                        setFourWheel(childrenBeanXES.get(index).getChildren());
                    }
                }
            }
        }, new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });


        mDataBinding.sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataBinding.morePickerView.obtianData();
            }
        });

        mDataBinding.morePickerView.setWheelCallBack(new WheelCallBack() {
            @Override
            public void callBack(List<WheelView> list) {
                StringBuffer stringBuffer=new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    WheelView wheelView = list.get(i);
                    switch (i)
                    {
                        case 0:
                            WheelAdapter adapter1 = wheelView.getAdapter();
                            if (adapter1 instanceof OneWheelAdapter)
                            {
                                List<WheelBean.DataBean> dataBeans = ((OneWheelAdapter) adapter1).obtainList();
                                stringBuffer.append(dataBeans.get(wheelView.getCurrentItem()).getName());
                            }
                            break;
                        case 1:
                            WheelAdapter adapter2 = wheelView.getAdapter();
                            if (adapter2 instanceof TwoWheelAdapter)
                            {
                                List<WheelBean.DataBean.ChildrenBeanXX> childrenBeanXXES = ((TwoWheelAdapter) adapter2).obtainList();
                                stringBuffer.append(childrenBeanXXES.get(wheelView.getCurrentItem()).getName());
                            }
                            break;
                        case 2:
                            WheelAdapter adapter3 = wheelView.getAdapter();
                            if (adapter3 instanceof ThreeWheelAdapter)
                            {
                                List<WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX> childrenBeanXES = ((ThreeWheelAdapter) adapter3).obtainList();
                                stringBuffer.append(childrenBeanXES.get(wheelView.getCurrentItem()).getName());
                            }
                            break;
                        case 3:
                            WheelAdapter adapter4 = wheelView.getAdapter();
                            if (adapter4 instanceof FourWheelAdapter)
                            {
                                List<WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX.ChildrenBean> childrenBeans = ((FourWheelAdapter) adapter4).obtainList();
                                stringBuffer.append(childrenBeans.get(wheelView.getCurrentItem()).getName());
                            }
                            break;
                    }
                }
                mDataBinding.textView.setText(stringBuffer.toString());
            }
        });

        Gson gson = new Gson();
        wheelBean= gson.fromJson(getResources().getString(R.string.json), WheelBean.class);
        setOneWheel(wheelBean);



        mDataBinding.recyclerView.setAdapter(new com.purewhite.ywc.frame.ui.activity.mine.WheelAdapter());
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new LinearSnapHelper().attachToRecyclerView(mDataBinding.recyclerView);


        mDataBinding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });




    }







    private void setOneWheel(WheelBean wheelBean)
    {
        if (wheelBean!=null&&wheelBean.getData()!=null&&wheelBean.getData().size()>0)
        {
            mDataBinding.morePickerView.setWheelAdapterPosition(0,new OneWheelAdapter(wheelBean.getData()));
            setTwoWheel(wheelBean.getData().get(0).getChildren());
        }
        else
        {
            mDataBinding.morePickerView.setWheelViewList(0,null,null,null,null);
        }
    }

    private void setTwoWheel(List<WheelBean.DataBean.ChildrenBeanXX> dataBeans)
    {
        if (dataBeans!=null&&dataBeans.size()>0)
        {
            mDataBinding.morePickerView.setWheelAdapterPosition(1,new TwoWheelAdapter(dataBeans));
            setThreeWheel(dataBeans.get(0).getChildren());
        }
        else
        {
            mDataBinding.morePickerView.setWheelViewList(1,null,null,null);
        }
    }

    private void setThreeWheel(List<WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX> childrenBeanXES)
    {
        if (childrenBeanXES!=null&&childrenBeanXES.size()>0)
        {
            mDataBinding.morePickerView.setWheelAdapterPosition(2,new ThreeWheelAdapter(childrenBeanXES));
            setFourWheel(childrenBeanXES.get(0).getChildren());
        }
        else
        {
            mDataBinding.morePickerView.setWheelViewList(2,null,null);
        }
    }

    private void setFourWheel(List<WheelBean.DataBean.ChildrenBeanXX.ChildrenBeanX.ChildrenBean> childrenBeans)
    {
        if (childrenBeans!=null&&childrenBeans.size()>0)
        {
            mDataBinding.morePickerView.setWheelAdapterPosition(3,new FourWheelAdapter(childrenBeans));
        }
        else
        {
//            mDataBinding.morePickerView.setWheelViewList(3,null);
        }
    }

    @Override
    public void onClick(View view) {
        if (ClickUtils.clickable(view))
        {
            switch (view.getId())
            {
                case R.id.left_img:
                    backActivity();
                    break;
            }
        }
    }
}
