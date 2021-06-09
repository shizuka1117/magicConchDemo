package com.example.magiccouchdemo.ui.longtermdecision;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.magiccouchdemo.dataBase.Option;

public class LongTermViewModel extends ViewModel {

    public ObservableArrayList<Option> mList = new ObservableArrayList<>();
    private MutableLiveData<String> decName;
    //private MutableLiveData<String> tag;
    private String result;

    public LongTermViewModel() {

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
    /*
    public MutableLiveData<String> getTag() {
        return tag;
    }

    public void setTag(MutableLiveData<String> tag) {
        this.tag = tag;
    }
*/
    public void setDecName(MutableLiveData<String> decName) {
        this.decName = decName;
    }

}
