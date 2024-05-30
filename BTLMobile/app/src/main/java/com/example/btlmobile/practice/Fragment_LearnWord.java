package com.example.btlmobile.practice;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlmobile.R;
import com.example.btlmobile.adapter.LearnWordAdapter;
import com.example.btlmobile.api.ApiService;
import com.example.btlmobile.model.Streak;
import com.example.btlmobile.model.Vocabulary;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_LearnWord extends Fragment {
    private RecyclerView recyclerView;
    private Button btn_submit;
    private TextView tvscore,tvhighestscore;
    private LearnWordAdapter adapter;
    private List<Vocabulary> listVocab;
    private ProgressDialog mProgressDialog;
    private Streak streak;
    private int score = 0;
    private int high_score;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learnword,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_learnword);
        btn_submit = view.findViewById(R.id.btn_submit);
        tvscore = view.findViewById(R.id.score);
        tvhighestscore = view.findViewById(R.id.scorehighest);
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Loading...");
        adapter = new LearnWordAdapter();
        listVocab = new ArrayList<>();
        callApiRandWord(2);
        callApiGetStreak();
        adapter.setListVoca(listVocab);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = Integer.parseInt(adapter.checkSubmit());
                if(check == 2) {
                    score += 1;
                    if(score > high_score){
                        high_score = score;
                        tvhighestscore.setText("Highest Score: "+high_score);
                    }
                    tvscore.setText("Score: " + score);
                    callApiRandWord(2);
                }
                else{
                    if(high_score > streak.getLearnWord())
                    {
                        streak.setLearnWord(high_score);
                        callApiUpdateStreak(streak);
                    }
                    callApiRandWord(2);
                    showScoreAlertDialog();
                    score = 0;
                    tvscore.setText("Score: 0");
                }
            }
        });
    }

    private void callApiRandWord(int number){
        ApiService.apiService.getRandWord(number).enqueue(new Callback<List<Vocabulary>>() {
            @Override
            public void onResponse(Call<List<Vocabulary>> call, Response<List<Vocabulary>> response) {
                listVocab = response.body();
//                for(Vocabulary v: listVocab){
//                    System.out.println(v.getId());
//                }
                adapter.setListVoca(listVocab);
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Vocabulary>> call, Throwable throwable) {
                mProgressDialog.dismiss();
            }
        });
    }

    private void callApiGetStreak(){
        ApiService.apiService.getStreak().enqueue(new Callback<Streak>() {
            @Override
            public void onResponse(Call<Streak> call, Response<Streak> response) {
                high_score = response.body().getLearnWord();
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
                high_score = response.body().getLearnWord();
            }

            @Override
            public void onFailure(Call<Streak> call, Throwable throwable) {

            }
        });
    }
    // Trong phương thức showScoreAlertDialog()
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

    @Override
    public void onResume() {
        mProgressDialog.show();
        super.onResume();
        callApiRandWord(2);
    }
}
