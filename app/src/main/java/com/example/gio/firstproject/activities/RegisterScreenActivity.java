package com.example.gio.firstproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gio.firstproject.R;

/**
 * Copyright by Gio.
 * Created on 3/8/2017.
 */

public class RegisterScreenActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private EditText edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        TextView tvHadUsername = (TextView) findViewById(R.id.tvHadUsername);
        tvHadUsername.setOnClickListener(this);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword.setOnClickListener(this);
        ImageButton imgBtnShow = (ImageButton) findViewById(R.id.imgBtnShow);
        imgBtnShow.setOnTouchListener(this);
    }

    //Navigate to Login page
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvHadUsername:
                finish();
        }
    }

    //Set event when handling see_password button
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        return true;
    }

}
