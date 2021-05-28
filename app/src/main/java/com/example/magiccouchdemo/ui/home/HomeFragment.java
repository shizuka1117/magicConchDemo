package com.example.magiccouchdemo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import com.example.magiccouchdemo.databinding.FragmentDashboardSetBinding;
import com.example.magiccouchdemo.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FragmentHomeBinding binding;

    private List<ComDec> ComDecList = new ArrayList<>();

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
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, null, false);
        //绑定fragment_home.xml
        binding.setLifecycleOwner(getActivity());
        binding.fab.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_navigation_home_to_homeSetFragment);
        });

        //ListView
        initComDec();
        ComDecAdapter adapter = new ComDecAdapter(getActivity(),
                R.layout.com_dec_data, ComDecList);
        binding.list1.setAdapter(adapter);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取menu resource
        setHasOptionsMenu(true);
    }
}