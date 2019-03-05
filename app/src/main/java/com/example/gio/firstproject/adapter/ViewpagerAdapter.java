package com.example.gio.firstproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.gio.firstproject.viewpagers.ViewpagerFragment1;
import com.example.gio.firstproject.viewpagers.VIewpagerFragment2;
import com.example.gio.firstproject.viewpagers.VIewpagerFragment3;

/**
 * Copyright by Gio.
 * Created on 3/23/2017.
 */

public class ViewpagerAdapter extends FragmentStatePagerAdapter {
    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new ViewpagerFragment1();
                break;
            case 1:
                frag = new VIewpagerFragment2();
                break;
            case 2:
                frag = new VIewpagerFragment3();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "List";
                break;
            case 1:
                title = "Page 2";
                break;
            case 2:
                title = "Page 3";
                break;
        }

        return title;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
