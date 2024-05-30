package com.example.btlmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.btlmobile.R;
import com.example.btlmobile.practice.PracticeAdapter;
import com.google.android.material.tabs.TabLayout;

public class Fragment_Practice extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public Fragment_Practice() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice,container,false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        PracticeAdapter adapter = new PracticeAdapter(getChildFragmentManager(),2);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
