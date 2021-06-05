package com.example.magiccouchdemo.ui.home;

import java.util.Calendar;

public class Option {

    String optionName;
    int value;
    int times;
    private Calendar date;

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Option(){

    }
    public Option (String optionName){
        this.optionName = optionName;
    }

    public Option(String name, Calendar date){
        this.optionName = name;
        this.date=Calendar.getInstance();
    }

    public Option(String optionName, int value) {
        this.optionName = optionName;
        this.value = value;
    }


    public void addValue(int i){
        value+=i;
        if(value>10)
            value=10;
        else if(value<0)
            value=0;
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
}
