package com.purewhite.ywc.frame1.ui.adapter;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.purewhite.ywc.frame1.R;
import com.purewhite.ywc.frame1.databinding.AdapterAndroidStudyBinding;
import com.purewhite.ywc.purewhitelibrary.adapter.recyclerview.BindAdapter;
import com.purewhite.ywc.purewhitelibrary.adapter.viewholder.BindHolder;

import java.util.List;

public class AndroidStudyAdapter extends BindAdapter<String> {

    private List<String> listUri;
    public AndroidStudyAdapter(List<String> list,List<String> listUri) {
        super(list);
        this.listUri=listUri;
        addLayout(R.layout.adapter_android_study);
    }

    public String obtainUri(int position)
    {
        if (getItemCount()>position)
            return listUri.get(position);
        return "";
    }


    @Override
    public int obtianDataCount() {
        return super.obtianDataCount();
    }


    @Override
    protected void onData(BindHolder holder, int position, String s) {
        ViewDataBinding binding = holder.getBinding();
        if (binding instanceof AdapterAndroidStudyBinding)
        {
            initBind(((AdapterAndroidStudyBinding) binding),position,s);
        }
    }

    private void initBind(AdapterAndroidStudyBinding binding, int position, String s) {
        binding.androidStudyText.setText(s);
        binding.viewLine.setVisibility(position==obtianDataCount()-1?View.GONE:View.VISIBLE);
    }

}
