package com.example.gio.firstproject.fragmentdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gio.firstproject.R;

/**
 * Created by Gio on 3/23/2017.
 */

public class FragmentMainDemo2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
    }


    public void showText(String topImageText, String bottomImageText) {
        BottomFragmentDemo2 bottomFragment
                = (BottomFragmentDemo2) this.getSupportFragmentManager().findFragmentById(R.id.bottom_fragment);
        bottomFragment.showText(topImageText, bottomImageText);
    }
}
