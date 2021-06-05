package com.example.magiccouchdemo.ui.home.Home_Page;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class randomColor {
    List<Integer> colors;//= new ArrayList<>();

    randomColor(){
        colors = new ArrayList<>();
    }

    public void setColors(){
        colors.add(0xCBB8ED);
        colors.add(0xFFEB3B);
        colors.add(0xF5F5F5);
        colors.add(0xDCDCDC);
        colors.add(0xFFFAF0);
        colors.add(0xFDF5F6);
        colors.add(0xFFF8DC);
        colors.add(0xFFE4C4);
        colors.add(0XFFDAB9);
        colors.add(0xFAFAD2);
        colors.add(0xFFFFF0);
        colors.add(0xFFF5EE);
        colors.add(0XF0FFF0);
        colors.add(0xE6E6FA);
        colors.add(0xFFF0F5);
        colors.add(0xFFE4E1);
        colors.add(0xCAE1FF);
        colors.add(0xBFEFFF);
        colors.add(0xEDCECE);
        colors.add(0xD0B9EA);
        colors.add(0xF6EA80);
        colors.add(0xE6C1C1);
        colors.add(0xF4CDA4);
        colors.add(0xD0F8B4);
        //Log.d("color0:",colors.get(0)+"");
    }

    public List<Integer> getColor(){
        setColors();
        return colors;
    }
}
