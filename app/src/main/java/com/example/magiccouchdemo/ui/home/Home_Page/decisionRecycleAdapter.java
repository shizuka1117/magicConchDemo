package com.example.magiccouchdemo.ui.home.Home_Page;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
//import com.example.magiccouchdemo.databinding.EventViewBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class decisionRecycleAdapter extends RecyclerView.Adapter<decisionRecycleAdapter.ViewHolder> {

    private List<decisionList> dataList = new ArrayList<>();
    //EventViewBinding binding;
    private boolean isShowCheckBox = false;//表示当前是否是多选状态。
    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();//用来存放CheckBox的选中状态，true为选中,false为没有选中


    public decisionRecycleAdapter(List<decisionList> dataList){
        this.dataList=dataList;
        Log.d("execute","111");
    }

    public interface  onItemClickListener{
        void onItemClick(CardView view, int position);
        void onItemLongClick(CardView view, int position);
    }
    //定义了当长按视图中的项目时调用的回调函数的接口。
    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }


    //内部ViewHolder类
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView event_name;
        CheckBox checkBox;
        ImageView img_id;
        TextView event_tag;

        randomColor rand = new randomColor();//随机颜色类
        Random random = new Random();//随机类
        List<Integer> color_list = rand.getColor();//返回一个color list
        Integer r = random.nextInt(rand.getColor().size());//获取随机下标
        Integer ranColor = color_list.get(r);


        @SuppressLint("ResourceAsColor")
        public ViewHolder(View itemView) {
            super(itemView);

            //setBackgroundcolor 无效？但是不能直接setBackgroundcolor(int color)
            cardView = (CardView) itemView;
            cardView.setCardBackgroundColor(Color.parseColor("#"+ranColor.toString()));

            event_name = (TextView) itemView.findViewById(R.id.event_name);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
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

/**
 * holder.event_name.setOnClickListener(new View.OnClickListener() {
 *             @Override
 *             public void onClick(View v) {
 *                 int position = holder.getAdapterPosition()+1;
 *                 decisionList decision = dataList.get(position);
 *                 Toast.makeText(v.getContext(), "you clicked Event :" + decision.getName(), Toast.LENGTH_SHORT).show();
 *             }
 *         });
 *         Log.d("View Count", "11111111");
 */

        return holder;
    }

    /**
     * 把数据交给对应的VH来显示。
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(onItemClickListener!=null){
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPos=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.cardView,layoutPos);

                }
            });
            holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPos=holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.cardView,layoutPos);
                    return false;
                }
            });
        }
        //判断是否选中
        boolean itemChecked = isItemChecked(position);

        holder.checkBox.setChecked(itemChecked);

        //checkBox的监听
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(position)) {
                    setItemChecked(position, false);
                } else {
                    setItemChecked(position, true);
                }
            }
        });

        //条目view的监听
         holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(position)) {
                    setItemChecked(position, false);
                } else {
                    setItemChecked(position, true);
                }
                notifyItemChanged(position);
            }
        });

        //decisionList list = dataList.get(position);
        holder.event_name.setText(dataList.get(position).getName());
        holder.img_id.setImageResource(dataList.get(position).getImgID());
        holder.event_tag.setText(dataList.get(position).getTag());
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //获得选中条目的结果
    public ArrayList<decisionList> getSelectedItem() {
        ArrayList<decisionList> selectList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            if (isItemChecked(i)) {
                selectList.add(dataList.get(i));
            }
        }
        return selectList;
    }

    //设置给定位置条目的选择状态
    void setItemChecked(int position, boolean isChecked) {
        if (mSelectedPositions == null) {
            mSelectedPositions = new SparseBooleanArray();
        }
        mSelectedPositions.put(position, isChecked);
    }

    //根据位置判断条目是否选中
    private boolean isItemChecked(int position) {
        if (mSelectedPositions != null) {
            return mSelectedPositions.get(position);
        }
        return false;
    }

    void setShowCheckBox(boolean check){
        isShowCheckBox = check;
    }

}