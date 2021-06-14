package com.example.magiccouchdemo.ui.home.Home_Page;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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
import com.example.magiccouchdemo.dataBase.OptionViewModel;
import com.example.magiccouchdemo.dataBase.Theme;
import com.example.magiccouchdemo.databinding.FragmentHomeSetBinding;
import com.example.magiccouchdemo.ui.home.HomeViewModel;
import com.example.magiccouchdemo.dataBase.Option;
import com.example.magiccouchdemo.ui.home.OptionAdapter;

import java.util.List;


public class HomeSetFragment extends Fragment {

    OptionAdapter adapter;
    HomeViewModel homeViewModel;
    OptionViewModel optionViewModel;
    FragmentHomeSetBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        optionViewModel = new ViewModelProvider(getActivity()).get(OptionViewModel.class);
        getActivity().findViewById(R.id.nav_view).setVisibility(View.INVISIBLE);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home_set, container, false);
        binding.setLifecycleOwner(getActivity());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new OptionAdapter(getContext(), optionViewModel);

        optionViewModel.getOptionsByParent(homeViewModel.getId()).observe(this.getViewLifecycleOwner(), new Observer<List<Option>>() {
            @Override
            public void onChanged(List<Option> options) {
                for(int i= 0; i<options.size(); i++)
                    options.get(i).setParentId(homeViewModel.getId());
                adapter.getItems().clear();
                adapter.getItems().addAll(options);
                adapter.notifyDataSetChanged();
            }
        });

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
                else if(adapter.getItems().size()<=1){
                    Toast.makeText(v.getContext(), "选项数量不足！", Toast.LENGTH_SHORT).show();
                }
                else{
                    homeViewModel.setmList(adapter.getItems());
                    String result = adapter.computeResult(1);
                    homeViewModel.setResult(result);
                    NavController controller = Navigation.findNavController(v);
                    //确认提交
                    controller.navigate(R.id.action_HomeSetFragment_to_HomeResultFragment);
                }
            }
        });
        binding.addOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Option option = new Option(homeViewModel.getId());
                adapter.addData(adapter.getItemCount(), option);
                binding.recyclerView.scrollToPosition(adapter.getItemCount()-1);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onPause() {

        super.onPause();
    }
}