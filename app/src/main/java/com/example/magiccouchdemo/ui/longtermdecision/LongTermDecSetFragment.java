package com.example.magiccouchdemo.ui.longtermdecision;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.FragmentLongTermDecSetBinding;
import com.example.magiccouchdemo.dataBase.Option;

public class LongTermDecSetFragment extends Fragment {

    LongTermDecSetAdapter adapter;
    LongTermViewModel longTermViewModel;
    FragmentLongTermDecSetBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        longTermViewModel = new ViewModelProvider(getActivity()).get(LongTermViewModel.class);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_long_term_dec_set, container, false);
        binding.setLifecycleOwner(getActivity());
        binding.recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.addOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Option option = new Option("");
                adapter.addData(adapter.getItemCount(), option);
                binding.recyclerView1.scrollToPosition(adapter.getItemCount()-1);
            }
        });
        adapter = new LongTermDecSetAdapter(getContext());
        //homeViewModel.setmList(adapter.getItems());
        binding.recyclerView1.setAdapter(adapter);
        binding.nextButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //String result = adapter.computeResult();
                //homeViewModel.setResult(result);
                if(TextUtils.isEmpty(binding.decTitle.getText())){
                    Toast.makeText(v.getContext(), "决策主题不能为空！", Toast.LENGTH_SHORT).show();
                }
                else{
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_longTermDecSetFragment_to_longTermFragment);
                }
            }
        });
        adapter.getItems().add(new Option("张三"));
        adapter.getItems().add(new Option("李四"));
        adapter.getItems().add(new Option("王五"));
        return binding.getRoot();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

}
