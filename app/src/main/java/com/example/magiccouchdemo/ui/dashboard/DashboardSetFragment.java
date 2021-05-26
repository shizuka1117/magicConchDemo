package com.example.magiccouchdemo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.FragmentDashboardSetBinding;

public class DashboardSetFragment extends Fragment {
    FragmentDashboardSetBinding binding;

    private DashboardViewModel model;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        model = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard_set, container, false);
        binding.setLifecycleOwner(getActivity());
        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                //确认返回
                controller.navigate(R.id.action_dashboardSetFragment_to_navigation_dashboard);
            }
        });
        binding.nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                //确认提交
                controller.navigate(R.id.action_dashboardSetFragment_to_dashboardResultFragment);
            }
        });
        return binding.getRoot();
    }
}