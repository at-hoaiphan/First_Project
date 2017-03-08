package com.example.gio.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Gio on 3/7/2017.
 */

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button btnSignUp, btnLogin;
    ImageButton ibtnShow;
    EditText edtName, edtPassword;
    String name, pw;
    int show = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        edtName = (EditText) findViewById(R.id.edtName);
        edtName.setOnClickListener(this);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword.setOnClickListener(this);
        ibtnShow = (ImageButton) findViewById(R.id.ibtnShow);
        ibtnShow.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                Intent i = new Intent(this, RegisterScreen.class);
                startActivity(i);
                break;
            case R.id.btnLogin:
                name = edtName.getText().toString();
                pw = edtPassword.getText().toString();
                Toast.makeText(LoginScreen.this, "Usr: " + name + "---" + "Pw: " + pw, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreate: " + name);
                break;
            case R.id.ibtnShow:
                if (show == 1) {
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    show = 0;
                } else {

                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    show = 1;
                }


                edtPassword.setSelection(edtPassword.length());
                break;
        }

    }
}

