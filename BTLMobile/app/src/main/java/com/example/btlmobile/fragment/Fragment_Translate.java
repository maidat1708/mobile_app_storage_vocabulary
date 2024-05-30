package com.example.btlmobile.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlmobile.R;
import com.example.btlmobile.adapter.TranslationAdapter;
import com.example.btlmobile.api.ApiService;
import com.example.btlmobile.model.ContriesCode;
import com.example.btlmobile.model.Translate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Translate extends Fragment {

    private EditText editTextInput;
    private Spinner spinnerFromLang;
    private Spinner spinnerToLang;
    private Button buttonSwap;
    private Button buttonTranslate,buttonMore;
    private TextView textViewResult;
    private RecyclerView recyclerView;
    private TranslationAdapter adapter;
    private List<Translate> translateList;

    private ProgressDialog mProgressDialog;
    private ContriesCode contriesCode = new ContriesCode();
    public Fragment_Translate() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_translate, container, false);
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Loading...");
        // Initialize views
        editTextInput = rootView.findViewById(R.id.editTextInput);
        spinnerFromLang = rootView.findViewById(R.id.spinnerFromLang);
        spinnerToLang = rootView.findViewById(R.id.spinnerToLang);
        buttonSwap = rootView.findViewById(R.id.buttonSwap);
        buttonTranslate = rootView.findViewById(R.id.buttonTranslate);
        textViewResult = rootView.findViewById(R.id.textViewResult);
        buttonMore = rootView.findViewById(R.id.btn_more);
        translateList = new ArrayList<>();
        adapter = new TranslationAdapter();
        recyclerView = rootView.findViewById(R.id.recyclerView);
        callApiGetLast10();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        // Set up Spinner adapters
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromLang.setAdapter(adapter);
        spinnerToLang.setAdapter(adapter);
        spinnerToLang.setSelection(1);

        // Set up button click listeners
        buttonSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapLanguages();
            }
        });

        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateText();
            }
        });
        buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonMore.getText().toString().equalsIgnoreCase("thêm")){
                    callApiGetLast20();
                    buttonMore.setText("Ẩn bớt");
                }
                else{
                    callApiGetLast10();
                    buttonMore.setText("Thêm");
                }
            }
        });
        return rootView;
    }

    // Method to swap languages
    private void swapLanguages() {
        String tmp = editTextInput.getText().toString();
        editTextInput.setText(textViewResult.getText().toString());
        textViewResult.setText(tmp);
        int fromLangPosition = spinnerFromLang.getSelectedItemPosition();
        int toLangPosition = spinnerToLang.getSelectedItemPosition();
        spinnerFromLang.setSelection(toLangPosition);
        spinnerToLang.setSelection(fromLangPosition);
    }

    // Method to translate text
    private void translateText() {
        String inputText = editTextInput.getText().toString();
        String fromLang = spinnerFromLang.getSelectedItem().toString();
        String toLang = spinnerToLang.getSelectedItem().toString();
        Translate translate = new Translate(0,(String)contriesCode.getCountryCodeMap().get(fromLang),(String)contriesCode.getCountryCodeMap().get(toLang),inputText,"");
        System.out.println(translate.toString());
        mProgressDialog.show();
        callApiTranslate(translate);
    }

    private void callApiTranslate(Translate translate){
        ApiService.apiService.translate(translate).enqueue(new Callback<Translate>() {
            @Override
            public void onResponse(Call<Translate> call, Response<Translate> response) {
                textViewResult.setText(response.body().getTranslatedText());
                callApiGetLast10();
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Translate> call, Throwable throwable) {
                textViewResult.setText(throwable.getMessage());
                mProgressDialog.dismiss();
            }
        });
    }
    private void callApiGetLast10(){
        ApiService.apiService.getLast10().enqueue(new Callback<List<Translate>>() {
            @Override
            public void onResponse(Call<List<Translate>> call, Response<List<Translate>> response) {
                translateList = response.body();
                System.out.println(translateList.size());
                adapter = new TranslationAdapter(translateList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Translate>> call, Throwable throwable) {

            }
        });
    }

    private void callApiGetLast20(){
        ApiService.apiService.getLast20().enqueue(new Callback<List<Translate>>() {
            @Override
            public void onResponse(Call<List<Translate>> call, Response<List<Translate>> response) {
                translateList = response.body();
                adapter = new TranslationAdapter(translateList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Translate>> call, Throwable throwable) {

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        editTextInput.setText("");
        textViewResult.setText("");
        callApiGetLast10();
    }
}
