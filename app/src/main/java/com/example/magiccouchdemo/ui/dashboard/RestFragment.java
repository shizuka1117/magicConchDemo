package com.example.magiccouchdemo.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.Option;
import com.example.magiccouchdemo.dataBase.OptionViewModel;

import java.util.ArrayList;
import java.util.List;

public class RestFragment extends Fragment {
    private RecyclerView Rv;
    private ArrayList<Option> restOptions;
    private RestChoiceAdapter myAdapter;
    OptionViewModel optionViewModel;

    public RestFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_rest_choice, container, false);

        Rv = (RecyclerView)view.findViewById(R.id.rest_choices_recycler_view);

        // 绑定数据到RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);

        //为ListView绑定适配器
        myAdapter = new RestChoiceAdapter();
        Rv.setAdapter(myAdapter);

        //初始化optionViewModel
        optionViewModel = ViewModelProviders.of(this.getActivity()).get(OptionViewModel.class);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int ParentId = getArguments().getInt("ParentId");
        optionViewModel.loadNewOptionByParent(ParentId).observe(this.getViewLifecycleOwner(),new Observer<List<Option>>(){
            @Override
            public void onChanged(List<Option> options) {
                myAdapter.SetRestOptionsList(options);
                myAdapter.notifyDataSetChanged();
            }
        });
    }

}
