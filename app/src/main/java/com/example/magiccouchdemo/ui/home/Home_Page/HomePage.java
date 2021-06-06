package com.example.magiccouchdemo.ui.home.Home_Page;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.RecycleViewList1Binding;

import java.util.ArrayList;
import java.util.List;


public class HomePage extends Fragment {
    RecycleViewList1Binding binding;
    private List<decisionList> DecisionList = new ArrayList<>();

    private decisionRecycleAdapter adapter = new decisionRecycleAdapter(DecisionList);
    private LinearLayout EditBar;//控制下方那一行的显示与隐藏

    public HomePage() {
        // Required empty public constructor
    }

    public void initDecisionList() {
        for (int i = 0; i < 6; i++) {
            decisionList evt1 = new decisionList("今晚吃啥？", R.drawable.ic_baseline_label, "吃吃吃");
            DecisionList.add(evt1);
            Log.d("color", evt1.getImgID() + "");
        }
        for (int i = 0; i < 5; i++) {
            decisionList evt2 = new decisionList("聚餐去哪里？", R.drawable.ic_baseline_label, "乐乐乐");
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
            getActivity().findViewById(R.id.nav_view).setVisibility(View.INVISIBLE);
            controller.navigate(R.id.action_homeFragment_to_homeSetFragment);
        });

        //初始化decision_list
        initDecisionList();

        binding.recycleView1.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.recycleView1.setLayoutManager(layoutManager);

        setOnListViewClickListener();//监听点击事件


        return binding.getRoot();
    }


    private void setOnListViewClickListener() {

        adapter.setOnItemClickListener(new decisionRecycleAdapter.onItemClickListener() {
            //监听点击（短）事件
            @Override
            public void onItemClick(CardView view, int position) {
                Toast.makeText(getActivity(), "onClick" + position, Toast.LENGTH_SHORT).show();
                //跳转到对应的编辑界面！
            }

            //监听长按,长按选择删除
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemLongClick(CardView view, int position) {
                Toast.makeText(getActivity(), "onLongClick" + position, Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.window_menu, popupMenu.getMenu());
                //设置PopupMenu在指定位置弹出
                popupMenu.setGravity(Gravity.END);

                //弹出式菜单的菜单项点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                //跳转到编辑界面
                                Toast.makeText(getActivity(), "edit" + position, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete:
                                //数据库中的删除，调用delete
                                delete(position);
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }

        });

    }


    //menu获取
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tag_menu:
                Log.d("RecycleViewList1", "点击了标签筛选");
                break;
            case R.id.search:
                Log.d("RecycleViewList1", "点击了搜索");
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


    private void delete(int position) {
        if (DecisionList.size() == 0) {
            Toast.makeText(getActivity(), "您还没有选中任何数据！", Toast.LENGTH_SHORT).show();
            return;
        }
        showInformation("确认删除吗？",position);
    }

    /**
     * 确认删除
     */
    private void beSureDelete(int position) {
        DecisionList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyDataSetChanged();
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
                Toast.makeText(getActivity(), "你点击确定了", Toast.LENGTH_SHORT).show();
                //确认删除，调用delete
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