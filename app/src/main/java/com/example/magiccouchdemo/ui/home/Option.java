package com.example.magiccouchdemo.ui.home;

import java.util.Calendar;

public class Option {

    String optionName;
    int value;
    private Calendar date;

    public Option (){

    }
    public Option(String optionName, int value) {
        this.optionName = optionName;
        this.value = value;
    }

    public Option(String name, Calendar date){
        this.optionName = name;
        this.date=Calendar.getInstance();
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar date) {
        this.date = date;
    }
}
