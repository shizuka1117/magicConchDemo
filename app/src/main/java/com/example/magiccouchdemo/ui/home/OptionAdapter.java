package com.example.magiccouchdemo.ui.home;

import android.content.Context;


import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.OptionItemBinding;

import java.util.Random;

public class OptionAdapter extends BaseBindingAdapter<Option, OptionItemBinding>
{
    public OptionAdapter(Context context)
    {
        super(context);

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

    public String computeResult(){
        int count = getItemCount();
        Random r = new Random();
        int i = r.nextInt(count-1);
        return items.get(i).getOptionName();
    }
}
