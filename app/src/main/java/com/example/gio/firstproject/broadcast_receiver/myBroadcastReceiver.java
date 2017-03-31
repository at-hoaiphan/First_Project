package com.example.gio.firstproject.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Gio on 3/28/2017.
 * Copyright by Gio.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        // Extract data included in the Intent
//        CharSequence intentData = intent.getCharSequenceExtra("message");
//        if (intentData != null) {
//            Toast.makeText(context, "The Intent's message: " + intentData, Toast.LENGTH_LONG).show();
//        }

        // Received from app2
        CharSequence intentData = intent.getCharSequenceExtra("fromApp2");
        Log.d("Received from app 2", "onReceive: " + intent.getCharSequenceExtra("fromApp2") + intentData);
        Toast.makeText(context, intent.getCharSequenceExtra("fromApp2"), Toast.LENGTH_SHORT).show();


//        // Internet State
//        String connectivityStatus = "Not connected to Internet";
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        if (null != activeNetwork) {
//            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
//                connectivityStatus = "Wifi enabled";
//            }
//            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//                connectivityStatus = "Mobile data enabled";
//            }
//        }
//        Toast.makeText(context, connectivityStatus, Toast.LENGTH_SHORT).show();
    }
}
