package com.example.magiccouchdemo.ui.dashboard;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionList;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionRecycleAdapter;
import com.example.magiccouchdemo.ui.home.Home_Page.randomColor;
import com.example.magiccouchdemo.ui.home.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RestChoiceAdapter extends RecyclerView.Adapter<RestChoiceAdapter.ViewHolder> {
    private ArrayList<Option> restOptions = new ArrayList<>();
    //构造函数，传入数据
    public RestChoiceAdapter(ArrayList<Option> restOptions){
        this.restOptions = restOptions;
    }

    //定义ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder  {
        CardView listView;
        TextView event_name;

        /**
         * 设置CardView随机颜色,试了一下，好丑....再改
         */

        randomColor rand = new randomColor();//随机颜色类

        Random random = new Random();//随机类
        List<Integer> color_list = rand.getColor();//返回一个color list

        Integer r = random.nextInt(rand.getColor().size());//获取随机下标
        Integer ranColor = color_list.get(r);


        public ViewHolder(View root) {
            super(root);
            listView = (CardView) itemView;
            listView.setCardBackgroundColor(Color.parseColor("#"+ranColor.toString()));
            event_name = (TextView) itemView.findViewById(R.id.event_name1);
        }

    }

    @NonNull
    @Override
    /**
     * 这里就是创建ViewHolder对象并返回。
     * 而VH构造器要求传入一个View，我们利用LayoutInflater创建一个view给它。
     * 当然，创建的根据就是event_view.xml
     */
    public RestChoiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view2,parent,false);
        RestChoiceAdapter.ViewHolder holder = new RestChoiceAdapter.ViewHolder(view);

        return holder;
    }

    /**
     * 把数据交给对应的VH来显示。
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RestChoiceAdapter.ViewHolder holder, int position) {
        holder.event_name.setText(restOptions.get(position).getOptionName());
    }

    @Override
    public int getItemCount() {
        return restOptions.size();
    }


}
