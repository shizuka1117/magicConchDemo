package com.example.magiccouchdemo;

import android.os.Bundle;
import android.view.View;

import com.example.magiccouchdemo.ui.dashboard.CancelDialogFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class NavigationActivity extends AppCompatActivity implements CancelDialogFragment.NoticeDialogListener{
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.nav_view);
        v.setVisibility(View.VISIBLE);
        NavController controller = Navigation.findNavController(dialog.getParentFragment().getView());
        //确认提交
        controller.navigate(R.id.action_dashboardSetFragment_to_navigation_dashboard);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        dialog.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

}