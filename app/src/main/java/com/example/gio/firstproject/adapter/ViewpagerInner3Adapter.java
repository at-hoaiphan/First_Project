package com.example.gio.firstproject.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.viewpagers.ViewPagerFragment3Item;

/**
 * Created by Gio on 3/23/2017.
 */

public class ViewPagerInner3Adapter extends FragmentStatePagerAdapter {

    private MyDatabaseHelper mMyDatabaseHelper;

    public ViewPagerInner3Adapter(FragmentManager fm, Context context) {
        super(fm);
        mMyDatabaseHelper = new MyDatabaseHelper(context);
    }

    @Override
    public Fragment getItem(int position) {
        return new ViewPagerFragment3Item().getPosition(position);
    }

    @Override
    public int getCount() {
        return mMyDatabaseHelper.getNotesCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return String.valueOf(position + 1);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}

