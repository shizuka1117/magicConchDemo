package com.example.magiccouchdemo.ui.longtermdecision;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.magiccouchdemo.dataBase.Option;
import com.example.magiccouchdemo.dataBase.Theme;

public class LongTermViewModel extends ViewModel {

    public ObservableArrayList<Option> longList = new ObservableArrayList<>();
    private MutableLiveData<Integer> id = new MutableLiveData<>(0);
    private MutableLiveData<String> decName = new MutableLiveData<>();
    private Theme theme = new Theme(null, "", "long");
    private MutableLiveData<Integer> maxThemeID = new MutableLiveData<Integer>();
    private String result;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Integer getMaxThemeID() {
        return maxThemeID.getValue();
    }

    public void setMaxThemeID(Integer maxThemeID) {
        this.maxThemeID.setValue(maxThemeID);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getId() {
        return id.getValue();
    }

    public void setId(Integer id) {
        this.id.setValue(id);
    }

    public ObservableArrayList<Option> getLongList() {
        return longList;
    }

    public void setLongList(ObservableArrayList<Option> longList) {
        this.longList = longList;
    }

    public MutableLiveData<String> getDecName() {
        return decName;
    }

    public void setDecName(String decName) {
        this.decName.setValue(decName);
    }

    public void clear(){
        this.setLongList(new ObservableArrayList<Option>());
        this.setDecName("");
        this.setId(0);
    }

}
