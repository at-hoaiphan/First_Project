package com.example.gio.firstproject.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gio.firstproject.R;

public class Test extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_demo);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

    public void playAudio(View view) {
        Intent objIntent = new Intent(this, PlayAudio.class);
        Toast.makeText(this, "Play audio...", Toast.LENGTH_LONG).show();
        startService(objIntent);
    }

    public void stopAudio(View view) {
        Intent objIntent = new Intent(this, PlayAudio.class);
        Toast.makeText(this, "Stop audio.", Toast.LENGTH_LONG).show();
        stopService(objIntent);
    }
}
