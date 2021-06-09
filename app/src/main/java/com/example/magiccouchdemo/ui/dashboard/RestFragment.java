package com.example.magiccouchdemo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.Option;


import java.util.ArrayList;

public class RestFragment extends Fragment {
    private RecyclerView Rv;
    private ArrayList<Option> restOptions;
    private RestChoiceAdapter myAdapter;

    public RestFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_rest_choice, container, false);

        Rv = (RecyclerView)view.findViewById(R.id.rest_choices_recycler_view);

        // 初始化显示的数据
        initData();

        // 绑定数据到RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);

        //为ListView绑定适配器
        myAdapter = new RestChoiceAdapter(restOptions);
        Rv.setAdapter(myAdapter);

        return view;

    }


    private void initData() {
        restOptions = new ArrayList<Option>();/*在数组中存放数据*/

        Option item1 = new Option("一起去济南吧");
        restOptions.add(item1);

        Option item2 = new Option("苏州gogogo");
        restOptions.add(item2);

        Option item3 = new Option("去大理看云海");
        restOptions.add(item3);

        Option item4 = new Option("西藏西藏！");
        restOptions.add(item4);

    }
}
