package com.example.gio.firstproject.fragmentdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gio.firstproject.R;

/**
 * Created by Gio on 3/22/2017.
 */

public class FragmentDemoActivity extends AppCompatActivity {
    boolean orientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        /**
         * Check the device orientation and act accordingly
         */
        orientation = getResources().getBoolean(R.bool.orientation);
        if (orientation) {
            /**
             * Landscape mode of the device
             */
            LsFragment ls_fragment = new LsFragment();
            fragmentTransaction.replace(android.R.id.content, ls_fragment);
//            fragmentTransaction.addToBackStack("0");
        } else {
            /**
             * Portrait mode of the device
             */
            PtFragment pm_fragment = new PtFragment();
            fragmentTransaction.replace(android.R.id.content, pm_fragment);
//            fragmentTransaction.addToBackStack("0");
        }
        fragmentTransaction.commit();
    }
}
