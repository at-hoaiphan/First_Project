package com.example.gio.firstproject.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gio.firstproject.R;

/**
 * Copyright by Gio.
 * Created on 3/23/2017.
 */

public class BottomFragmentDemo2 extends Fragment {

    private TextView topTextName;
    private TextView bottomTextAge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Đọc file xml tạo ra đối tượng View.

        // inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
        View view = inflater.inflate(R.layout.activity_fragment_bottom, container, false);

        topTextName = (TextView) view.findViewById(R.id.tvName);
        bottomTextAge = (TextView) view.findViewById(R.id.tvAge);

        return view;
    }


    public void showText(String topImageText, String bottomImageText) {
        topTextName.setText(topImageText);
        bottomTextAge.setText(bottomImageText);
    }
}