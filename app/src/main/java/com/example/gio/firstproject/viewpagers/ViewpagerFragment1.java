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

public class ViewpagerFragment1 extends Fragment {


    public ViewpagerFragment1() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment_1, container, false);

        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ViewpagerFragment1ListNote viewpagerFragment1ListNote = new ViewpagerFragment1ListNote();
        fragmentTransaction.replace(R.id.frameListNote, viewpagerFragment1ListNote);
        fragmentTransaction.commit();


        return view;

    }
}
