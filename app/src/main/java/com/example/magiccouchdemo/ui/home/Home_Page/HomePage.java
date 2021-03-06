package com.example.magiccouchdemo.ui.home.Home_Page;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.OptionViewModel;
import com.example.magiccouchdemo.dataBase.Theme;
import com.example.magiccouchdemo.dataBase.ThemeViewModel;
import com.example.magiccouchdemo.databinding.RecycleViewList1Binding;
import com.example.magiccouchdemo.ui.home.HomeViewModel;

import java.util.List;


public class HomePage extends Fragment {
    RecycleViewList1Binding binding;
    private RecyclerView Rv;
    //for database
    ThemeViewModel themeViewModel;
    HomeViewModel homeViewModel;
    OptionViewModel optionViewModel;
    private LiveData<List<Theme>> filteredTheme;//过滤后的

    private decisionRecycleAdapter adapter = new decisionRecycleAdapter();

    public HomePage() {
        // Required empty public constructor
    }

    public void init() {
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        themeViewModel = ViewModelProviders.of(this.getActivity()).get(ThemeViewModel.class);
        optionViewModel = ViewModelProviders.of(this.getActivity()).get(OptionViewModel.class);
        //themeViewModel.deleteAllThemes();

        /**
         * 初始化
         */
        /*
        Theme it1 = new Theme("吃吃吃", "今晚吃啥？", "short");
        Theme it2 = new Theme("乐乐乐", "聚餐去哪里？", "short");
        Theme it3 = new Theme("学习","先复习哪门科目？","short");
        Theme it4 = new Theme(null,"今天要不要喝奶茶？","short");
        Theme it5 = new Theme("乐乐乐","周末去哪里玩？","short");
        Theme it6 = new Theme("购物","买什么牌子的手机？","short");

        themeViewModel.insertThemes(it1, it2, it3, it4, it5,it6);
        */
        //样例提示主题
        Theme it0 = new Theme("这里是标签","在这里记录你经常用的决策","short");
        themeViewModel.getAllShortTermThemeLive().observe(this.getViewLifecycleOwner(), new Observer<List<Theme>>() {
            @Override
            public void onChanged(List<Theme> themes) {
                adapter.setDataList(themes);
                if (adapter.getItemCount()==0){
                    themeViewModel.insertThemes(it0);
                }
                adapter.notifyDataSetChanged();
            }
        });
        themeViewModel.getMaxShortThemeID().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                homeViewModel.setMaxThemeID(integer);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        binding = DataBindingUtil.inflate(inflater, R.layout.recycle_view_list1, null, false);
        binding.setLifecycleOwner(getActivity());
        //点击加号 跳转
        binding.fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9AC0FD")));
        binding.fab1.setOnClickListener(v -> {
            homeViewModel.clear();
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_homeFragment_to_homeSetFragment);
        });

        Rv = binding.recycleView1;

        //初始化
        init();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        Rv.setLayoutManager(layoutManager);
       // Rv.setHasFixedSize(true);
        Rv.setAdapter(adapter);

        setOnListViewClickListener();//监听点击事件

        return binding.getRoot();
    }


    private void setOnListViewClickListener() {
        adapter.setOnItemClickListener(new decisionRecycleAdapter.onItemClickListener() {
            //监听点击（短）事件
            @Override
            public void onItemClick(CardView view, int position) {
                homeViewModel.clear();
                Theme theme = adapter.getDataList().get(position);
                homeViewModel.setDecName(theme.getName());
                homeViewModel.setTag(theme.getTag());
                homeViewModel.setId(theme.getT_id());
                Log.d("themeID", theme.getT_id()+"");
                NavController controller = Navigation.findNavController(getView());
                controller.navigate(R.id.action_homeFragment_to_homeSetFragment);
                //跳转到对应的编辑界面
            }

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
    //Search 模糊查询
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setMaxWidth(600);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                /**
                 * 模糊匹配
                 */
                String pattern = newText.trim();
                filteredTheme = themeViewModel.searchTheme(pattern);
                filteredTheme.observe(getActivity(), new Observer<List<Theme>>() {
                    @Override
                    public void onChanged(List<Theme> themes) {
                        int temp = adapter.getItemCount();
                        adapter.setDataList(themes);
                        if(temp!=themes.size()){
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                return true;
            }
        });



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void delete(int position) {
        if(themeViewModel.getAllShortTermThemeLive()==null){
            Toast.makeText(getActivity(), "您还没有选中任何数据！", Toast.LENGTH_SHORT).show();
            return;
        }
        showInformation("确认删除吗？",position);
    }

    /**
     * 确认删除
     */
    private void beSureDelete(int position) {
        Theme theme = adapter.getDataList().get(position);
        themeViewModel.deleteThemes(theme);
        adapter.getDataList().remove(theme);
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