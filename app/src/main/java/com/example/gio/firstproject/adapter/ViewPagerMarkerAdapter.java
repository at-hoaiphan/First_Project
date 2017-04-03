package com.example.gio.firstproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.gio.firstproject.viewpagers.ViewPagerMarker;

/**
 * Copyright by Gio.
 * Created on 4/3/2017.
 */

public class ViewPagerMarkerAdapter extends FragmentStatePagerAdapter{

    private int count = 10;

    public ViewPagerMarkerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ViewPagerMarker().getPosition(position);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return String.valueOf(position + 1);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
