package com.example.magiccouchdemo.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
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
import com.example.magiccouchdemo.ui.home.Home_Page.decisionRecycleAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LongTermDecisionFragment extends Fragment {
    //for database

    ThemeViewModel themeViewModel;

    //
    private RecyclerView Rv;
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

        // 绑定数据到RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);
        myAdapter = new LongTermDecisionAdapter();
        Rv.setAdapter(myAdapter);

        //生成测试数据
        //Theme item1 = new Theme(null,"这个学期要读完的15本书","long");
        //Theme item2 = new Theme(null,"今年要去的十个旅行地","long");
        //Theme item3 = new Theme(null,"和ta在一起要做的事","long");
        //Theme item4 = new Theme(null,"给自己的奖励吃吃吃","long");
        //Theme item5 = new Theme(null,"给自己的奖励吃吃吃111","long");
        Theme item0 = new Theme(null,"在这里记录你想在一段时间内做的事","long");


        //database交互
        themeViewModel = ViewModelProviders.of(this.getActivity()).get(ThemeViewModel.class);
        //themeViewModel.deleteAllThemes();
        //themeViewModel.insertThemes(item1,item2,item3,item4,item5);

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
                if (myAdapter.getItemCount()==0){
                    themeViewModel.insertThemes(item0);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        setOnListViewClickListener();//监听点击事件

        return view;

    }

    private void setOnListViewClickListener() {
        myAdapter.setOnItemClickListener(new LongTermDecisionAdapter.onItemClickListener() {
            //监听长按,长按选择编辑/删除
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemLongClick(CardView view, int position) {
                //Toast.makeText(getActivity(), "onLongClick" + position, Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.window_menu, popupMenu.getMenu());
                //设置PopupMenu在指定位置弹出
                popupMenu.setGravity(Gravity.END);

                //弹出式菜单的菜单项点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:
                                //数据库中的删除，调用delete
                                delete(position);
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }

        });

    }

    private void delete(int position) {
        if(themeViewModel.getAllLongTermThemeLive()==null){
            Toast.makeText(getActivity(), "您还没有选中任何数据！", Toast.LENGTH_SHORT).show();
            return;
        }
        showInformation("确认删除吗？",position);
    }

    /**
     * 确认删除
     */
    private void beSureDelete(int position) {
        Theme theme = myAdapter.getDataList().get(position);
        themeViewModel.deleteThemes(theme);
        myAdapter.getDataList().remove(theme);
        myAdapter.notifyItemRemoved(position);
        myAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
    }

    private void showInformation(String msg, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setTitle("提示");

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // 执行操作
                //Toast.makeText(getActivity(), "你点击确定了", Toast.LENGTH_SHORT).show();
                //确认删除，调用buSureDelete
                beSureDelete(position);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
