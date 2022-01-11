package com.example.school_speedrun_2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Test_Question_fragment extends Fragment implements View.OnClickListener{
    View mainLayout;
    Question currentQuestion;
    rightFragmentListener listener;
    int currentQuestionPosition;
    byte currentQuestionType;
    private  boolean questionSaved;


    public interface rightFragmentListener{
        void saveAnswer(int position, String answer);
    }

    //Attaching listener(activity) after the fragment is attached.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Test_question_list_fragment.leftFragmentListener){
            listener = (Test_Question_fragment.rightFragmentListener) context;
        }else {
            throw new RuntimeException(context.toString() + " must implement rightFragmentListener");
        }
    }


    public Test_Question_fragment() {
        // Required empty public constructor
    }


    public void updateQuestionLayout(Question question, int position){
        questionSaved = false;
        currentQuestionType = question.getQuestionType();
        currentQuestionPosition = position;
        currentQuestion = question;

        TextView numberText = (TextView) mainLayout.findViewById(R.id.numberText);
        numberText.setText(String.valueOf(position+1));

        TextView questionText = (TextView) mainLayout.findViewById(R.id.testQuestionText);
        questionText.setText(question.getQuestion());

        LinearLayout variantLinLay = (LinearLayout) clearVariantLayout();


        switch (question.getQuestionType()){
            case 0:
                createSingleAnswerQuestion(question, variantLinLay);
                break;
            case 1:
                createMultipleAnswerQuestion(question, variantLinLay);
                break;
            case 2:
                createInputAnswerQuestion(question, variantLinLay);
                break;
        }


    }

    private String getAnswer(LinearLayout variantLinLay) {
        int childCount;
        switch (currentQuestionType) {
            case 0:
                childCount = variantLinLay.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = variantLinLay.getChildAt(i);
                    if (child instanceof RadioGroup) {
                        Toast.makeText(getContext(), String.valueOf(((RadioGroup) child).getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();
                        return  String.valueOf(((RadioGroup) child).getCheckedRadioButtonId());
                    }
                    break;
                }
            case 1:
                String answer = "";
                childCount = variantLinLay.getChildCount();
                for(int i =0; i<childCount; i++) {
                    View child = variantLinLay.getChildAt(i);
                    if (child instanceof CheckBox & ((CheckBox) child).isChecked()) {
                        answer += String.valueOf(child.getId());
                    }
                }
                Toast.makeText(getContext(), answer, Toast.LENGTH_SHORT).show();
                return answer;
            case 2:
                Toast.makeText(getContext(), ((EditText) variantLinLay.getChildAt(0)).getText().toString(), Toast.LENGTH_SHORT).show();
                return ((EditText) variantLinLay.getChildAt(0)).getText().toString();
        }
        return "";
    }



    private void createInputAnswerQuestion(Question question, LinearLayout variantLinLay){
        EditText edit = new EditText(getContext());
        edit.setId(0);
        edit.setHint("Введите свой ответ");
        variantLinLay.addView(edit);
    }

    private void createMultipleAnswerQuestion(Question question, LinearLayout variantLinLay) {
        CheckBox cb;
        byte variantAmount = (byte) question.getVariants().size();
        for(int i =0; i<variantAmount; i++){
            cb = new CheckBox(getContext());
            cb.setText(question.getVariants().get(i));
            cb.setId(i);
            variantLinLay.addView(cb);
        }
    }

    private void createSingleAnswerQuestion(Question question, LinearLayout variantLinLay) {
        RadioGroup radioGroup = new RadioGroup(getContext());
        RadioButton rb;
        byte variantAmount = (byte) question.getVariants().size();
        for (int i =0; i<variantAmount; i++){
            rb = new RadioButton(getContext());
            rb.setText(question.getVariants().get(i));
            rb.setId(i);
            radioGroup.addView(rb);
        }
        variantLinLay.addView(radioGroup);
    }

    private LinearLayout clearVariantLayout() {
        LinearLayout variantLinLay = mainLayout.findViewById(R.id.variantsLayout);
        variantLinLay.removeAllViews();

        return variantLinLay;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test__question_fragment, container, false);
        Toast.makeText(getContext(), "213123", Toast.LENGTH_SHORT).show();
        this.mainLayout = view;
        Button saveBtn = mainLayout.findViewById(R.id.saveQuestionAnswerBtn);
        saveBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        LinearLayout linLay = mainLayout.findViewById(R.id.variantsLayout);
        getAnswer(linLay);
    }


}