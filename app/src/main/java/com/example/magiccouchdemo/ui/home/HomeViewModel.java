package com.example.magiccouchdemo.ui.home;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    public ObservableArrayList<Option> mList = new ObservableArrayList<>();
    private MutableLiveData<String> decName;
    private MutableLiveData<String> tag;
    private String result;

    public HomeViewModel() {

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ObservableArrayList<Option> getmList() {
        return mList;
    }

    public void setmList(ObservableArrayList<Option> mList) {
        this.mList = mList;
    }

    public MutableLiveData<String> getDecName() {
        return decName;
    }

    public MutableLiveData<String> getTag() {
        return tag;
    }

    public void setTag(MutableLiveData<String> tag) {
        this.tag = tag;
    }

    public void setDecName(MutableLiveData<String> decName) {
        this.decName = decName;
    }
}