package com.example.gio.firstproject.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Gio on 3/28/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // TODO Auto-generated method stub
//         Extract data included in the Intent
        CharSequence intentData = intent.getCharSequenceExtra("message");
        if (intentData != null) {
            Toast.makeText(context, "The Intent's message: " + intentData, Toast.LENGTH_LONG).show();
        }

        // Internet State
        String connectivityStatus = "Not connected to Internet";
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                connectivityStatus = "Wifi enabled";
            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                connectivityStatus = "Mobile data enabled";
            }
        }
        Toast.makeText(context, connectivityStatus, Toast.LENGTH_SHORT).show();

        // Sms
        Bundle bundle = intent.getExtras();
        Log.d("MyBroadcast", "onReceive: ");
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdu");

            for (int i = 0; i < pdus.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

                String number = smsMessage.getOriginatingAddress();
                String message = smsMessage.getDisplayMessageBody();
                Toast.makeText(context, "From: " + number + "\nMessage: " + message, Toast.LENGTH_LONG).show();

            }
        }

    }
}
