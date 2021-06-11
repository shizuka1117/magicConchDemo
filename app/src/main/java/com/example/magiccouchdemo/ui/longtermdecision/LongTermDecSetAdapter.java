package com.example.magiccouchdemo.ui.longtermdecision;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.LongOptionItemBinding;
import com.example.magiccouchdemo.databinding.OptionItemBinding;
import com.example.magiccouchdemo.ui.home.BaseBindingAdapter;
import com.example.magiccouchdemo.ui.home.BaseBindingViewHolder;
import com.example.magiccouchdemo.dataBase.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LongTermDecSetAdapter extends BaseBindingAdapter<Option, LongOptionItemBinding> {
    public LongTermDecSetAdapter(Context context)
    {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType)
    {
        return R.layout.long_option_item;
    }

    @Override
    protected void onBindItem(LongOptionItemBinding binding, Option item) {
        binding.setOption(item);
        binding.executePendingBindings();
    }


    public void addData(int position, Option option) {
//      在list中添加数据，并通知条目加入一条
        items.add(position, option);
        //添加动画
        notifyItemInserted(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LongOptionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), this.getLayoutResId(viewType), parent, false);
        binding.delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = items.indexOf(binding.getOption());
                items.remove(position);
                //删除动画
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
        return new BaseBindingViewHolder(binding.getRoot());
    }

}
