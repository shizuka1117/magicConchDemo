package com.example.magiccouchdemo.ui.longtermdecision;

import android.os.Bundle;
import android.text.TextUtils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.OptionViewModel;
import com.example.magiccouchdemo.dataBase.Theme;
import com.example.magiccouchdemo.dataBase.ThemeViewModel;
import com.example.magiccouchdemo.databinding.FragmentLongTermDecSetBinding;
import com.example.magiccouchdemo.dataBase.Option;

import java.util.List;

public class LongTermDecSetFragment extends Fragment {

    LongTermDecSetAdapter adapter;
    LongTermViewModel longTermViewModel;
    FragmentLongTermDecSetBinding binding;
    ThemeViewModel themeViewModel;
    OptionViewModel optionViewModel;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        longTermViewModel = new ViewModelProvider(getActivity()).get(LongTermViewModel.class);
        optionViewModel = new ViewModelProvider(getActivity()).get(OptionViewModel.class);
        themeViewModel = new ViewModelProvider(getActivity()).get(ThemeViewModel.class);
        themeViewModel.getMaxLongThemeID().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                longTermViewModel.setMaxThemeID(integer);
            }
        });

        getActivity().findViewById(R.id.nav_view).setVisibility(View.INVISIBLE);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_long_term_dec_set, container, false);
        binding.setLifecycleOwner(getActivity());
        binding.recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LongTermDecSetAdapter(getContext());

        optionViewModel.getOptionsByParent(longTermViewModel.getId()).observe(this.getViewLifecycleOwner(), new Observer<List<Option>>() {
            @Override
            public void onChanged(List<Option> options) {
                for(int i= 0; i<options.size(); i++)
                    options.get(i).setParentId(longTermViewModel.getId());
                adapter.getItems().clear();
                adapter.getItems().addAll(options);
                adapter.notifyDataSetChanged();
            }
        });


        binding.setLongmodel(longTermViewModel);
        binding.recyclerView1.setAdapter(adapter);
        binding.nextButton1.bringToFront();
        binding.addOption1.bringToFront();
        binding.longTermDecTitle.getText().toString();

        binding.nextButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(binding.longTermDecTitle.getText())){
                    Toast.makeText(v.getContext(), "决策主题不能为空！", Toast.LENGTH_SHORT).show();
                }
                else if(adapter.getItems().size()<1){
                    Toast.makeText(v.getContext(), "还没有添加任何选项！", Toast.LENGTH_SHORT).show();
                }
                else{

                    //将theme和其对应的option插入到database中
                    //insert theme
                    themeViewModel = new ViewModelProvider(getActivity()).get(ThemeViewModel.class);
                    String name = longTermViewModel.getDecName().getValue().toString();
                    Log.d("11111","name="+name);
                    Theme item = new Theme(null,name,"long");
                    themeViewModel.insertThemes(item);
                    Log.d("msg","insert theme successfully");
                    longTermViewModel.setLongList(adapter.getItems());

                    //insert option
                    for(int i = 0; i< longTermViewModel.getLongList().size(); i++){
                        Option option = longTermViewModel.getLongList().get(i);
                        option.setParentId(longTermViewModel.getMaxThemeID()+1);
                        optionViewModel.insertOptions(option);
                    }
                    Log.d("msg","insert option successfully");


                    longTermViewModel.setLongList(adapter.getItems());
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_longTermDecSetFragment_to_longTermFragment);
                }
            }
        });
        /*
        adapter.getItems().add(new Option("张三"));
        adapter.getItems().add(new Option("李四"));
        adapter.getItems().add(new Option("王五"));
*/
        binding.addOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Option option = new Option();
                adapter.addData(adapter.getItemCount(), option);
                binding.recyclerView1.scrollToPosition(adapter.getItemCount()-1);
            }
        });


        return binding.getRoot();

    }




    @Override
    public void onPause() {

        super.onPause();
    }

}
