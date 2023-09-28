package com.umrani.tool.view.main;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class MyPagerAdapter extends FragmentStatePagerAdapter {
    Activity activity;

    @Override
    public int getCount() {
        return 8;
    }

    public MyPagerAdapter(FragmentManager fragmentManager, Activity activity2) {
        super(fragmentManager);
        this.activity = activity2;
    }

    @Override
    public Fragment getItem(int i) {
        return SpecialKeyboardFragment.newInstance(SpecialTextUtil.getStringList(this.activity).get(i), i);
    }
}