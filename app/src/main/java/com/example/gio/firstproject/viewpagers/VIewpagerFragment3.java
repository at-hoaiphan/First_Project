package com.example.gio.firstproject.viewpagers;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.ViewpagerInner3Adapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 3/23/2017.
 */
@EFragment(R.layout.viewpager_fragment_3)
public class ViewpagerFragment3 extends Fragment {

    @ViewById(R.id.view_pager3)
    ViewPager mViewPager;
    @ViewById(R.id.tab_layout3)
    TabLayout mTabLayout;

    public ViewpagerFragment3() {
    }

    @AfterViews
    void afterViews() {
        ViewpagerInner3Adapter mAdapter = new ViewpagerInner3Adapter(getChildFragmentManager(), this.getContext());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.viewpager_fragment_3, container, false);
//
//        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.view_pager3);
//        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout3);
//
//        mAdapter = new ViewpagerInner3Adapter(getChildFragmentManager(), view.getContext());
//        mViewPager.setAdapter(mAdapter);
//        mTabLayout.setupWithViewPager(mViewPager);
//        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//        mTabLayout.setTabsFromPagerAdapter(mAdapter);
//        return view;
//    }
}
