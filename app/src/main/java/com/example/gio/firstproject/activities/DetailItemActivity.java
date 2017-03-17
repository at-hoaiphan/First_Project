package com.example.gio.firstproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.model.ItemUser;

/**
 * Created by Gio on 3/13/2017.
 */
public class DetailItemActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imgBtnIsFavourite;
    private ItemUser user;
    private int mIndex;

    private void init() {
        TextView tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(user.getName());

        TextView tvCompany = (TextView) findViewById(R.id.tvCompany);
        tvCompany.setText(user.getCompany());

        TextView tvMajor = (TextView) findViewById(R.id.tvMajor);
        tvMajor.setText(user.getMajor());

        TextView tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvAbout.setText(user.getAbout());

        TextView tvId = (TextView) findViewById(R.id.tvId);
        tvId.setText(String.valueOf(user.getId()));

    }

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        user = getIntent().getBundleExtra("mListItems").getParcelable("user_item");
        mIndex = getIntent().getIntExtra("index", -1);
        init();

        imgBtnIsFavourite = (ImageButton) findViewById(R.id.imgBtnFavourite);
        if (user.getIsFavourite()) {
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_stargold);
        } else {
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
        }
        imgBtnIsFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getIsFavourite()) {
                    user.setIsFavourite(false);
                    imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
                } else {
                    user.setIsFavourite(true);
                    imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_stargold);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("isFavourite", user.getIsFavourite());
        if (mIndex != -1) {
            i.putExtra("index", mIndex);
        }
        setResult(RESULT_OK, i);
        finish();
    }
}
