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
        /*Yellow*/
        colors.add("#FFFAF0");//FLowerWhite
        colors.add("#FFF8DC");//LightYellow

        /*Pink*/
        colors.add("#FFF5EE");//sea shell
        colors.add("#fae8e8");//FlowerBlush

        /*Green*/
        colors.add("#F0FFF0");//Honeydew
        colors.add("#e3f1de");//melodyGreen

        /*orange*/
        colors.add("#f7e7d8");

        /*Blue*/
        colors.add("#F0F8FF");//AliceBlue
        colors.add("#e3eef8");//melodyBlue

        /*Purple*/
        colors.add("#eaeafb");//Lavender
        colors.add("#E6E6FA");


    }

    public List<String> getColor(){
        setColors();
        return colors;
    }
}
