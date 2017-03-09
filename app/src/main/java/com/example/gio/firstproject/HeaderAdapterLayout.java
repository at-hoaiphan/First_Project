package com.example.gio.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Gio on 3/9/2017.
 */

public class HeaderAdapterLayout extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgBtnBack, imgBtnSettings;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_information);

        imgBtnBack = (ImageButton) findViewById(R.id.imgBtnBack);
        imgBtnBack.setOnClickListener(this);
        imgBtnSettings = (ImageButton) findViewById(R.id.imgBtnSettings);
        imgBtnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imgBtnSettings:
                //Navigate to Information Layout  Screen
                startActivity(new Intent(this, InformationLayout.class));
                break;
            case  R.id.imgBtnBack:
                finish();
        }
    }
}
