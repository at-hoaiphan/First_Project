package com.example.gio.firstproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.broadcast_receiver.SmsBroadcastReceiver;
import com.example.gio.firstproject.fragmentdemo.FragmentDemoActivity;
import com.example.gio.firstproject.fragmentdemo.FragmentMainDemo2_;
import com.example.gio.firstproject.servicedemo.Test_;
import com.example.gio.firstproject.thread_handler_asynctask.DownloadImageActivity;
import com.example.gio.firstproject.thread_handler_asynctask.MultiThread;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.btnLoginPage)
    Button btnLoginPage;

    @ViewById(R.id.btnLayout)
    Button btnLayout;

    @ViewById(R.id.imgBtnCall)
    ImageButton imgBtnCall;

    @ViewById(R.id.btnIntentFilter)
    Button btnIntentFilter;

    @ViewById(R.id.btnSqlite)
    Button btnSqlite;

    @ViewById(R.id.btnSharedPreference)
    Button btnSharedPre;

    @ViewById(R.id.btnStorage)
    Button btnStorage;

    @ViewById(R.id.btnFragment)
    Button btnFragment;

    @ViewById(R.id.btnFragmentDemo2)
    Button btnFragmentDemo2;

    @ViewById(R.id.btnViewpager)
    Button btnViewpager;

    @ViewById(R.id.btnService)
    Button btnService;

    @ViewById(R.id.btnBroadcastReceiver)
    Button btnBroadcast;

    @ViewById(R.id.btnUI)
    Button btnUI;

    @ViewById(R.id.btnGoogleMap)
    Button btnGoogleMap;

    @ViewById(R.id.btnThreadDemo)
    Button btnThreadDemo;

    @ViewById(R.id.btnAsyncTask)
    Button btnDownloader;

    @ViewById(R.id.btnAnswersJson)
    Button btnAnswersJson;

    @ViewById(R.id.btnAAnotation)
    Button btnAAnotation;

    @Click(R.id.btnLoginPage)
    void clickBtnLoginPage() {
        boolean isLogIn = false;
        SharedPreferences sharedPreferences = getSharedPreferences("Check LogIn", Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            isLogIn = sharedPreferences.getBoolean("isLogIn", false);
        }
        if (isLogIn) {
            startActivity(new Intent(this, LogInSuccessActivity_.class));
        } else {
            //Navigate to Login  Screen
            LoginScreenActivity_.intent(this).start();
        }
    }

    @Click(R.id.btnLayout)
    void clickBtnLayout() {
        //Navigate to Header Information Layout  Screen
        HeaderInformationLayoutActivity_.intent(this).start();
    }

    @Click(R.id.imgBtnCall)
    void clickImgBtnCall() {
        //Navigate to PhoneCallActvity Layout  Screen
        PhoneCallActivity_.intent(this).start();
    }

    @Click(R.id.btnIntentFilter)
    void clickBtnIntentFilter() {
        //Navigate to IntentFilterActivity Layout  Screen
        IntentFilterActivity_.intent(this).start();
    }

    @Click(R.id.btnSqlite)
    void clickBtnSqlite() {
        //Navigate to ListNoteActivity Layout  Screen
        ListNoteActivity_.intent(this).start();
    }

    @Click(R.id.btnSharedPreference)
    void clickBtnSharedPreference() {
        //Navigate to SharedPreferenceActivity Layout  Screen
        SharedPreferenceActivity_.intent(this).start();
    }

    @Click(R.id.btnStorage)
    void clickBtnStorage() {
        //Navigate to InternalStorageActivity Layout  Screen
        InternalStorageActivity_.intent(this).start();
    }

    @Click(R.id.btnFragment)
    void clickBtnFragment() {
        //Navigate to FragmentDemoActivity Layout  Screen
        Intent intentFragment = new Intent(this, FragmentDemoActivity.class);
        startActivity(intentFragment);
    }

    @Click(R.id.btnFragmentDemo2)
    void clickBtnFragmentDemo2() {
        //Navigate to FragmentMainDemo2 Layout  Screen
        Intent intentFragmentDemo2 = new Intent(this, FragmentMainDemo2_.class);
        startActivity(intentFragmentDemo2);
    }

    @Click(R.id.btnViewpager)
    void clickBtnViewpager() {
        //Navigate to ViewPager Layout  Screen
        ViewPagerActivity_.intent(this).start();
    }

    @Click(R.id.btnService)
    void clickBtnService() {
        //Navigate to ServiceDemo Layout  Screen
        Test_.intent(this).start();
    }

    @Click(R.id.btnBroadcastReceiver)
    void clickBtnBroadcastReceiver() {
        //Navigate to BroadcastReceiver Layout  Screen
        Intent intentBroadcast = new Intent(this, SmsBroadcastReceiver.class);

        startActivity(intentBroadcast);
    }

    @Click(R.id.btnUI)
    void clickBtnUI() {
        //Navigate to UI Layout  Screen
        UIActivity_.intent(this).start();
    }

    @Click(R.id.btnGoogleMap)
    void clickBtnGoogleMap() {
        //Navigate to Google Map Layout  Screen
        MapActivity_.intent(this).start();
    }

    @Click(R.id.btnThreadDemo)
    void clickBtnThreadDemo() {
        //Navigate to Demo Multi-Thread Layout  Screen
        Intent intentThread = new Intent(this, MultiThread.class);
        startActivity(intentThread);
    }

    @Click(R.id.btnAsyncTask)
    void clickBtnAsyncTask() {
        //Navigate to Downloader AsyncTask Layout  Screen
        Intent intentDownloader = new Intent(this, DownloadImageActivity.class);
        startActivity(intentDownloader);
    }

    @Click(R.id.btnAnswersJson)
    void clickBtnAnswerJson() {
        //Navigate to Answers Json Layout  Screen
        Intent intentAnswerJson = new Intent(this, AnswersActivity_.class);
        startActivity(intentAnswerJson);
    }

    @Click(R.id.btnAAnotation)
    void clickBtnAAnnotation() {
        // AAnnotationDemo_.intent(this).start();
        Intent iAA = new Intent(this, AAnnotationDemo_.class);
        startActivity(iAA);
    }
}
