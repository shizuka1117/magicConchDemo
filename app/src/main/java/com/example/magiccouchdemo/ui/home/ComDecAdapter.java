package com.example.magiccouchdemo.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.magiccouchdemo.R;

import java.util.List;

//重现构造函数
public class ComDecAdapter extends ArrayAdapter<ComDec> {

    private int resourceID;
    public ComDecAdapter(Context context, int textViewResourceId, List<ComDec> objects){
        super(context,textViewResourceId,objects);
        resourceID = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ComDec comdec = getItem(position);//获取当前common decision实例
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        TextView EventName = (TextView) view.findViewById(R.id.name);
        TextView tag = (TextView) view.findViewById(R.id.tag);
        EventName.setText(ComDec.getName());//setText 显示文字
        tag.setText(ComDec.getTag());
        //Log.d("tag","msg");
        return view;//将布局返回
    }

}
