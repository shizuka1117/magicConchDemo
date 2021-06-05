package com.example.magiccouchdemo.ui.home;

public class Option {

    String optionName;
    int value;

    public Option(){

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
