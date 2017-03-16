package com.example.gio.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.gio.firstproject.screen_layout.LoginScreenActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLoginPage, btnLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoginPage = (Button) findViewById(R.id.btnLoginPage);
        btnLoginPage.setOnClickListener(this);
        btnLayout = (Button) findViewById(R.id.btnLayout);
        btnLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLoginPage:
                //Navigate to Login  Screen
                Intent i = new Intent(MainActivity.this, LoginScreenActivity.class);
                startActivity(i);
                break;
            case R.id.btnLayout:
                //Navigate to Header Information Layout  Screen
                Intent i2 = new Intent(this, HeaderInformationLayout.class);
                startActivity(i2);
                break;
        }

    }
}
