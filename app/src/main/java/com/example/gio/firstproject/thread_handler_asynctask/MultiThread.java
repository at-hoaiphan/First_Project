package com.example.gio.firstproject.thread_handler_asynctask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.gio.firstproject.R;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright by Gio.
 * Created on 4/4/2017.
 */

public class MultiThread extends AppCompatActivity {

    private LinearLayout linearLayout;
    private EditText edtNumber;
    private Button btnDraw;
    private Handler handler;
    private AtomicBoolean atomic = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread_demo);

        linearLayout = (LinearLayout) findViewById(R.id.llListButton);
        edtNumber = (EditText) findViewById(R.id.edtNumberThread);
        btnDraw = (Button) findViewById(R.id.btnThreadDraw);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                // Get label of sent button from subThread
                String number_Button = msg.obj.toString();
                // Create a button
                Button button = new Button(MultiThread.this);
                // Make text for button
                button.setText(number_Button);
                // Design button: width, height
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                // Layout for Button
                button.setLayoutParams(params);
                // Add button to layout
                linearLayout.addView(button);
            }
        };

        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawActor();
            }
        });
    }

    private void drawActor() {
        atomic = new AtomicBoolean(false);
        final int numberButton = Integer.parseInt(edtNumber.getText().toString());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < numberButton && atomic.get(); i++) {
                    // Delay 200ms
                    SystemClock.sleep(200);
                    // Take message from Main Thread
                    Message message = handler.obtainMessage();
                    // Assign data to msg MainThread, save to device
                    // Caution: We can save every data type for obj
                    message.obj = "Actor " + i;
                    // Send back message to mainthread
                    handler.sendMessage(message);
                }
            }
        });
        atomic.set(true);
        // Start Thread
        thread.start();
    }
}
