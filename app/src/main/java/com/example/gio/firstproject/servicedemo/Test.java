package com.example.gio.firstproject.servicedemo;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.service_demo)
public class Test extends Activity {

    public void playAudio(View view) {
        Toast.makeText(this, "Play audio...", Toast.LENGTH_LONG).show();
        PlayAudio_.intent(this).start();
    }

    public void stopAudio(View view) {
        Toast.makeText(this, "Stop audio.", Toast.LENGTH_LONG).show();
        PlayAudio_.intent(this).stop();
    }
}
