package com.example.magiccouchdemo.ui.home.Home_Page;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.FragmentHomeSetBinding;
import com.example.magiccouchdemo.ui.home.HomeViewModel;
import com.example.magiccouchdemo.ui.home.Option;
import com.example.magiccouchdemo.ui.home.OptionAdapter;


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
        ObservableArrayList<Option> options = homeViewModel.getmList();
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
        binding.setModel(homeViewModel);
        binding.recyclerView.setAdapter(adapter);
        binding.nextButton.bringToFront();
        binding.addOption.bringToFront();
        binding.nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(binding.decTitle.getText())){
                    Toast.makeText(v.getContext(), "决策主题不能为空！", Toast.LENGTH_SHORT).show();
                }
                else if(adapter.getItems().size()==0){
                    Toast.makeText(v.getContext(), "还没有添加任何选项！", Toast.LENGTH_SHORT).show();
                }
                else{
                    String result = adapter.computeResult(1);
                    homeViewModel.getDecName();
                    homeViewModel.setmList(adapter.getItems());
                    homeViewModel.setResult(result);
                    NavController controller = Navigation.findNavController(v);
                    //确认提交
                    controller.navigate(R.id.action_HomeSetFragment_to_HomeResultFragment);
                }
            }
        });
        adapter.getItems().add(new Option("张三", 5));
        adapter.getItems().add(new Option("李四", 5));
        adapter.getItems().add(new Option("王五", 5));
        return binding.getRoot();
    }

    @Override
    public void onPause() {

        super.onPause();
    }
}