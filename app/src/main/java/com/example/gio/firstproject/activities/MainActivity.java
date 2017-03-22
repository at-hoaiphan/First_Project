package com.example.gio.firstproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gio.firstproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLoginPage = (Button) findViewById(R.id.btnLoginPage);
        btnLoginPage.setOnClickListener(this);
        Button btnLayout = (Button) findViewById(R.id.btnLayout);
        btnLayout.setOnClickListener(this);
        ImageButton imgBtnCall = (ImageButton) findViewById(R.id.imgBtnCall);
        imgBtnCall.setOnClickListener(this);
        Button btnIntentFilter = (Button) findViewById(R.id.btnIntentFilter);
        btnIntentFilter.setOnClickListener(this);
        Button btnSqlite = (Button) findViewById(R.id.btnSqlite);
        btnSqlite.setOnClickListener(this);
        Button btnSharedPre = (Button) findViewById(R.id.btnSharedPreference);
        btnSharedPre.setOnClickListener(this);
        Button btnStorage = (Button) findViewById(R.id.btnStorage);
        btnStorage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginPage:
                boolean isLogIn = false;
                SharedPreferences sharedPreferences = getSharedPreferences("Check LogIn", Context.MODE_PRIVATE);

                if (sharedPreferences != null) {
                    isLogIn = sharedPreferences.getBoolean("isLogIn", false);
                }
                if (isLogIn) {
                    startActivity(new Intent(this,LogInSuccessActivity.class));
                } else {
                    //Navigate to Login  Screen
                    Intent i = new Intent(MainActivity.this, LoginScreenActivity.class);
                    startActivity(i);
                }
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
            case R.id.btnSqlite:
                //Navigate to Header PhoneCallActvity Layout  Screen
                Log.d("click imgBtnDial", "onClick: ");
                Intent intentSqlite = new Intent(this, ListNoteActivity.class);
                startActivity(intentSqlite);
                break;
            case R.id.btnSharedPreference:
                //Navigate to Header PhoneCallActvity Layout  Screen
                Log.d("click imgBtnDial", "onClick: ");
                Intent intentSharedPre = new Intent(this, SharedPreferenceActivity.class);
                startActivity(intentSharedPre);
                break;
            case R.id.btnStorage:
                //Navigate to Header PhoneCallActvity Layout  Screen
                Log.d("click imgBtnDial", "onClick: ");
                Intent intentStorage = new Intent(this, InternalStorageActivity.class);
                startActivity(intentStorage);
                break;

        }
    }
}
