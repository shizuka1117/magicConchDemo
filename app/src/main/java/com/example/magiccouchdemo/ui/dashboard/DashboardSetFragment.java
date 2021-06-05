package com.example.magiccouchdemo.ui.dashboard;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.magiccouchdemo.R;

public class DashboardSetFragment extends Fragment {
    /*FragmentDashboardSetBinding binding;

    private DashboardViewModel model;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = getActivity().findViewById(R.id.nav_view);
        v.setVisibility(View.INVISIBLE);
        model = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard_set, container, false);
        binding.setLifecycleOwner(getActivity());
        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelDialogFragment dialogFragment = new CancelDialogFragment();
                dialogFragment.show(getFragmentManager(), "EditNameDialog");
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

     */
}