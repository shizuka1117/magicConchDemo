package com.example.magiccouchdemo.ui.home;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.magiccouchdemo.dataBase.Option;
import com.example.magiccouchdemo.dataBase.Theme;

public class HomeViewModel extends ViewModel {
    public ObservableArrayList<Option> mList = new ObservableArrayList<>();
    private MutableLiveData<Integer> id = new MutableLiveData<>(-1);
    private MutableLiveData<String> decName = new MutableLiveData<>();
    private MutableLiveData<String> tag = new MutableLiveData<>();
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public MutableLiveData<Integer> getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id.setValue(id);
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

    public void setDecName(String decName) {
        this.decName.setValue(decName);
    }

    public MutableLiveData<String> getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag.setValue(tag);
    }

    public void clear(){
        this.setmList(new ObservableArrayList<Option>());
        this.setDecName("");
        this.setId(-1);
        this.setTag("");
    }
}