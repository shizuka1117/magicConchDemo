package com.example.magiccouchdemo.ui.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.FragmentDashboardSetBinding;
import com.example.magiccouchdemo.databinding.FragmentHomeSetBinding;
import com.example.magiccouchdemo.ui.dashboard.CancelDialogFragment;

import static android.view.View.INVISIBLE;

public class HomeSetFragment extends Fragment {

    FragmentHomeSetBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = getActivity().findViewById(R.id.homeSetFragment);
        //v.setVisibility(INVISIBLE); 加了这个就闪退，null pointer reference
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home_set, container, false);
        binding.setLifecycleOwner(getActivity());
        binding.cancelButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelDialogFragment dialogFragment1 = new CancelDialogFragment();
                dialogFragment1.show(getFragmentManager(), "EditNameDialog");
            }
        });

        binding.nextStepButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //常用决策设置完成，存入数据库
            }
        });

        return binding.getRoot();
    }
}