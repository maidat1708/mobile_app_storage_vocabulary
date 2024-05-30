package com.example.btlmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlmobile.R;
import com.example.btlmobile.model.Answer;

import java.util.ArrayList;
import java.util.List;

public class ListenAdapter extends RecyclerView.Adapter<ListenAdapter.ListenViewHolder>{

    private List<Answer> listAnswer;


    public ListenAdapter() {

    }

    public void setListAnswer(List<Answer> list) {
        this.listAnswer = new ArrayList<>();
        for(Answer a : list){
            listAnswer.add(a);
        }
        notifyDataSetChanged();
    }


    public Answer getAnswer(int position){
        return listAnswer.get(position);
    }

    @NonNull
    @Override
    public ListenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listen_item,parent,false);
        return new ListenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListenViewHolder holder, int position) {
        Answer answer = listAnswer.get(position);
        holder.text_view_question.setText(answer.getQuestion());
        holder.text_view_ans1.setText(answer.getAns1());
        holder.text_view_ans2.setText(answer.getAns2());
        holder.text_view_ans3.setText(answer.getAns3());
        holder.text_view_ans4.setText(answer.getAns4());

    }

    @Override
    public int getItemCount() {
        return listAnswer.size();
    }

    public class ListenViewHolder extends RecyclerView.ViewHolder{

        private TextView text_view_question,text_view_ans1,text_view_ans2,text_view_ans3,text_view_ans4;

        public ListenViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_question = itemView.findViewById(R.id.question);
            text_view_ans1 = itemView.findViewById(R.id.ans1);
            text_view_ans2 = itemView.findViewById(R.id.ans2);
            text_view_ans3 = itemView.findViewById(R.id.ans3);
            text_view_ans4 = itemView.findViewById(R.id.ans4);
        }
    }
}
