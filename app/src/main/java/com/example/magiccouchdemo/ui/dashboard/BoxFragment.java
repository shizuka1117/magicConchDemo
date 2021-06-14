package com.example.magiccouchdemo.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.Option;
import com.example.magiccouchdemo.dataBase.OptionDao;
import com.example.magiccouchdemo.dataBase.OptionViewModel;
import com.example.magiccouchdemo.dataBase.Theme;


import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class BoxFragment extends Fragment {
    private RecyclerView Rv;
    //private List<Option> randList;
    OptionViewModel optionViewModel;
    OptionDao optionDao;
    private BoxHistoryAdapter myAdapter;
    private ImageButton imageButton;
    private Button button;
    private AlertDialog.Builder builder;
    int ParentId;
    private Answer ans = new Answer();

    public BoxFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_box, container, false);

        Rv = (RecyclerView) view.findViewById(R.id.time_recycler_view);

        // 初始化显示的数据
        //initData();

        // 绑定数据到RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);

        //为ListView绑定适配器
        myAdapter = new BoxHistoryAdapter();
        Rv.setAdapter(myAdapter);

        //Option item1 = new Option("一起去济南吧", Calendar.getInstance(), 1090);
        //Option item2 = new Option("苏州gogogo", Calendar.getInstance(), 1090);
        //Option item3 = new Option("去大理看云海", Calendar.getInstance(), 1090);
        //Option item4 = new Option("西藏西藏！", Calendar.getInstance(), 1);

        optionViewModel = ViewModelProviders.of(this.getActivity()).get(OptionViewModel.class);
       // optionViewModel.deleteAllOptions();
        //optionViewModel.insertOptions(item1, item2, item3, item4);

        imageButton = (ImageButton) view.findViewById(R.id.rest_choices);
        button = (Button) view.findViewById(R.id.ask_button);

        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取参数
        ParentId = getArguments().getInt("ParentId");
        optionViewModel.getOptionsByParent(ParentId).observe(this.getViewLifecycleOwner(), new Observer<List<Option>>() {
            @Override
            public void onChanged(List<Option> options) {
                myAdapter.SetBoxHistoryList(options);
                myAdapter.notifyDataSetChanged();
            }
        });

        optionViewModel.loadNewOptionByParent(ParentId).observe(this.getViewLifecycleOwner(), new Observer<List<Option>>() {
            @Override
            public void onChanged(List<Option> randList) {
                int count = randList.size();
                //Log.i("11111111","answer="+count);
                if (count > 0) {
                    Random r = new Random();
                    int i = r.nextInt(count);
                    Option option = randList.get(i);
                    String answer = option.getOptionName();
                    ans.setAnswer(answer);
                    ans.setOption(option);
                    //Log.i("111","answer="+answer);
                    //Log.i("333","answer="+ans.getAnswer());
                } else {
                    String answer = "There is no more choices in the conch";
                    ans.setAnswer(answer);
                    //Log.i("222","answer="+answer);
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ParentId", ParentId);
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_boxFragment_to_restFragment, bundle);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswer();
            }
        });
    }

    public void showAnswer() {
        builder = new AlertDialog.Builder(this.getContext()).setIcon(R.drawable.couch).setTitle("神奇海螺的答案是……");
        builder.create();
        builder.setMessage(ans.getAnswer());
        builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!ans.isNull()) {
                    Option option = ans.getOption();
                    option.setTimes(1);
                    optionViewModel.updateOptions(option);
                }
                dialog.dismiss();
                //Log.i("boxfragment","you clicker ok!");
            }
        }).setNegativeButton("No!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

}