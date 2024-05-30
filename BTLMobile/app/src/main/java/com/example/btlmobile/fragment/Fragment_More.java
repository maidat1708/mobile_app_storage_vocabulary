package com.example.btlmobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btlmobile.R;
import com.example.btlmobile.activity.AddActivity;
import com.example.btlmobile.activity.StatisticActivity;

public class Fragment_More extends Fragment {

    private TextView textview_statistics, textview_support;
    private Button btn_exit;
    public Fragment_More() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more,container,false);
        textview_statistics = view.findViewById(R.id.textview_statistics);
        textview_support = view.findViewById(R.id.textview_support);
        btn_exit = view.findViewById(R.id.button_exit);

        textview_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), StatisticActivity.class);
                startActivity(intent);
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                System.exit(0);
            }
        });
        return view;
    }
}
