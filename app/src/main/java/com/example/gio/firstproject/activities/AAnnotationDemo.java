package com.example.gio.firstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 4/10/2017.
 */
@EActivity(R.layout.annotation_demo)
public class AAnnotationDemo extends AppCompatActivity {
    @ViewById(R.id.tvAAnnotaion)
    TextView tvAAnnotation;

    @ViewById
    Button btnAA;

    @AfterViews
    void updateText() {
        tvAAnnotation.setText("before click");
    }

    @Click(R.id.btnAA)
    void clickBtnAA() {
        tvAAnnotation.setText("clicked");
    }
}
