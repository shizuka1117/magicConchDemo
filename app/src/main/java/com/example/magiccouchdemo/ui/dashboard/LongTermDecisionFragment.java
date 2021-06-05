package com.example.magiccouchdemo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionList;
import com.example.magiccouchdemo.ui.home.Option;

import java.util.ArrayList;

public class LongTermDecisionFragment extends Fragment {
    private RecyclerView Rv;
    private ArrayList<decisionList> lists;
    private LongTermDecisionAdapter myAdapter;

    public LongTermDecisionFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.recycle_view_list2, container, false);

        Rv = (RecyclerView)view.findViewById(R.id.recycle_view2);

        // 初始化显示的数据
        initData();

        // 绑定数据到RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);

        //为ListView绑定适配器
        myAdapter = new LongTermDecisionAdapter(lists);
        Rv.setAdapter(myAdapter);

        return view;

    }


    private void initData() {
        lists = new ArrayList<decisionList>();/*在数组中存放数据*/

        decisionList item1 = new decisionList("这个学期要读完的15本书");
        lists.add(item1);

        decisionList item2 = new decisionList("今年要去的十个旅行地");
        lists.add(item2);

        decisionList item3 = new decisionList("和ta在一起要做的事");
        lists.add(item3);

        decisionList item4 = new decisionList("给自己的奖励吃吃吃");
        lists.add(item4);

    }

}
