package com.example.gio.firstproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.gio.firstproject.R;

/**
 * Created by Gio on 3/20/2017.
 */

public class LogInSuccessActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvLogOut;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        SharedPreferences sharedPreferences = this.getSharedPreferences("Check LogIn", Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        tvLogOut = (TextView) findViewById(R.id.tvLogOut);
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear().commit();
//                editor.remove("isLogIn").commit();
                finish();
            }
        });


        editor.putBoolean("isLogIn", true);
        // Save.
        editor.apply();
    }

    @Override
    public void onClick(View v) {

    }

//    @Override
//    public void onBackPressed() {
//        editor.putBoolean("isLogIn", true);
//        // Save.
//        editor.apply();
//        finish();
//    }
}
