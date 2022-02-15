package com.example.school_speedrun_2022;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Test_question_list_fragment extends Fragment implements QuestionListAdapter.onQuestionListener{
    private RecyclerView questionRecyclerView;
    private leftFragmentListener listener;
    private Test test;

    public Test_question_list_fragment(){
    }

    public void setTest(Test test){
        this.test = test;
    }

    //method from the adapter interface.
    //RecyclerView sends the position of a click here.
    @Override
    public void onQuestionClick(int position) {
        listener.recyclerTouched(position);
    }


    //Interface for the activity.
    //Method is called in onQuestionClick every time the recyclerView is clicked
    public interface leftFragmentListener{
        void recyclerTouched(int position);
    }


    //Attaching listener(activity) after the fragment is attached.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof leftFragmentListener){
            listener = (leftFragmentListener) context;
        }else {
            throw new RuntimeException(context.toString() + " must implement leftFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.questions_recyclerview_fragment, container, false);
        questionRecyclerView = view.findViewById(R.id.questionsRecyclerView);
        QuestionListAdapter myAdapter = new QuestionListAdapter(view.getContext(), this.test, this);
        questionRecyclerView.setAdapter(myAdapter);
        questionRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}