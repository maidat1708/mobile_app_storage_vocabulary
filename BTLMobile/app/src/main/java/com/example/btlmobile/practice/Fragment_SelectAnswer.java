package com.example.btlmobile.practice;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlmobile.R;
import com.example.btlmobile.api.ApiService;
import com.example.btlmobile.model.Vocabulary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_SelectAnswer extends Fragment implements View.OnClickListener {
    private ImageView img_ques;
    private TextView question,ansA,ansB,ansC,ansD;
    private Button btn_new;
    private Vocabulary vocabulary;
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choseans,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Loading...");
        callApiGetQuestion();
        btn_new.setOnClickListener(this);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
    }

    private void initView(View view) {
        img_ques = view.findViewById(R.id.img_upload);
        question = view.findViewById(R.id.question);
        ansA = view.findViewById(R.id.ansA);
        ansB = view.findViewById(R.id.ansB);
        ansC = view.findViewById(R.id.ansC);
        ansD = view.findViewById(R.id.ansD);
        btn_new = view.findViewById(R.id.btn_submit);
    }
    private void callApiGetQuestion(){
        ApiService.apiService.getQuestion().enqueue(new Callback<List<Vocabulary>>() {
            @Override
            public void onResponse(Call<List<Vocabulary>> call, Response<List<Vocabulary>> response) {
                List<Vocabulary> listVocab = response.body();
                vocabulary = listVocab.get(3);
                if(vocabulary.getImage() != null && !vocabulary.getImage().isEmpty()){
                    byte [] encodeByte = Base64.decode(vocabulary.getImage(),Base64.DEFAULT);
                    Bitmap bitmap1 = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                    img_ques.setImageBitmap(Bitmap.createBitmap(bitmap1));
                }
                else img_ques.setImageResource(R.drawable.upload);
                question.setText(vocabulary.getMeaning() + " là gì?");
                List<String> ans = new ArrayList<>();
                for(Vocabulary v : listVocab){
                    ans.add(v.getWord());
                }
                for(String v : ans){
                    Log.e("checkAns",v);
                }
                Collections.shuffle(ans);

                for (int i = 0; i < 4; i++) {
                    if(i == 0){
                        ansA.setText("  A. " + ans.get(i));
                    }
                    if(i == 1){
                        ansB.setText("  B. " + ans.get(i));
                    }
                    if(i == 2){
                        ansC.setText("  C. " + ans.get(i));
                    }
                    if(i == 3){
                        ansD.setText("  D. " + ans.get(i));
                    }
                }
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Vocabulary>> call, Throwable throwable) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        if(view == btn_new){
            mProgressDialog.show();
            callApiGetQuestion();
            ansA.setBackgroundResource(R.drawable.text_radius);
            ansB.setBackgroundResource(R.drawable.text_radius);
            ansC.setBackgroundResource(R.drawable.text_radius);
            ansD.setBackgroundResource(R.drawable.text_radius);
            ansA.setEnabled(true);
            ansB.setEnabled(true);
            ansC.setEnabled(true);
            ansD.setEnabled(true);
        }
        if(view == ansA){
            String ans = ansA.getText().toString().substring(5);
            System.out.println(ans);
            ansB.setEnabled(false);
            ansC.setEnabled(false);
            ansD.setEnabled(false);
            if(ans.equalsIgnoreCase(vocabulary.getWord())){
                ansA.setBackgroundResource(R.drawable.text_radius_true);
            }
            else ansA.setBackgroundResource(R.drawable.text_radius_false);
        }
        if(view == ansB){
            String ans = ansB.getText().toString().substring(5);
            ansA.setEnabled(false);
            ansC.setEnabled(false);
            ansD.setEnabled(false);
            if(ans.equalsIgnoreCase(vocabulary.getWord())){
                ansB.setBackgroundResource(R.drawable.text_radius_true);
            }
            else ansB.setBackgroundResource(R.drawable.text_radius_false);
        }
        if(view == ansC){
            String ans = ansC.getText().toString().substring(5);
            ansB.setEnabled(false);
            ansA.setEnabled(false);
            ansD.setEnabled(false);
            if(ans.equalsIgnoreCase(vocabulary.getWord())){
                ansC.setBackgroundResource(R.drawable.text_radius_true);
            }
            else ansC.setBackgroundResource(R.drawable.text_radius_false);
        }
        if(view == ansD){
            String ans = ansD.getText().toString().substring(5);
            ansB.setEnabled(false);
            ansC.setEnabled(false);
            ansA.setEnabled(false);
            if(ans.equalsIgnoreCase(vocabulary.getWord())){
                ansD.setBackgroundResource(R.drawable.text_radius_true);
            }
            else ansD.setBackgroundResource(R.drawable.text_radius_false);
        }
    }
}
