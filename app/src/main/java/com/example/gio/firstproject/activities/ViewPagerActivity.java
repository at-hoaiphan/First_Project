package com.example.gio.firstproject.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.ViewpagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 3/23/2017.
 */
@EActivity(R.layout.activity_viewpager_fragment)
public class ViewPagerActivity extends AppCompatActivity {
    @ViewById(R.id.view_pager)
    ViewPager mViewPager;
    @ViewById(R.id.tab_layout)
    TabLayout mTabLayout;

    @AfterViews
    void afterViews() {
        FragmentManager manager = getSupportFragmentManager();
        ViewpagerAdapter adapter = new ViewpagerAdapter(manager);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }
}