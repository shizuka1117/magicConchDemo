package com.example.magiccouchdemo;

import android.os.Bundle;
import android.view.View;

import com.example.magiccouchdemo.ui.dashboard.CancelDialogFragment;
import com.example.magiccouchdemo.ui.dashboard.DashboardSetFragment;
import com.example.magiccouchdemo.ui.home.HomeCancelDialogFragment;
import com.example.magiccouchdemo.ui.home.HomeSetFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class NavigationActivity extends AppCompatActivity implements CancelDialogFragment.NoticeDialogListener,
        HomeCancelDialogFragment.NoticeDialogListener {
    @Override
    public void onDialogPositiveClick(HomeCancelDialogFragment dialog) {
        View v = findViewById(R.id.nav_view);
        v.setVisibility(View.VISIBLE);
        NavController controller = Navigation.findNavController(dialog.getParentFragment().getView());
        controller.navigate(R.id.action_homeSetFragment_to_homeFragment);
    }

    @Override
    public void onDialogPositiveClick(CancelDialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    public void onDialogNegativeClick(CancelDialogFragment dialog) {
        View v = findViewById(R.id.nav_view);
        v.setVisibility(View.VISIBLE);
        NavController controller = Navigation.findNavController(dialog.getParentFragment().getView());
        controller.navigate(R.id.blankFragment);
    }

    @Override
    public void onDialogNegativeClick(HomeCancelDialogFragment dialog) {
        View v = findViewById(R.id.nav_view);
        v.setVisibility(View.VISIBLE);
        NavController controller = Navigation.findNavController(dialog.getParentFragment().getView());
        controller.navigate(R.id.action_homeSetFragment_to_homeFragment);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}