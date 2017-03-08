package com.example.gio.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Gio on 3/8/2017.
 */

public class RegisterScreen extends AppCompatActivity implements View.OnClickListener {

    Button btnGotID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        btnGotID = (Button) findViewById(R.id.btnGotID);
        btnGotID.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnGotID:
                startActivity(new Intent(this, LoginScreen.class));
        }
    }
}
