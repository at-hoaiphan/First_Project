package com.example.gio.firstproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.broadcast_receiver.SmsBroadcastReceiver;
import com.example.gio.firstproject.fragmentdemo.FragmentDemoActivity;
import com.example.gio.firstproject.fragmentdemo.FragmentMainDemo2;
import com.example.gio.firstproject.servicedemo.Test;
import com.example.gio.firstproject.thread_handler_asynctask.DownloadImageActivity;
import com.example.gio.firstproject.thread_handler_asynctask.MultiThread;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLoginPage = (Button) findViewById(R.id.btnLoginPage);
        btnLoginPage.setOnClickListener(this);
        Button btnLayout = (Button) findViewById(R.id.btnLayout);
        btnLayout.setOnClickListener(this);
        ImageButton imgBtnCall = (ImageButton) findViewById(R.id.imgBtnCall);
        imgBtnCall.setOnClickListener(this);
        Button btnIntentFilter = (Button) findViewById(R.id.btnIntentFilter);
        btnIntentFilter.setOnClickListener(this);
        Button btnSqlite = (Button) findViewById(R.id.btnSqlite);
        btnSqlite.setOnClickListener(this);
        Button btnSharedPre = (Button) findViewById(R.id.btnSharedPreference);
        btnSharedPre.setOnClickListener(this);
        Button btnStorage = (Button) findViewById(R.id.btnStorage);
        btnStorage.setOnClickListener(this);
        Button btnFragment = (Button) findViewById(R.id.btnFragment);
        btnFragment.setOnClickListener(this);
        Button btnFragmentDemo2 = (Button) findViewById(R.id.btnFragmentDemo2);
        btnFragmentDemo2.setOnClickListener(this);
        Button btnViewpager = (Button) findViewById(R.id.btnViewpager);
        btnViewpager.setOnClickListener(this);
        Button btnService = (Button) findViewById(R.id.btnService);
        btnService.setOnClickListener(this);
        Button btnBroadcast = (Button) findViewById(R.id.btnBroadcastReceiver);
        btnBroadcast.setOnClickListener(this);
        Button btnUI = (Button) findViewById(R.id.btnUI);
        btnUI.setOnClickListener(this);
        Button btnGoogleMap = (Button) findViewById(R.id.btnGoogleMap);
        btnGoogleMap.setOnClickListener(this);
        Button btnThreadDemo = (Button) findViewById(R.id.btnThreadDemo);
        btnThreadDemo.setOnClickListener(this);
        Button btnDownloader = (Button) findViewById(R.id.btnAsyncTask);
        btnDownloader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginPage:
                boolean isLogIn = false;
                SharedPreferences sharedPreferences = getSharedPreferences("Check LogIn", Context.MODE_PRIVATE);

                if (sharedPreferences != null) {
                    isLogIn = sharedPreferences.getBoolean("isLogIn", false);
                }
                if (isLogIn) {
                    startActivity(new Intent(this, LogInSuccessActivity.class));
                } else {
                    //Navigate to Login  Screen
                    Intent i = new Intent(MainActivity.this, LoginScreenActivity.class);
                    startActivity(i);
                }
                break;
            case R.id.btnLayout:
                //Navigate to Header Information Layout  Screen
                Intent i2 = new Intent(this, HeaderInformationLayoutActivity.class);
                startActivity(i2);
                break;
            case R.id.imgBtnCall:
                //Navigate to PhoneCallActvity Layout  Screen
                Intent intentCall = new Intent(this, PhoneCallActivity.class);
                startActivity(intentCall);
                break;
            case R.id.btnIntentFilter:
                //Navigate to IntentFilterActivity Layout  Screen
                Intent intentFilter = new Intent(this, IntentFilterActivity.class);
                startActivity(intentFilter);
                break;
            case R.id.btnSqlite:
                //Navigate to ListNoteActivity Layout  Screen
                Intent intentSqlite = new Intent(this, ListNoteActivity.class);
                startActivity(intentSqlite);
                break;
            case R.id.btnSharedPreference:
                //Navigate to SharedPreferenceActivity Layout  Screen
                Intent intentSharedPre = new Intent(this, SharedPreferenceActivity.class);
                startActivity(intentSharedPre);
                break;
            case R.id.btnStorage:
                //Navigate to InternalStorageActivity Layout  Screen
                Intent intentStorage = new Intent(this, InternalStorageActivity.class);
                startActivity(intentStorage);
                break;
            case R.id.btnFragment:
                //Navigate to FragmentDemoActivity Layout  Screen
                Intent intentFragment = new Intent(this, FragmentDemoActivity.class);
                startActivity(intentFragment);
                break;
            case R.id.btnFragmentDemo2:
                //Navigate to FragmentMainDemo2 Layout  Screen
                Intent intentFragmentDemo2 = new Intent(this, FragmentMainDemo2.class);
                startActivity(intentFragmentDemo2);
                break;
            case R.id.btnViewpager:
                //Navigate to ViewPager Layout  Screen
                Intent intentViewpager = new Intent(this, ViewPagerActivity.class);
                startActivity(intentViewpager);
                break;
            case R.id.btnService:
                //Navigate to ServiceDemo Layout  Screen
                Intent intentService = new Intent(this, Test.class);
                startActivity(intentService);
                break;
            case R.id.btnBroadcastReceiver:
                //Navigate to BroadcastReceiver Layout  Screen
                Intent intentBroadcast = new Intent(this, SmsBroadcastReceiver.class);
                startActivity(intentBroadcast);
                break;
            case R.id.btnUI:
                //Navigate to UI Layout  Screen
                Intent intentUI = new Intent(this, UIActivity.class);
                startActivity(intentUI);
                break;
            case R.id.btnGoogleMap:
                //Navigate to Google Map Layout  Screen
                Intent intentMap = new Intent(this, MapActivity.class);
                startActivity(intentMap);
                break;
            case R.id.btnThreadDemo:
                //Navigate to Demo Multi-Thread Layout  Screen
                Intent intentThread = new Intent(this, MultiThread.class);
                startActivity(intentThread);
                break;
            case R.id.btnAsyncTask:
                //Navigate to Demo Multi-Thread Layout  Screen
                Intent intentDownloader = new Intent(this, DownloadImageActivity.class);
                startActivity(intentDownloader);
                break;

        }
    }
}
