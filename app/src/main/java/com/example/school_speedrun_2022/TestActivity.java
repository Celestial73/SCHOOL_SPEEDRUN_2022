package com.example.school_speedrun_2022;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements Test_question_list_fragment.leftFragmentListener, Test_Question_fragment.rightFragmentListener{
    private Test_question_list_fragment leftFragment;
    private Test_Question_fragment rightFragment;
    private Test test;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);



        List<Question> questionList = new ArrayList<Question>();
        List<String> q1Variants = new ArrayList<String>();
        q1Variants.add("1850");
        q1Variants.add("650");
        q1Variants.add("1488");
        q1Variants.add("-399");
        Question q1 = new Question("В каком году начал править Николай 2?", "19 ВЕК",  q1Variants,"12");
        questionList.add(q1);
        Question q2 = new Question("Wasd1", "Abcs1", "Niggers1");
        questionList.add(q2);
        Question q3 = new Question("Wasd2", "Abcs2", "Niggers2");
        questionList.add(q3);
        List<String> variantList = new ArrayList<String>();
        variantList.add("wasd");
        variantList.add("masdfl");
        variantList.add("awds");
        Question q4 = new Question("jksdhfksdkhf", "sjdfhsldkj", variantList, "2");
        Test test = new Test(questionList);
        this.test = test;
        leftFragment = new Test_question_list_fragment();
        leftFragment.setTest(test);
        rightFragment = new Test_Question_fragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.leftFragment, leftFragment)
                .replace(R.id.rightFragment, rightFragment)
                .commit();
        Button start = findViewById(R.id.startBtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightFragment.updateQuestionLayout(test.getQuestionList().get(0), 0);
            }
        });

    }
    @Override
    public void recyclerTouched(int position) {
        Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveAnswer(int position, String answer) {

    }
}
