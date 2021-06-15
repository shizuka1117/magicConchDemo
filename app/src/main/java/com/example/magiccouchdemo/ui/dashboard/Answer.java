package com.example.magiccouchdemo.ui.dashboard;

import com.example.magiccouchdemo.dataBase.Option;

public class Answer {
    String answer;
    Option option;
    int flag;

    public Answer(){
        this.answer = null;
    }
    public Answer(String answer, Option option) {
        this.answer = answer;
        this.option = option;
        this.flag = 0;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
