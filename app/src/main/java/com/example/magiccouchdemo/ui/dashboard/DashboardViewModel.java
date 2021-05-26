package com.example.magiccouchdemo.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<HashMap<String, Integer>> choices;

    public DashboardViewModel() {

    }

}