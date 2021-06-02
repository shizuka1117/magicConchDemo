package com.example.magiccouchdemo.ui.home.Home_Page;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
//import com.example.magiccouchdemo.databinding.EventViewBinding;

import java.util.ArrayList;
import java.util.List;

public class decisionRecycleAdapter extends RecyclerView.Adapter<decisionRecycleAdapter.ViewHolder> {

    private List<decisionList> dataList = new ArrayList<>();
    //EventViewBinding binding;

    public decisionRecycleAdapter(List<decisionList> dataList){
        this.dataList=dataList;
        Log.d("execute","111");
    }

    //内部ViewHolder类
    static class ViewHolder extends RecyclerView.ViewHolder {
        View listView;
        TextView event_name;
        ImageView img_id;
        TextView event_tag;

        /**
         * 设置CardView随机颜色,试了一下，好丑....再改
         */

        /**
        Random myRandom = new Random();
        int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
         */
        public ViewHolder(View itemView) {
            super(itemView);
            listView = itemView;
            //listView.setBackgroundColor(ranColor);
            event_name = (TextView) itemView.findViewById(R.id.event_name);
            img_id = (ImageView)itemView.findViewById(R.id.tag_img);
            event_tag = (TextView) itemView.findViewById(R.id.event_tag);
        }
    }

    @NonNull
    @Override
    /**
     * 这里就是创建ViewHolder对象并返回。
     * 而VH构造器要求传入一个View，我们利用LayoutInflater创建一个view给它。
     * 当然，创建的根据就是event_view.xml
     */
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view,parent,false);
        ViewHolder holder = new ViewHolder(view);
        final ViewHolder holder1 = new ViewHolder(view);
/*
        holder1.listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder1.getAdapterPosition()+1;//index>=0
                decisionList decision = dataList.get(position);
                Toast.makeText(v.getContext(), "you clicked view " +  decision.getName(),Toast.LENGTH_SHORT).show();
            }
        });
*/
        holder.event_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder1.getAdapterPosition()+1;
                decisionList decision = dataList.get(position);
                Toast.makeText(v.getContext(), "you clicked Event :" + decision.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("View Count", "11111111");
        return holder;
    }

    /**
     * 把数据交给对应的VH来显示。
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //decisionList list = dataList.get(position);
        holder.event_name.setText(dataList.get(position).getName());
        holder.img_id.setImageResource(dataList.get(position).getImgID());
        holder.event_tag.setText(dataList.get(position).getTag());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}