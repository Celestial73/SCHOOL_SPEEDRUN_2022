package com.example.school_speedrun_2022;

import java.util.List;

public class Test {
    private List<Question> questionList;
    private String lesson;

    public Test(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
