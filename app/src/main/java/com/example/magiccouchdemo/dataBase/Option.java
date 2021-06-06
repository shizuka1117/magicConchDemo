package com.example.magiccouchdemo.dataBase;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Option {
    @PrimaryKey(autoGenerate = true)
    int ParentId;
    String optionName;
    int value;
    int times;
    String date;

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public Calendar getDate() throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        if(date.isEmpty())
            return null;
        Date time =sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Option(){
    }

    @Ignore
    public Option (String optionName){
        this.optionName = optionName;
        this.value = 1;
        date = new String("");
    }

    @Ignore
    public Option(String name, Calendar date){
        this.optionName = name;
        this.date=Calendar.getInstance().toString();
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