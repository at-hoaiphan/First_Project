package com.example.gio.firstproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 3/20/2017.
 */
@EActivity(R.layout.activity_login_success)
public class LogInSuccessActivity extends AppCompatActivity {

    @ViewById(R.id.tvLogOut)
    TextView tvLogOut;

    private SharedPreferences.Editor editor;

    @AfterViews
    void afterViews() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("Check LogIn", Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();

        editor.putBoolean("isLogIn", true);
        // Save.
        editor.apply();
    }

    @Click(R.id.tvLogOut)
    void clickTvLogOut() {
        editor.clear().commit();
        finish();
    }
}
