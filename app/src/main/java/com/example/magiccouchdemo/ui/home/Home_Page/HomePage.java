package com.example.magiccouchdemo.ui.home.Home_Page;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.RecycleViewList1Binding;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends Fragment {
    RecycleViewList1Binding binding;
    private List<decisionList> DecisionList = new ArrayList<>();

    public HomePage() {
        // Required empty public constructor
    }

    public void initDecisionList(){
        for(int i=0;i<6;i++) {
            decisionList evt1 = new decisionList("今晚吃啥？",R.drawable.ic_baseline_label,"吃吃吃");
            DecisionList.add(evt1);
        }
        for(int i=0;i<5;i++){
            decisionList evt2 = new decisionList("聚餐去哪里？",R.drawable.ic_baseline_label,"乐乐乐");
            DecisionList.add(evt2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.recycle_view_list1, null, false);
        binding.setLifecycleOwner(getActivity());
        //点击加号 跳转
        binding.fab1.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_homeFragment_to_homeSetFragment);
        });


        //初始化decision_list
        initDecisionList();

        decisionRecycleAdapter adapter = new decisionRecycleAdapter(DecisionList);
        binding.recycleView1.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.recycleView1.setLayoutManager(layoutManager);


/*
        // onCreated方法中进行设置
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ReViewTouchCallback(new IActionListener() {
           //getDatalist==>Databinding 数据库 room
            @Override
            public boolean onItemMove(int src, int target) {
                Collections.swap(adapter.getDataList(), src, target);
                adapter.notifyItemMoved(src, target);
                return true;
            }

            @Override
            public void onItemRemove(int pos) {
                adapter.getDataList().remove(pos);
                adapter.notifyItemRemoved(pos);
            }
        }));
        itemTouchHelper.attachToRecyclerView(binding.recycleView1);
*/

        return binding.getRoot();
    }

    //menu获取
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.tag_menu:
                Log.d("RecycleViewList1","点击了标签筛选");

                break;
            case R.id.del:
                //删除操作
                Log.d("RecycleViewList1","点击了删除");
                //监听删除操作
                break;
            case R.id.search:
                Log.d("RecycleViewList1","点击了搜索");
                //监听搜索事件
            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

}