package com.example.gio.firstproject.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Locale;

/**
 * Copyright by Gio.
 * Created on 3/16/2017.
 */
@EActivity(R.layout.activity_intent_filter)
public class IntentFilterActivity extends AppCompatActivity {

    @ViewById(R.id.btnCallIntent)
    Button btnCallIntent;

    @ViewById(R.id.btnSmsIntent)
    Button btnSmsIntent;

    @ViewById(R.id.btnMailIntent)
    Button btnMailIntent;

    @ViewById(R.id.btnLaunchWebIntent)
    Button btnLaunchWebIntent;

    @ViewById(R.id.btnPlayStoreIntent)
    Button btnPlayStoreIntent;

    @ViewById(R.id.btnMapIntent)
    Button btnMapIntent;

    @ViewById(R.id.btnGalleryIntent)
    Button btnGalleryIntent;

    @ViewById(R.id.btnCameraIntent)
    Button btnCameraIntent;

    @ViewById(R.id.imgView)
    ImageView imgView;

    private static final int SELECT_PICTURE = 7;
    private static final int CAMERA_REQUEST = 8;

    @Click(R.id.btnCallIntent)
    void clickBtnCallIntent() {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:123123123"));
        startActivity(i);
    }

    @Click(R.id.btnSmsIntent)
    void clickBtnSmsIntent() {
        Intent i2 = new Intent(Intent.ACTION_SENDTO);
        i2.setData(Uri.parse("smsto:123123123"));
        i2.putExtra("sms_body", "Tell me why...?");
        startActivity(i2);
    }

    @Click(R.id.btnLaunchWebIntent)
    void clickBtnLaunchWebIntent() {
        Intent i4 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vnexpress.net"));
        startActivity(i4);
    }

    @Click(R.id.btnPlayStoreIntent)
    void clickBtnPlayStoreIntent() {
        Intent i5 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/"));
        startActivity(i5);
    }

    @Click(R.id.btnMapIntent)
    void clickBtnMapIntent() {
        double latitude = 16.08, longitude = 108.22;
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    @Click(R.id.btnGalleryIntent)
    void clickBtnGalleryIntent() {
        openImageChooser();
    }

    /* Choose an image from Gallery */
    public void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Click(R.id.btnCameraIntent)
    void clickBtnCameraIntent() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Gallery request
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            // Get the url from data
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // Set the image in ImageView
                imgView.setImageURI(selectedImageUri);
            }
        }
        // Camera request
        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Log.i("capture", "Image Path : ");
            imgView.setImageBitmap(photo);
        }
    }
}


