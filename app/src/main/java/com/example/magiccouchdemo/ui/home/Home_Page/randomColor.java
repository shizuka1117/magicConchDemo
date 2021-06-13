package com.example.magiccouchdemo.ui.home.Home_Page;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class randomColor {
    List<String> colors;//= new ArrayList<>();

    public randomColor(){
        colors = new ArrayList<>();
    }

    public void setColors(){
        colors.add("#FAF9DE");//杏仁黄
        colors.add("#FFF8DC");//粉
        //colors.add("#E6E6FA");//Lavender
        colors.add("#F0FFF0");//HoneyDew
        colors.add("#FFF5EE");//Seashell
        colors.add("#F8F8FF");//GhostWhite
        colors.add("#F0FFFF");//Azure1
        colors.add("#FFF8DC");//Coresilk
        colors.add("#F5FFFA");//MintCream
        colors.add("#F0F8FF");//AliceBlue
        colors.add("#FFF0F5");//LavenderBlush
        colors.add("#FFFFF0");//Ivory
        colors.add("#FFFAF0");//FloralWhite
        colors.add("#E6E6FA");//Lavender
        //Log.d("color0:",colors.get(0)+"");
    }

    public List<String> getColor(){
        setColors();
        return colors;
    }
}
