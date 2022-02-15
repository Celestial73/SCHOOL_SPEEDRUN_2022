package com.example.school_speedrun_2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.MyViewHolder> {

    private List<Question> questionList;
    private onQuestionListener onQuestionListener;
    private Context context;

    public QuestionListAdapter(Context context, Test test, onQuestionListener onQuestionListener) {
        this.onQuestionListener = onQuestionListener;
        this.context = context;
        this.questionList = test.getQuestionList();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.question_recyclerview_row, parent, false);
        return new MyViewHolder(view, onQuestionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.questionText.setText(questionList.get(position).getQuestion());
        holder.topicText.setText(questionList.get(position).getTopic());
        holder.indexText.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        onQuestionListener onQuestionListener;
        TextView questionText, topicText, indexText;
        public MyViewHolder(@NonNull View itemView, onQuestionListener onQuestionListener) {
            super(itemView);
            this.onQuestionListener = onQuestionListener;
            questionText = itemView.findViewById(R.id.questionText);
            topicText = itemView.findViewById(R.id.topicText);
            indexText = itemView.findViewById(R.id.indexText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onQuestionListener.onQuestionClick(getAdapterPosition());
        }
    }

    //Interface for the fragment.
    public interface onQuestionListener{
        void onQuestionClick(int position);
    }
}