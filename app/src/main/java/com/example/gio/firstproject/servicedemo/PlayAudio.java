package com.example.gio.firstproject.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.EService;

@EService
public class PlayAudio extends Service{
    MediaPlayer objPlayer;

    public void onCreate(){
        super.onCreate();
        objPlayer = MediaPlayer.create(this,R.raw.theme);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        objPlayer.start();
        return START_STICKY;
    }

    public void onDestroy(){
        objPlayer.stop();
        objPlayer.release();
    }
    @Override
    public IBinder onBind(Intent objIndent) {
        return null;
    }
}
