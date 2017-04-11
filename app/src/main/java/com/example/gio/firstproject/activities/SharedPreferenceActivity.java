package com.example.gio.firstproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 3/20/2017.
 */
@EActivity(R.layout.activity_shared_preference_setting)
public class SharedPreferenceActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewById(R.id.skSound)
    SeekBar skSound;
    @ViewById(R.id.skBrightness)
    SeekBar skBrightness;
    @ViewById(R.id.rgDifficulty)
    RadioGroup rgDifficulty;
    @ViewById(R.id.btnSave)
    Button btnSave;

    @AfterViews
    void afterViews() {
        // Load settings last time
        this.loadGameSettings();
    }

    private void loadGameSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences("gameSetting", Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            int brightness = sharedPreferences.getInt("brightness", R.id.skBrightness);
            int sound = sharedPreferences.getInt("sound", R.id.skSound);
            int checkedRadioButtonId = sharedPreferences.getInt("checkedRadioButtonId", R.id.rbNormal);

            skSound.setProgress(sound);
            skBrightness.setProgress(brightness);
            rgDifficulty.check(checkedRadioButtonId);
        } else {
            rgDifficulty.check(R.id.rbNormal);
            Toast.makeText(this, "Use default game settings.", Toast.LENGTH_LONG).show();
        }
    }

    // Called when user press Save button
    private void saveGameSettings() {
        // Shared file used for internal application, or shared applications in same User.
        // Shared file used for app,
        SharedPreferences sharedPreferences = this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("brightness", this.skBrightness.getProgress());
        editor.putInt("sound", this.skSound.getProgress());
        // ID của RadioButton đang được chọn.
        int checkedRadioButtonId = rgDifficulty.getCheckedRadioButtonId();

        editor.putInt("checkedRadioButtonId", checkedRadioButtonId);

        // Save.
        editor.apply();

        Toast.makeText(this, "Game Setting saved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        saveGameSettings();
    }
}

