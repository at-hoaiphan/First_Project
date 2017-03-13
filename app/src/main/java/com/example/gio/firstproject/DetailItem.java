package com.example.gio.firstproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Gio on 3/13/2017.
 */
public class DetailItem extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView tvId, tvName, tvCompany, tvMajor, tvAbout;
    ImageButton imgBtnIsFavourite;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        final User user = getIntent().getBundleExtra("mUser").getParcelable("user_item");

        tvId = (TextView) findViewById(R.id.tvId);
        tvId.setText("ID: "+ String.valueOf(user.getId()));
        imgBtnIsFavourite = (ImageButton) findViewById(R.id.imgBtnFavourite);
        if (user.getIsFavourite() == 0) {
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
        }
        else {
            imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staron);
        }
        imgBtnIsFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getIsFavourite() == 0) {
                    imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
                    user.setIsFavourite(1);
                }
                else {
                    imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staron);
                    user.setIsFavourite(0);
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
}
