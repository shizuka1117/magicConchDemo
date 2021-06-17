package com.example.magiccouchdemo.ui.dashboard;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.Theme;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionList;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionRecycleAdapter;
import com.example.magiccouchdemo.ui.home.Home_Page.randomColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LongTermDecisionAdapter extends RecyclerView.Adapter<LongTermDecisionAdapter.ViewHolder> {

    private List<Theme> dataList = new ArrayList<>();
    //EventViewBinding binding;

    //for binding database
    public void setDataList(List<Theme> dataList) {
        this.dataList = dataList;
    }
    public List<Theme> getDataList(){ return dataList; }

    /*public LongTermDecisionAdapter(List<decisionList> dataList){
        this.dataList=dataList;
        Log.d("execute","111");
    }*/

    public interface  onItemClickListener{
        void onItemLongClick(CardView view, int position);
    }
    //定义了当长按视图中的项目时调用的回调函数的接口。
    private LongTermDecisionAdapter.onItemClickListener onItemClickListener;

    public void setOnItemClickListener(LongTermDecisionAdapter.onItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    //内部ViewHolder类
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView listView;
        TextView event_name;

        randomColor rand = new randomColor();//随机颜色类

        Random random = new Random();//随机类
        List<String> color_list = rand.getColor();//返回一个color list

        Integer r = random.nextInt(rand.getColor().size());//获取随机下标
        String ranColor = color_list.get(r);

        @SuppressLint("ResourceAsColor")
        public ViewHolder(View itemView) {
            super(itemView);

            listView = (CardView) itemView;
            listView.setCardBackgroundColor(Color.parseColor(ranColor));

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
                Bundle bundle = new Bundle();
                int pos = holder.getLayoutPosition();
                int ParentId = dataList.get(pos).getT_id();
                bundle.putInt("ParentId",ParentId);

                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_longTermFragment_to_boxFragment,bundle);
            }
        });
        if(onItemClickListener!=null){
            holder.listView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPos=holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.listView,layoutPos);
                    return true;
                }
            });
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LongTermDecisionAdapter.ViewHolder holder, int position) {
        holder.event_name.setText(dataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
