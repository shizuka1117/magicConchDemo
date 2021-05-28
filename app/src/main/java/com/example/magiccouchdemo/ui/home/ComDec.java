package com.example.magiccouchdemo.ui.home;

public class ComDec {
    private static String event_name;
   //private String color;
    private static String tag;
    //construction
    ComDec(){}

    public ComDec(String name, String tag){
        this.event_name=name;
        this.tag=tag;
    }

    public static String getName(){
        return event_name;
    }

    public static String getTag(){
        return tag;
    }

}
