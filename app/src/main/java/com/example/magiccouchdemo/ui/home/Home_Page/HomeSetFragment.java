package com.example.magiccouchdemo.ui.home.Home_Page;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.FragmentHomeSetBinding;
import com.example.magiccouchdemo.ui.dashboard.CancelDialogFragment;
import com.example.magiccouchdemo.ui.dashboard.DashboardViewModel;
import com.example.magiccouchdemo.ui.home.HomeViewModel;
import com.example.magiccouchdemo.ui.home.Option;
import com.example.magiccouchdemo.ui.home.OptionAdapter;

import java.util.ArrayList;


public class HomeSetFragment extends Fragment {

    OptionAdapter adapter;
    HomeViewModel homeViewModel;
    FragmentHomeSetBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home_set, container, false);
        binding.setLifecycleOwner(getActivity());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.addOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Option option = new Option("", 0);
                adapter.addData(adapter.getItemCount(), option);
                binding.recyclerView.scrollToPosition(adapter.getItemCount()-1);
            }
        });
        adapter = new OptionAdapter(getContext());
        homeViewModel.setmList(adapter.getItems());
        binding.recyclerView.setAdapter(adapter);
        binding.nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String result = adapter.computeResult();
                homeViewModel.setResult(result);
                if(homeViewModel.getDecName().getValue()==null){
                    Toast.makeText(v.getContext(), "决策主题不能为空！", Toast.LENGTH_SHORT).show();
                }
                else if(homeViewModel.getmList()==null){
                    Toast.makeText(v.getContext(), "还没有添加任何选项！", Toast.LENGTH_SHORT).show();
                }
                else{
                    NavController controller = Navigation.findNavController(v);
                    //确认提交
                    controller.navigate(R.id.action_HomeSetFragment_to_HomeResultFragment);
                }
            }
        });
        adapter.getItems().add(new Option("张三", 18));
        adapter.getItems().add(new Option("李四", 28));
        adapter.getItems().add(new Option("王五", 38));
        return binding.getRoot();
    }

    @Override
    public void onPause() {

        super.onPause();
    }
}