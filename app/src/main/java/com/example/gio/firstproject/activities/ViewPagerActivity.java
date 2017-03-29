package com.example.gio.firstproject.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.ViewpagerAdapter;

/**
 * Copyright by Gio.
 * Created on 3/23/2017.
 */

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_fragment);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        FragmentManager manager = getSupportFragmentManager();
        ViewpagerAdapter adapter = new ViewpagerAdapter(manager);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }
}