package com.example.gio.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Gio on 3/13/2017.
 */
public class DetailItemActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView tvId, tvName, tvCompany, tvMajor, tvAbout;
    ImageButton imgBtnIsFavourite;
    User user;
    int index;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        user = getIntent().getBundleExtra("mUsers").getParcelable("user_item");
        index = getIntent().getIntExtra("index mUsers", -1);

        tvId = (TextView) findViewById(R.id.tvId);
        tvId.setText("ID: " + String.valueOf(user.getId()));
        imgBtnIsFavourite = (ImageButton) findViewById(R.id.imgBtnFavourite);
        if (user.getIsFavourite()) {
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staron);
        } else {
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
        }
        imgBtnIsFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "click Favourite_Item "+user.getId()+ user.getName()+ user.getCompany()+ user.getMajor()+ user.getAbout()+ user.getIsFavourite());
                if (user.getIsFavourite()) {
                    user.setIsFavourite(false);
                    Log.d(TAG, "isfavourite set " + user.getIsFavourite());
                    imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
//                    mUserDetail.set(user.getId(), new User(user.getId(), user.getName(), user.getCompany(), user.getMajor(), user.getAbout(), 1));
                } else {
                    user.setIsFavourite(true);
                    Log.d(TAG, "isfavourite set " + user.getIsFavourite());
//                    Log.d(TAG, "click Favourite_Item_Detail1 "+user.getId()+ user.getName()+ user.getCompany()+ user.getMajor()+ user.getAbout()+ user.getIsFavourite());
                    imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staron);
//                    mUserDetail.set(user.getId(), new User(user.getId(), user.getName(), user.getCompany(), user.getMajor(), user.getAbout(), 0));
                }

            }
        });

        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(user.getName());

        tvCompany = (TextView) findViewById(R.id.tvCompany);
        tvCompany.setText(user.getCompany());

        tvMajor = (TextView) findViewById(R.id.tvMajor);
        tvMajor.setText(user.getMajor());

        tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvAbout.setText("About: " + user.getAbout());

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("select isFavourite", user.getIsFavourite());
        Log.d(TAG, "onBackPressed: " + index);
        if (index != -1) {
            i.putExtra("index", index);
            Log.d(TAG, "onBackPressed: fahsdjfgsdajf" + index);
        }
        setResult(RESULT_OK, i);
        finish();
    }
}
