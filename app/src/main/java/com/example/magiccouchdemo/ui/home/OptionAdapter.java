package com.example.magiccouchdemo.ui.home;

import android.content.Context;
import android.util.Log;
import android.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.Option;
import com.example.magiccouchdemo.dataBase.OptionViewModel;
import com.example.magiccouchdemo.dataBase.Theme;
import com.example.magiccouchdemo.dataBase.ThemeViewModel;
import com.example.magiccouchdemo.databinding.OptionItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OptionAdapter extends BaseBindingAdapter<Option, OptionItemBinding>
{
    OptionViewModel model;
    public OptionAdapter(Context context, OptionViewModel model)
    {
        super(context);
        this.model = model;
    }

    @Override
    protected int getLayoutResId(int viewType)
    {
        return R.layout.option_item;
    }

    @Override
    protected void onBindItem(OptionItemBinding binding, Option option)
    {
        binding.setOption(option);
        binding.executePendingBindings();
    }

    public void addData(int position, Option option) {
//      在list中添加数据，并通知条目加入一条
        items.add(position, option);
        //添加动画
        notifyItemInserted(position);
    }

    public String computeResult(int isWithValue){
        Random r = new Random(System.currentTimeMillis());
        int count = getItemCount();
        if(isWithValue==1){
            List<String> tmp = new ArrayList<String>();
            for(int i = 0; i<count; i++){
                String str = items.get(i).getOptionName();
                int cnt = items.get(i).getValue();
                for(int j = 0; j<cnt; j++)
                    tmp.add(str);
            }
            int tmpcnt = tmp.size();
            Log.d("size",""+tmpcnt);
            int i = r.nextInt(tmpcnt);
            Log.d("item:", ""+i);
            return tmp.get(r.nextInt(tmpcnt));
        }
        else {
            int i = r.nextInt(count);
            return items.get(i).getOptionName();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        OptionItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), this.getLayoutResId(viewType), parent, false);
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = items.indexOf(binding.getOption());
                Option option = binding.getOption();
                model.deleteOptions(option);
                items.remove(position);
                //删除动画
                notifyDataSetChanged();
            }
        });
        binding.addValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.getOption().addValue(1);
                notifyDataSetChanged();
            }
        });
        binding.minusValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.getOption().addValue(-1);
                notifyDataSetChanged();
            }
        });
        return new BaseBindingViewHolder(binding.getRoot());
    }
}
