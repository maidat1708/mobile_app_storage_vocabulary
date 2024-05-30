package com.example.btlmobile.practice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PracticeAdapter  extends FragmentStatePagerAdapter {


    public PracticeAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Fragment_LearnWord();
            case 1: return  new Fragment_SortWord();
            case 2: return  new Fragment_SelectAnswer();
        }
        return new Fragment_LearnWord();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Learn Word";
            case 1: return  "Sort Word";
            case 2: return "Select Answer";
        }
        return "Learn Word";
    }
}
