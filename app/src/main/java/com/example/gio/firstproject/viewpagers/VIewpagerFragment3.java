package com.example.gio.firstproject.viewpagers;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.ViewpagerInner3Adapter;

/**
 * Created by Gio on 3/23/2017.
 */

public class ViewpagerFragment3 extends Fragment {

    private ViewpagerInner3Adapter mAdapter;
    public ViewpagerFragment3() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment_3, container, false);

        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.view_pager3);
        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout3);

        mAdapter = new ViewpagerInner3Adapter(getChildFragmentManager(), view.getContext());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        return view;
    }
}
