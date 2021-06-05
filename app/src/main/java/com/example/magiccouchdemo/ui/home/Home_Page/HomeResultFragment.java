package com.example.magiccouchdemo.ui.home.Home_Page;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.databinding.FragmentHomeResultBinding;
import com.example.magiccouchdemo.ui.home.HomeViewModel;


public class HomeResultFragment extends Fragment {
    private HomeViewModel model;
    private FragmentHomeResultBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        model = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_result, container, false);
        binding.setLifecycleOwner(getActivity());
        Log.d("result", "onCreateView: "+model.getResult());
        binding.result.setText(model.getResult());
        binding.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
                NavController controller = Navigation.findNavController(v);
            }
        });
        return binding.getRoot();
    }
}