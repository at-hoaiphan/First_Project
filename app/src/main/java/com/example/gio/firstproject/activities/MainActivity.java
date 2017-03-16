package com.example.gio.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gio.firstproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLoginPage, btnLayout, btnIntentFilter;
    ImageButton imgBtnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoginPage = (Button) findViewById(R.id.btnLoginPage);
        btnLoginPage.setOnClickListener(this);
        btnLayout = (Button) findViewById(R.id.btnLayout);
        btnLayout.setOnClickListener(this);
        imgBtnCall = (ImageButton) findViewById(R.id.imgBtnCall);
        imgBtnCall.setOnClickListener(this);
        btnIntentFilter = (Button) findViewById(R.id.btnIntentFilter);
        btnIntentFilter.setOnClickListener(this);
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
                Log.d("click btnLayout", "onClick: ");
                Intent i2 = new Intent(this, HeaderInformationLayoutActivity.class);
                startActivity(i2);
                break;
            case R.id.imgBtnCall:
                //Navigate to Header PhoneCallActvity Layout  Screen
                Intent intentCall = new Intent(this, PhoneCallActivity.class);
                startActivity(intentCall);
                break;
            case R.id.btnIntentFilter:
                //Navigate to Header PhoneCallActvity Layout  Screen
                Log.d("click imgBtnDial", "onClick: ");
                Intent intentFilter = new Intent(this, IntentFilterActivity.class);
                startActivity(intentFilter);
                break;
        }

    }
}
