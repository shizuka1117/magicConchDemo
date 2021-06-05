package com.example.magiccouchdemo.ui.home.Home_Page;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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

   // private List<String> mData = new ArrayList<>();//所有数据
    private List<String> mCheckedData = new ArrayList<>();//将选中数据放入里面
    private SparseBooleanArray stateCheckedMap = new SparseBooleanArray();//用来存放CheckBox的选中状态，true为选中,false为没有选中
    private boolean isSelectedAll = true;//用来控制点击全选，全选和全不选相互切换

    public HomePage() {
        // Required empty public constructor
    }

    public void initDecisionList(){
        for(int i=0;i<6;i++) {
            decisionList evt1 = new decisionList("今晚吃啥？",R.drawable.ic_baseline_label,"吃吃吃");
            DecisionList.add(evt1);
            Log.d("color", evt1.getImgID()+"");
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
            getActivity().findViewById(R.id.nav_view).setVisibility(View.INVISIBLE);
            controller.navigate(R.id.action_homeFragment_to_homeSetFragment);
        });

        initView();
        //初始化decision_list
        initDecisionList();

        binding.recycleView1.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        binding.recycleView1.setLayoutManager(layoutManager);

        setOnListViewClickListener();

        /**
         * 对底层的editbar添加监听事件
         */

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                Log.d("msg","You canceled");

            }
        });

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        binding.selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAll();
            }
        });
        return binding.getRoot();
    }



    private void initView() {
       // recyclerView.setChoiceMode(RecyclerView.CHOICE_MODE_MULTIPLE);
        EditBar = binding.editBar;
        //绑定监听事件
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                Log.d("msg","You canceled");
            }
        });
    }

    /**
     * 长按
     */
    private void setOnListViewClickListener() {
        /**
         * 监听长按事件
         */
        adapter.setOnItemClickListener(new decisionRecycleAdapter.onItemClickListener() {
            @Override
            public void onItemClick(CardView view, int position) {
                Toast.makeText(getActivity(),"onClick"+position,Toast.LENGTH_SHORT).show();
                //跳转到对应的编辑界面！
            }

            @Override
            public void onItemLongClick(CardView view, int position) {
                getActivity().findViewById(R.id.nav_view).setVisibility(View.INVISIBLE);
                EditBar.setVisibility(View.VISIBLE);
                Log.d("msg","choose to delete");
                adapter.setShowCheckBox(true);//CheckBox的那个方框显示
                updateCheckBoxStatus(view, position);
            }

        });

    }


    /**
     * 更新checkbox状态
     */
    private void updateCheckBoxStatus(View view, int position) {
        decisionRecycleAdapter.ViewHolder holder = new decisionRecycleAdapter.ViewHolder(view);
        //decisionRecycleAdapter.ViewHolder holder = (decisionRecycleAdapter.ViewHolder) view.getTag();
        holder.checkBox.toggle();//反转CheckBox的选中状态
        holder.checkBox.setVisibility(View.VISIBLE);
        adapter.setItemChecked(position, holder.checkBox.isChecked());//长按ListView时选中按的那一项
        stateCheckedMap.put(position, holder.checkBox.isChecked());//存放CheckBox的选中状态

        /**
         * if (holder.checkBox.isChecked()) {
         *             mCheckedData.add(mData.get(position));//CheckBox选中时，把这一项的数据加到选中数据列表
         *         } else {
         *             mCheckedData.remove(mData.get(position));//CheckBox未选中时，把这一项的数据从选中数据列表移除
         *         }
         *         adapter.notifyDataSetChanged();
         *
         *         mData应该对应的是decisionList
          */
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
            /**
             * case R.id.del:
             *                 //删除操作
             *                 Log.d("RecycleViewList1","点击了删除");
             *                 //显示删除框
             *                 //监听删除操作
             *                 break;
             */
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


    private void cancel() {
        setStateCheckedMap(false);//将CheckBox的所有选中状态变成未选中
        EditBar.setVisibility(View.GONE);//隐藏下方布局
        getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);//显示导航栏
        adapter.setShowCheckBox(false);//让CheckBox那个方框隐藏
        Log.d("msg","You canceled");
        adapter.notifyDataSetChanged();//更新ListView
    }

    private void delete() {
        if (mCheckedData.size() == 0) {
            Toast.makeText(getActivity(), "您还没有选中任何数据！", Toast.LENGTH_SHORT).show();
            return;
        }
        /**
         * 弹窗：确认？
         * final CustomDialog dialog = new CustomDialog(this);
         *         dialog.show();
         *         dialog.setHintText("是否删除？");
         *         dialog.setLeftButton("取消", new View.OnClickListener() {
         *             @Override
         *             public void onClick(View v) {
         *                 dialog.dismiss();
         *             }
         *         });
         *         dialog.setRightButton("确定", new View.OnClickListener() {
         *             @Override
         *             public void onClick(View v) {
         *                 beSureDelete();
         *                 dialog.dismiss();
         *             }
         *         });
         */
       binding.delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               beSureDelete();
              // Toast.makeText(getActivity(),"delete!",Toast.LENGTH_SHORT).show();
           }
       });

    }

    /**
     * 确认删除
     */
    private void beSureDelete() {
        //mData.removeAll(mCheckedData);//删除选中数据
        setStateCheckedMap(false);//将CheckBox的所有选中状态变成未选中
        mCheckedData.clear();//清空选中数据
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 全选
     */
    private void selectAll() {
        mCheckedData.clear();//清空之前选中数据
        if (isSelectedAll) {

            setStateCheckedMap(true);//将CheckBox的所有状态变成选中
            isSelectedAll = false;
         //   mCheckedData.addAll(mData);//把所有的数据添加到选中列表中
        } else {
            setStateCheckedMap(false);//将CheckBox的所有选中状态变成未选中
            isSelectedAll = true;
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 设置所有CheckBox的选中状态
     * */
    private void setStateCheckedMap(boolean isSelectedAll) {

        for (int i = 0; i < DecisionList.size(); i++) {
            stateCheckedMap.put(i, isSelectedAll);
            adapter.setItemChecked(i, isSelectedAll);
        }
    }

}