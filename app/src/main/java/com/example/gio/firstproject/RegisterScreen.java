package com.example.gio.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Gio on 3/8/2017.
 */

public class RegisterScreen extends AppCompatActivity implements View.OnClickListener{

    TextView tvHadUsername;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        tvHadUsername = (TextView) findViewById(R.id.tvHadUsername);
        tvHadUsername.setOnClickListener(this);
    }

    //Navigate to Login page
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tvHadUsername:
                startActivity(new Intent(this, LoginScreen.class));
        }
    }
}
