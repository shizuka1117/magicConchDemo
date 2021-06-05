package com.example.magiccouchdemo.ui.dashboard;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionList;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionRecycleAdapter;
import com.example.magiccouchdemo.ui.home.Home_Page.randomColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LongTermDecisionAdapter extends RecyclerView.Adapter<LongTermDecisionAdapter.ViewHolder> {

    private List<decisionList> dataList = new ArrayList<>();
    //EventViewBinding binding;

    public LongTermDecisionAdapter(List<decisionList> dataList){
        this.dataList=dataList;
        Log.d("execute","111");
    }

    //内部ViewHolder类
    static class ViewHolder extends RecyclerView.ViewHolder {
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

        @SuppressLint("ResourceAsColor")
        public ViewHolder(View itemView) {
            super(itemView);

            //setBackgroundcolor 无效？但是不能直接setBackgroundcolor(int color)
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
    public LongTermDecisionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view2,parent,false);
        LongTermDecisionAdapter.ViewHolder holder = new LongTermDecisionAdapter.ViewHolder(view);
        final LongTermDecisionAdapter.ViewHolder holder1 = new LongTermDecisionAdapter.ViewHolder(view);

        holder.event_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_longTermFragment_to_boxFragment);
            }
        });
        Log.d("View Count", "11111111");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LongTermDecisionAdapter.ViewHolder holder, int position) {
        //decisionList list = dataList.get(position);
        holder.event_name.setText(dataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
