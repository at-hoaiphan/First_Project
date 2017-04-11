package com.example.gio.firstproject.fragmentdemo;

import android.support.v7.app.AppCompatActivity;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.EActivity;

/**
 * Copyright by Gio.
 * Created on 3/23/2017.
 */
@EActivity(R.layout.activity_fragment_main)
public class FragmentMainDemo2 extends AppCompatActivity {
    public void showText(String topImageText, String bottomImageText) {
        BottomFragmentDemo2 bottomFragment
                = (BottomFragmentDemo2) this.getSupportFragmentManager().findFragmentById(R.id.bottom_fragment);
        bottomFragment.showText(topImageText, bottomImageText);
    }
}
