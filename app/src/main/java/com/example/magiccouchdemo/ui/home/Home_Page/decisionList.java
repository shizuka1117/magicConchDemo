package com.example.magiccouchdemo.ui.home.Home_Page;


import java.util.Calendar;

public class decisionList {
    private String name;
    private int imgID;
    private String tag;


    public decisionList(String name, int id, String tag) {
        this.name = name;
        this.imgID = id;
        this.tag = tag;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
    
}
