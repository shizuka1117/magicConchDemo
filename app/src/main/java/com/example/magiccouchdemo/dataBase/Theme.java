package com.example.magiccouchdemo.dataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Theme {
    @PrimaryKey(autoGenerate = true)
    private int t_id;   //决策主题id，自动生成

    private String tag; //标签
    private String name;    //主题名
    private String type;    //类型 long or short

    public Theme(String tag, String name, String type) {
        this.tag = tag;
        this.name = name;
        this.type = type;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
