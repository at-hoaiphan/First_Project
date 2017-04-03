package com.example.gio.firstproject.viewpagers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gio.firstproject.MockMarker;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.model.MyMarker;

import java.util.ArrayList;

/**
 * Copyright by Gio.
 * Created on 4/3/2017.
 */

public class ViewPagerMarker extends Fragment implements View.OnClickListener {
    private static final String TAG ="ViewPagerMarker" ;
    private TextView tvMarkerTitle;
    private TextView tvmarkerLongLat;
    private MyMarker mMyMarker;
    private ArrayList<MyMarker> mMyMarkers = new ArrayList<>();

    public ViewPagerMarker() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.google_map_detail_marker, container, false);

        tvMarkerTitle = (TextView) view.findViewById(R.id.tvMarkerTitle);
        tvmarkerLongLat = (TextView) view.findViewById(R.id.tvMarkerLongLat);
        int position = 0;

        if(getArguments()!=null){
            position = getArguments().getInt("positionFrag");
        }

        // Replace item on fragment
        mMyMarkers.addAll(MockMarker.getData());
        mMyMarker = mMyMarkers.get(position);
        tvMarkerTitle.setText(mMyMarker.getMarkerTitle());
        tvmarkerLongLat.setText(String.valueOf("Lat: " + mMyMarker.getMarkerLatitude())
                + "; Long: " + String.valueOf(mMyMarker.getMarkerLongitude()));

        return view;
    }

    public ViewPagerMarker getPosition(int position) {
        ViewPagerMarker fragment = new ViewPagerMarker();
        Bundle bundle = new Bundle();
        bundle.putInt("positionFrag", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onClick(View v) {

    }
}
