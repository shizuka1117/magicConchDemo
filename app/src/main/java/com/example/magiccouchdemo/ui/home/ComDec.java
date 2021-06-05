package com.example.magiccouchdemo.ui.home;

public class ComDec {
    private String event_name;
   //private String color;
    private String tag;
    //construction
    ComDec(){}

    public ComDec(String name, String tag){
        this.event_name=name;
        this.tag=tag;
    }

    public String getName(){
        return event_name;
    }

    public String getTag(){
        return tag;
    }

}
