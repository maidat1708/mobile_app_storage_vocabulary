package com.example.btlmobile.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.btlmobile.fragment.Fragment_More;
import com.example.btlmobile.fragment.Fragment_Practice;
import com.example.btlmobile.fragment.Fragment_Search;
import com.example.btlmobile.fragment.Fragment_Translate;

public class ViewPagerApdater extends FragmentStatePagerAdapter {


    public ViewPagerApdater(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Fragment_Search();
            case 1: return new Fragment_Practice();
            case 2: return new Fragment_Translate();
            case 3: return new Fragment_More();
        }
        return new Fragment_Search();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
