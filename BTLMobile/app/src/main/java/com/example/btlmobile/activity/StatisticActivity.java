package com.example.btlmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.btlmobile.R;
import com.example.btlmobile.api.ApiService;
import com.example.btlmobile.model.Statistic;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticActivity extends AppCompatActivity {
    private TextView totalWordsTextView,nounsTextView,verbsTextView,adjectivesTextView,prepositionsTextView,adverbsTextView;
    private List<Statistic> statisticList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        totalWordsTextView = findViewById(R.id.textview_total_words);
        nounsTextView = findViewById(R.id.textview_nouns);
        verbsTextView = findViewById(R.id.textview_verbs);
        adjectivesTextView = findViewById(R.id.textview_adjectives);
        prepositionsTextView = findViewById(R.id.textview_prepositions);
        adverbsTextView = findViewById(R.id.textview_adverbs);

    }


    private void callApiStatistic(){
        ApiService.apiService.getStatistic().enqueue(new Callback<List<Statistic>>() {
            @Override
            public void onResponse(Call<List<Statistic>> call, Response<List<Statistic>> response) {
                statisticList = response.body();
                for(Statistic statistic : statisticList){
                    if(statistic.getWord_type().equalsIgnoreCase("total")){
                        totalWordsTextView.setText("Số từ đã thêm: " + statistic.getTotal_count());
                    }
                    if(statistic.getWord_type().equalsIgnoreCase("verb")){
                        verbsTextView.setText("Số động từ thêm được: " + statistic.getTotal_count());
                    }
                    if(statistic.getWord_type().equalsIgnoreCase("noun")){
                        nounsTextView.setText("Số danh từ thêm được: " + statistic.getTotal_count());
                    }
                    if(statistic.getWord_type().equalsIgnoreCase("adjective")){
                        adjectivesTextView.setText("Số tính từ thêm được: " + statistic.getTotal_count());
                    }
                    if(statistic.getWord_type().equalsIgnoreCase("adverb")){
                        adverbsTextView.setText("Số trạng từ thêm được: " + statistic.getTotal_count());
                    }
                    if(statistic.getWord_type().equalsIgnoreCase("preposition")){
                        prepositionsTextView.setText("Số giới từ thêm được: "+ statistic.getTotal_count());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Statistic>> call, Throwable throwable) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalWordsTextView.setText("Số từ đã thêm: 0");
        nounsTextView.setText("Số danh từ thêm được: 0");
        verbsTextView.setText("Số động từ thêm được: 0" );
        adjectivesTextView.setText("Số tính từ thêm được: 0" );
        prepositionsTextView.setText("Số giới từ thêm được: 0");
        adverbsTextView.setText("Số trạng từ thêm được: 0" );
        callApiStatistic();
    }
}