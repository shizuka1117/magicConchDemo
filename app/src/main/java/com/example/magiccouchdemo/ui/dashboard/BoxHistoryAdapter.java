package com.example.magiccouchdemo.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.Option;
import com.example.magiccouchdemo.dataBase.Theme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class BoxHistoryAdapter extends RecyclerView.Adapter<BoxHistoryAdapter.ViewHolder>{
    private List<Option> decisionLists = new ArrayList<>();

    //构造函数，传入数据
    public void SetBoxHistoryList(List<Option> decisionLists){
        this.decisionLists = decisionLists;
    }

    public List<Option> getDataList(){ return decisionLists; }

    //定义ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder  {
        TextView day,year,name;
        CardView cardView;

        public ViewHolder(View root) {
            super(root);
            cardView = (CardView)  root;
            day = (TextView) root.findViewById((R.id.day));
            year = (TextView) root.findViewById(R.id.year);
            name = (TextView) root.findViewById(R.id.history_text);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Option listItem = decisionLists.get(position);
        Calendar temp = null;
        try{
           temp = listItem.getDate();
        }catch (ParseException e){
            e.printStackTrace();
        }
        int month = temp.get(Calendar.MONTH)+1;
        int day = temp.get(Calendar.DAY_OF_MONTH);
        int year = temp.get(Calendar.YEAR);
        String tempDay = month+"-"+day;
        holder.day.setText(tempDay);
        holder.year.setText(String.valueOf(year));
        holder.name.setText(listItem.getOptionName());
    }

    @Override
    public int getItemCount() {
        return decisionLists.size();
    }
}
