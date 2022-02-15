package com.example.school_speedrun_2022;

import java.util.ArrayList;
import java.util.List;

public class Question {


    private byte questionType;
    /*
    types:
    0 - single answer (RadioButtons)
    1 - multiple answers (CheckBoxes)
    2 - input answer (EditText)
     */

    private String question;
    private List<String> variants;
    private String correctCheckBoxAnswers;
    private char correctRadioAnswer;
    private String correctInputAnswer;
    private String topic;


    // Three constructors for different question types


    public Question(String question, String topic, List<String> variants, String correctAnswers) {
        this.question = question;
        this.topic = topic;
        this.variants = variants;
        if (correctAnswers.length()>1){
            this.correctCheckBoxAnswers = correctAnswers;
            this.questionType = 1;
        }else{
            this.correctCheckBoxAnswers = correctAnswers;
            this.questionType = 0;
        }


    }

    public Question(String question, String topic, String correctAnswer) {
        this.question = question;
        this.topic = topic;
        this.correctInputAnswer = correctAnswer;
        this.questionType = 2;
    }

    public byte getQuestionType() {
        return questionType;
    }

    public void setQuestionType(byte questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getVariants() {
        return variants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

    public String getCorrectCheckBoxAnswers() {
        return correctCheckBoxAnswers;
    }

    public void setCorrectCheckBoxAnswers(String correctCheckBoxAnswers) {
        this.correctCheckBoxAnswers = correctCheckBoxAnswers;
    }

    public char getCorrectRadioAnswer() {
        return correctRadioAnswer;
    }

    public void setCorrectRadioAnswer(char correctRadioAnswer) {
        this.correctRadioAnswer = correctRadioAnswer;
    }

    public String getCorrectInputAnswer() {
        return correctInputAnswer;
    }

    public void setCorrectInputAnswer(String correctInputAnswer) {
        this.correctInputAnswer = correctInputAnswer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
