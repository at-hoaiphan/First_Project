package com.example.gio.firstproject.viewpagers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.ViewpagerInnerAdapter;

/**
 * Created by Gio on 3/23/2017.
 */

public class ViewpagerFragment2 extends Fragment {

    //    private AnimatedGifImageView animatedGifImageView;
    private ViewPager pager;
    private TabLayout tabLayout;

    public ViewpagerFragment2() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment_2, container, false);

//        animatedGifImageView = ((AnimatedGifImageView)findViewById(R.id.animatedGifImageView));
//        animatedGifImageView.setAnimatedGif(R.raw.animated_gif,
//                TYPE.FIT_CENTER);
        pager = (ViewPager) view.findViewById(R.id.view_pager2);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout2);

        FragmentManager manager = getChildFragmentManager();
        ViewpagerInnerAdapter adapter = new ViewpagerInnerAdapter(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);


        return view;
    }
}
