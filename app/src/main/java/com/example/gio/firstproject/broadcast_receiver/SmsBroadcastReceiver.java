package com.example.gio.firstproject.broadcast_receiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gio.firstproject.R;

/**
 * Created by Gio on 3/28/2017.
 */

public class SmsBroadcastReceiver extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_receiver);

        Button btnToast = (Button) findViewById(R.id.btnToast);
        btnToast.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("MyCustom");
        EditText edt = (EditText) findViewById(R.id.edt);
        intent.putExtra("message", edt.getText());
        intent.setAction("com.gio.android.A_CUSTOM_INTENT");
        sendBroadcast(intent);
    }
}
