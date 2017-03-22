package com.example.gio.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio.firstproject.R;

/**
 * Created by Gio on 3/7/2017.
 */

public class LoginScreenActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private EditText edtName;
    private EditText edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        TextView tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(this);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        edtName = (EditText) findViewById(R.id.edtName);
        edtName.setOnClickListener(this);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword.setOnClickListener(this);
        ImageButton imgBtnShow = (ImageButton) findViewById(R.id.imgBtnShow);
        imgBtnShow.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignUp:
                // Navigate to Register screen
                Intent i = new Intent(this, RegisterScreenActivity.class);
                startActivity(i);
                break;
            case R.id.btnLogin:
                // Get value of Username and Password
                String strName = edtName.getText().toString();
                String strPassword = edtPassword.getText().toString();
                Toast.makeText(LoginScreenActivity.this, "Usr: " + strName + "---" + "Pw: " + strPassword, Toast.LENGTH_SHORT).show();
                break;
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

