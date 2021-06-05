package com.example.magiccouchdemo.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.FragmentDashboardBinding;
import com.example.magiccouchdemo.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding binding;
    private List<ComDec> ComDecList = new ArrayList<>();

    //弹出菜单
    private PopupWindow popupWindow; // 左侧弹出菜单
    private PopupWindow popupCover; // 菜单蒙版

    private View home_page;
    private ViewGroup customView;
    private ViewGroup coverView;
    private LayoutInflater layoutInflater;//渲染布局
    //private WindowManager wm;
    private DisplayMetrics metrics;
    //private Display display;
    //private Point size;

    //初始化窗口
    public void initPopupWindow() {
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //渲染
        customView = (ViewGroup) layoutInflater.inflate(R.layout.edit_tag, null);
        coverView = (ViewGroup) layoutInflater.inflate(R.layout.cover_view, null);
        home_page = getActivity().findViewById(R.id.homeFragment);
        metrics = new DisplayMetrics();
        //display = getActivity().getWindowManager().getDefaultDisplay();
        //size = new Point();
        //display.getSize(size);
        //wm = getActivity().getWindowManager();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);//对屏幕宽高进行获取
    }

    //显示窗口
    public void showPopupWindow() {
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        customView = (ViewGroup) layoutInflater.inflate(R.layout.edit_tag, null);
        coverView = (ViewGroup) layoutInflater.inflate(R.layout.cover_view, null);
        home_page = getActivity().findViewById(R.id.homeFragment);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;//获取宽高
       // int width = size.x;
        //int height = size.y;

        popupCover = new PopupWindow(coverView, width, height, false);
        popupWindow = new PopupWindow(customView, (int) (width * 0.7), (int) (height), true);//实例化popupwindow
        popupWindow.setBackgroundDrawable(new ColorDrawable((Color.WHITE)));//popwindow的背景

        //popupWindow.setAnimationStyle(R.style.AnimationFade);
        //popupCover.setAnimationStyle(R.style.AnimationCover);
        //在主界面加载成功后显示弹出
        getActivity().findViewById(R.id.homeFragment).post(new Runnable() {
            @Override
            public void run() {
                if(home_page!=null){
                    popupCover.showAtLocation(home_page, Gravity.NO_GRAVITY, 0, 0);
                    popupWindow.showAtLocation(home_page, Gravity.NO_GRAVITY, 0, 0);

                    coverView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            popupWindow.dismiss();
                            return true;
                        }
                    });
                    //弹窗消失的时候，触发让蒙版消失
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            popupCover.dismiss();
                        }
                    });
                }
            }
        });
    }

    //menu获取
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu,menu);
    }

    private void initComDec() {
        for(int i=0;i<10;i++) {
            ComDec evt1 = new ComDec("What's to eat tonight", "Eating");
            ComDecList.add(evt1);
        }
        ComDec evt2 = new ComDec("What's to buy", "Shopping");
        ComDecList.add(evt2);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, null, false);
        //绑定fragment_home.xml
        binding.setLifecycleOwner(getActivity());
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_homeFragment_to_homeSetFragment);
            }
        });

        //ListView
        initComDec();
        initPopupWindow();
        ComDecAdapter adapter = new ComDecAdapter(getActivity(),
                R.layout.com_dec_data, ComDecList);
        binding.list1.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.tag_menu:
                showPopupWindow();
                Log.d("HomeFragment","点击了标签设置");
                break;
            case R.id.del:
                //删除操作
                Log.d("HomeFragment","点击了删除");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取menu resource
        setHasOptionsMenu(true);
    }
}