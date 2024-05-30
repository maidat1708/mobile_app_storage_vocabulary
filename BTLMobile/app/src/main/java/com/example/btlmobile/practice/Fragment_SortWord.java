package com.example.btlmobile.practice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btlmobile.R;
import com.example.btlmobile.api.ApiService;
import com.example.btlmobile.model.Streak;
import com.example.btlmobile.model.Vocabulary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_SortWord extends Fragment implements View.OnClickListener{
    private TextView text_view_sortword,text_view_score;
    private EditText edit_text_sortword;
    private Button btn_submit;
    private TextView tvscore,tvhighestscore;
    private Vocabulary vocabulary;
    private Streak streak;
    private int score = 0;
    private int high_score;
    public static String shuffleString(String input) {

        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }

        Collections.shuffle(characters);

        StringBuilder shuffledString = new StringBuilder();
        for (char c : characters) {
            shuffledString.append(c);
        }

        return shuffledString.toString();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sortword,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text_view_sortword = view.findViewById(R.id.text_view_sortword);
        edit_text_sortword = view.findViewById(R.id.edit_text_sortword);
        tvscore = view.findViewById(R.id.score);
        tvhighestscore = view.findViewById(R.id.scorehighest);
        btn_submit = view.findViewById(R.id.btn_submit);
        callApiRandWord(1);
        callApiGetStreak();
        btn_submit.setOnClickListener(this);
    }
    private void callApiRandWord(int number){
        ApiService.apiService.getRandWord(number).enqueue(new Callback<List<Vocabulary>>() {
            @Override
            public void onResponse(Call<List<Vocabulary>> call, Response<List<Vocabulary>> response) {
                vocabulary = response.body().get(0);
                String ques = shuffleString(vocabulary.getWord());
                String question = "";
                for(int i = 0 ; i < ques.length();i++){
                    if(i == ques.length() - 1){
                        question += ques.charAt(i) + "?";
                        break;
                    }
                    question += ques.charAt(i) + "/";
                }
                text_view_sortword.setText(question);
            }
            @Override
            public void onFailure(Call<List<Vocabulary>> call, Throwable throwable) {

            }
        });
    }

    private void callApiGetStreak(){
        ApiService.apiService.getStreak().enqueue(new Callback<Streak>() {
            @Override
            public void onResponse(Call<Streak> call, Response<Streak> response) {
                high_score = response.body().getSortWord();
                tvhighestscore.setText("Highest Score: "+high_score);
                streak = response.body();
            }

            @Override
            public void onFailure(Call<Streak> call, Throwable throwable) {

            }
        });
    }

    private void callApiUpdateStreak(Streak streak){
        ApiService.apiService.updateStreak(streak).enqueue(new Callback<Streak>() {
            @Override
            public void onResponse(Call<Streak> call, Response<Streak> response) {
                high_score = response.body().getSortWord();
            }

            @Override
            public void onFailure(Call<Streak> call, Throwable throwable) {

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        callApiRandWord(1);
        edit_text_sortword.setText("");
    }

    @Override
    public void onClick(View view) {
        if(view == btn_submit){
            String ans = edit_text_sortword.getText().toString();
            String result ;
            if(ans.equalsIgnoreCase(vocabulary.getWord()))
            {
                result = "Correct";
                score += 1;
                if(score > high_score){
                    high_score = score;
                    tvhighestscore.setText("Highest Score: "+high_score);
                }
                tvscore.setText("Score: " + score);
                callApiRandWord(1);
                showCustomAlertDialog("Result", result);
            }
            else{
                result = "Incorrect";
                if(high_score > streak.getSortWord())
                {
                    streak.setSortWord(high_score);
                    callApiUpdateStreak(streak);
                }
                callApiRandWord(2);
                showScoreAlertDialog();
                showCustomAlertDialog("Result", result);
                score = 0;
                tvscore.setText("Score: 0");
            }
        }
    }

    private void showCustomAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_result, null);
        builder.setView(view);

        TextView textViewTitle = view.findViewById(R.id.text_view_result_title);
        TextView textViewMessage = view.findViewById(R.id.text_view_result_message);
        Button btn_ok = view.findViewById(R.id.btn_ok);

        textViewTitle.setText(title);
        textViewMessage.setText(message);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApiRandWord(1);
                edit_text_sortword.setText("");
                alertDialog.dismiss();
            }
        });
    }
    private void showScoreAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Inflate custom layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_score, null);

        // Set score text
        TextView textViewScore = dialogView.findViewById(R.id.text_view_score);
        textViewScore.setText(score+"");
        TextView textViewScoreHighest = dialogView.findViewById(R.id.text_view_score_highest);
        textViewScoreHighest.setText(high_score+"");

        // Set title
        TextView textViewTitle = dialogView.findViewById(R.id.text_view_score_title);
        textViewTitle.setText("Your Score");

        // Set button click listener
        Button buttonOK = dialogView.findViewById(R.id.button_ok);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog
                alertDialog.dismiss();
            }
        });
    }
}
