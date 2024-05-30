package com.example.btlmobile.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlmobile.MainActivity;
import com.example.btlmobile.R;
import com.example.btlmobile.activity.AddActivity;
import com.example.btlmobile.activity.UpdateDeleteActivity;
import com.example.btlmobile.adapter.VocabularyAdapter;
import com.example.btlmobile.api.ApiService;
import com.example.btlmobile.model.Vocabulary;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Search  extends Fragment implements VocabularyAdapter.ItemVocaListener {
    private RecyclerView recyclerView;
    private SearchView searchView;
    private FloatingActionButton fab;
    private VocabularyAdapter adapter;
    private List<Vocabulary> listVoca;
    private ProgressDialog mProgressDialog;
    public Fragment_Search() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Loading...");
        adapter = new VocabularyAdapter();
        listVoca = new ArrayList<>();
        callApiGetByWord("");
        adapter.setListVoca(listVoca);
        recyclerView.setAdapter(adapter);
        adapter.setItemVocaListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callApiGetByWord(newText);
                return true;
            }
        });
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        searchView = view.findViewById(R.id.search_view);
        fab = view.findViewById(R.id.fap);
    }
    private void callApiGetByWord(String str){
        ApiService.apiService.findByWord(str).enqueue(new Callback<List<Vocabulary>>() {
            @Override
            public void onResponse(Call<List<Vocabulary>> call, Response<List<Vocabulary>> response) {
                listVoca = response.body();
                adapter.setListVoca(listVoca);
//                for(Vocabulary v: listVoca){
//                    System.out.println(v.toString());
//                }
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Vocabulary>> call, Throwable throwable) {
                Toast.makeText(getContext(),"call API Error",Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        mProgressDialog.show();
        callApiGetByWord("");
        super.onResume();
    }

    @Override
    public void onItemVocaClick(View view, int position) {
        Vocabulary vocabulary = adapter.getVocab(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("idVocab",vocabulary.getId());
        startActivity(intent);
    }
}
