package com.example.gio.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Gio on 3/7/2017.
 */

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button btnSignUp, btnLogin;
    EditText edtName, edtPassword;
    String name, pw;

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
                Toast.makeText(LoginScreen.this, "Usr: "+ name +"---" + "Pw: " + pw, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onCreate: " + name);

        }
    }
}
