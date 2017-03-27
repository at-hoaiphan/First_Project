package com.example.gio.firstproject.viewpagers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gio.firstproject.R;

/**
 * Created by Gio on 3/23/2017.
 */

public class ViewPagerFragment extends Fragment {


    public ViewPagerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment, container, false);
        return view;

    }
}
