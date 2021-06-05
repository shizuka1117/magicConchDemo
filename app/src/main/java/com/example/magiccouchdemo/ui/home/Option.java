package com.example.magiccouchdemo.ui.home;

import java.util.Calendar;

public class Option {

    String optionName;
    int value;
    private Calendar date;
    private int times;

    public Option (){

    }
    public Option (String optionName){
        this.optionName = optionName;
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

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
