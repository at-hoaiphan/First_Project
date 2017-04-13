package com.example.gio.firstproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.model.ItemUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 3/13/2017.
 */

@EActivity(R.layout.activity_detail_item)
public class DetailItemActivity extends AppCompatActivity {

    @ViewById(R.id.tvName)
    TextView tvName;

    @ViewById(R.id.tvCompany)
    TextView tvCompany;

    @ViewById(R.id.tvMajor)
    TextView tvMajor;

    @ViewById(R.id.tvAbout)
    TextView tvAbout;

    @ViewById(R.id.tvId)
    TextView tvId;

    @ViewById(R.id.imgBtnFavourite)
    ImageButton imgBtnIsFavourite;

    private ItemUser user;
    private int mIndex;

    @AfterViews
    void afterViews() {
        user = getIntent().getBundleExtra("mListItems").getParcelable("user_item");
        mIndex = getIntent().getIntExtra("index", -1);

        tvName.setText(user.getName());
        tvCompany.setText(user.getCompany());
        tvMajor.setText(user.getMajor());
        tvAbout.setText(user.getAbout());
        tvId.setText(String.valueOf(user.getId()));

        if (user.getIsFavourite()) {
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_stargold);
        } else {
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
        }
    }

    @Click(R.id.imgBtnFavourite)
    void clickBtnIsFavourite() {
        if (user.getIsFavourite()) {
            user.setIsFavourite(false);
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
        } else {
            user.setIsFavourite(true);
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_stargold);
        }
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
