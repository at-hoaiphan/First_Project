package com.example.gio.firstproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 3/9/2017.
 */

@EActivity(R.layout.activity_header_information)
public class HeaderInformationLayoutActivity extends AppCompatActivity {

    @ViewById(R.id.imgBtnBack)
    ImageButton imgBtnBack;

    @ViewById(R.id.imgBtnSettings)
    ImageButton imgBtnSettings;

    @Click(R.id.imgBtnSettings)
    void clickBtnSettings() {
        // Navigate to ListUser  Screen
        startActivity(new Intent(this, ListUserActivity.class));
    }

    @Click(R.id.imgBtnBack)
    void clickImgBtnBack() {
        finish();
    }
}
