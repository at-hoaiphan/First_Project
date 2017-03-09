package com.example.gio.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gio on 3/7/2017.
 */

public class LoginScreen extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button btnLogin;
    TextView tvSignUp;
    ImageButton imgBtnShow;
    EditText edtName, edtPassword;
    String strName, strPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(this);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        edtName = (EditText) findViewById(R.id.edtName);
        edtName.setOnClickListener(this);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword.setOnClickListener(this);
        imgBtnShow = (ImageButton) findViewById(R.id.imgBtnShow);
        imgBtnShow.setOnTouchListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSignUp:
                // Navigate to Register screen
                Intent i = new Intent(this, RegisterScreen.class);
                startActivity(i);
                break;
            case R.id.btnLogin:
                // Get value of Username and Password
                strName = edtName.getText().toString();
                strPassword = edtPassword.getText().toString();
                Toast.makeText(LoginScreen.this, "Usr: " + strName + "---" + "Pw: " + strPassword, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreate: " + strName);
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

