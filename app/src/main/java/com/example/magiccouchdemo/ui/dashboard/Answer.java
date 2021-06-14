package com.example.magiccouchdemo.ui.dashboard;

import com.example.magiccouchdemo.dataBase.Option;

public class Answer {
    String answer;
    Option option;

    public Answer(){
        this.answer = null;
    }
    public Answer(String answer, Option option) {
        this.answer = answer;
        this.option = option;
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

    public boolean isNull(){
        return (this.answer==null);
    }
}
