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
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);

        sharedPreferences= this.getSharedPreferences("Check LogIn", Context.MODE_PRIVATE);

         editor = sharedPreferences.edit();

        tvLogOut = (TextView) findViewById(R.id.tvLogOut);
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear().commit();
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        editor.putBoolean("isLogIn", true);
        // Save.
        editor.apply();
        finish();
    }
}
