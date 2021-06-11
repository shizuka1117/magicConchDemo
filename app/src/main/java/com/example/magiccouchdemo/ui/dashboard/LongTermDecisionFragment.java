package com.example.magiccouchdemo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.Theme;
import com.example.magiccouchdemo.dataBase.ThemeViewModel;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LongTermDecisionFragment extends Fragment {
    //for database

    ThemeViewModel themeViewModel;

    //
    private RecyclerView Rv;
    private ArrayList<decisionList> lists;
    private LongTermDecisionAdapter myAdapter;
    //private SwipeRefreshLayout swipe_refresh;
    private FloatingActionButton floatingActionButton;

    public LongTermDecisionFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        View view = inflater.inflate(R.layout.recycle_view_list2, container, false);

        Rv = (RecyclerView)view.findViewById(R.id.recycle_view2);
        //swipe_refresh = (SwipeRefreshLayout)view.findViewById(R.id.refresh_long);
        floatingActionButton =(FloatingActionButton)view.findViewById(R.id.add_option);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_longTermFragment_to_longTermDecSetFragment);
            }
        });

        // 初始化显示的数据
        //initData();

        // 绑定数据到RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);
        myAdapter = new LongTermDecisionAdapter();
        Rv.setAdapter(myAdapter);

        Theme item1 = new Theme(null,"这个学期要读完的15本书","long");
        Theme item2 = new Theme(null,"今年要去的十个旅行地","long");
        Theme item3 = new Theme(null,"和ta在一起要做的事","long");
        Theme item4 = new Theme(null,"给自己的奖励吃吃吃","long");
        Theme item5 = new Theme(null,"给自己的奖励吃吃吃111","long");


        //database交互
        themeViewModel = ViewModelProviders.of(this.getActivity()).get(ThemeViewModel.class);
        themeViewModel.deleteAllThemes();
        themeViewModel.insertThemes(item1,item2,item3,item4,item5);

        /*swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {  //列表下拉刷新
            @Override
            public void onRefresh() {
                myAdapter.setDataList(themes);
            }
        });*/
        themeViewModel.getAllLongTermThemeLive().observe(this.getViewLifecycleOwner(),new Observer<List<Theme>>(){
            @Override
            public void onChanged(List<Theme> themes) {
                myAdapter.setDataList(themes);
                myAdapter.notifyDataSetChanged();
            }
        });


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
