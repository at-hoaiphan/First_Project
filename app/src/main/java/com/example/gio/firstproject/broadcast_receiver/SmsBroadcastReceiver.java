package com.example.gio.firstproject.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio.firstproject.R;
import com.squareup.picasso.Picasso;

/**
 * Copyright by Gio.
 * Created on 3/28/2017.
 */

public class SmsBroadcastReceiver extends AppCompatActivity implements View.OnClickListener {
    private static final int SELECT_PICTURE = 7;
    private TextView textView;
    private ImageView imgChoosen;
    private Uri selectedImageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_receiver);

        textView = (TextView) findViewById(R.id.textView);
        Button btnToast = (Button) findViewById(R.id.btnSendTo);
        imgChoosen = (ImageView) findViewById(R.id.imgChoosen);
        imgChoosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
                Picasso.with(getBaseContext()).load(selectedImageUri).into(imgChoosen);
            }
        });
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("MyCustom");
                EditText edt = (EditText) findViewById(R.id.edt);
                intent.putExtra("message", edt.getText());
                if (selectedImageUri != null) {
                    intent.putExtra("uri", selectedImageUri.toString());
                }
                intent.setAction("com.gio.android.A_CUSTOM_INTENT");
                sendBroadcast(intent);
            }
        });

        // Power Status
        IntentFilter filter = new IntentFilter();
        // Register event POWER
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");

        BroadcastReceiver receiver = new BroadcastReceiver() {

            // Phương thức này sẽ được hệ thống gọi khi nhận được sự kiện đang sạc pin
            @Override
            public void onReceive(Context context, Intent intent) {
                // Event: Power connected
                if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                    textView.setText(" Đang sạc pin");
                }
                // Event: Power disconnected
                if (intent.getAction().endsWith(Intent.ACTION_POWER_DISCONNECTED)) {
                    textView.setText(" Đã rút sạc");
                }
            }
        };
        // Register to receiver data to system
        registerReceiver(receiver, filter);

        // Sms Receiver
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction("android.provider.Telephony.SMS_RECEIVED");
        BroadcastReceiver receiver2 = new BroadcastReceiver() {

            // Phương thức này sẽ được hệ thống gọi khi nhận được sự kiện đang sạc pin
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Có 1 tin nhắn mới", Toast.LENGTH_LONG).show();
                // pdus to get message package
                String sms_extra = "pdus";
                Bundle bundle = intent.getExtras();
                // Bundle return message packages at same time
                Object[] objArr = (Object[]) bundle.get(sms_extra);
                String sms = "";
                // Loop for reading message
                assert objArr != null;
                for (Object anObjArr : objArr) {
                    // Transferr to createFromPdu message
                    SmsMessage smsMsg = SmsMessage.createFromPdu((byte[]) anObjArr);
                    // Get body message
                    String body = smsMsg.getMessageBody();
                    // Get number-sender
                    String address = smsMsg.getDisplayOriginatingAddress();
                    sms += address + ":\n" + body + "\n";
                }
                textView.setText(sms);
            }
        };
        // Register to receiver data to system
        registerReceiver(receiver2, filter2);
    }

    @Override
    public void onClick(View v) {
    }

    /* Choose an image from Gallery */
    public void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Gallery request
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            // Get the url from data
            selectedImageUri = data.getData();
            // Set the image in ImageView
            imgChoosen.setImageURI(selectedImageUri);
        }
    }
}
