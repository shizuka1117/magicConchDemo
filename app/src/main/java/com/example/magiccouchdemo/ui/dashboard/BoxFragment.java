package com.example.magiccouchdemo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionList;
import com.example.magiccouchdemo.ui.home.Option;

import java.util.ArrayList;
import java.util.Calendar;

public class BoxFragment extends Fragment {
    private RecyclerView Rv;
    private ArrayList<Option> listItem;
    private BoxHistoryAdapeter myAdapter;
    private ImageButton imageButton;
    private Button button;

    public BoxFragment(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_box, container, false);

        Rv = (RecyclerView)view.findViewById(R.id.time_recycler_view);

        // 初始化显示的数据
        initData();

        // 绑定数据到RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);


        //为ListView绑定适配器
        myAdapter = new BoxHistoryAdapeter(listItem);
        Rv.setAdapter(myAdapter);

        imageButton = (ImageButton) view.findViewById(R.id.rest_choices);
        button = (Button) view.findViewById(R.id.ask_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;


    }
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            //获取参数
            //Bundle arguments = getArguments();
            //改变值
            //mTextView.setText(arguments.getString("tag"));
        }
        // 初始化显示的数据
    public void initData(){
        listItem = new ArrayList<Option>();/*在数组中存放数据*/

        Option item1 = new Option("一起去济南吧", Calendar.getInstance());
        listItem.add(item1);

        Option item2 = new Option("苏州gogogo", Calendar.getInstance());
        listItem.add(item2);

        Option item3 = new Option("去大理看云海", Calendar.getInstance());
        listItem.add(item3);

        Option item4 = new Option("西藏西藏！", Calendar.getInstance());
        listItem.add(item4);
    }
}
