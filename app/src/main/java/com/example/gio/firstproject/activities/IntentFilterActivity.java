package com.example.gio.firstproject.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gio.firstproject.R;

import java.util.Locale;

/**
 * Created by Gio on 3/16/2017.
 */

public class IntentFilterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCallIntent, btnSmsIntent, btnMailIntent, btnLaunchWebIntent, btnPlayStoreIntent, btnMapIntent, btnGalleryIntent, btnCameraIntent;
    ImageView imgView;
    private static int SELECT_PICTURE = 7, CAMERA_REQUEST = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_filter);

        btnCallIntent = (Button) findViewById(R.id.btnCallIntent);
        btnSmsIntent = (Button) findViewById(R.id.btnSmsIntent);
        btnMailIntent = (Button) findViewById(R.id.btnMailIntent);
        btnLaunchWebIntent = (Button) findViewById(R.id.btnLaunchWebIntent);
        btnPlayStoreIntent = (Button) findViewById(R.id.btnPlayStoreIntent);
        btnMapIntent = (Button) findViewById(R.id.btnMapIntent);
        btnGalleryIntent = (Button) findViewById(R.id.btnGalleryIntent);
        btnCameraIntent = (Button) findViewById(R.id.btnCameraIntent);
        btnCallIntent.setOnClickListener(this);
        btnSmsIntent.setOnClickListener(this);
        btnMailIntent.setOnClickListener(this);
        btnLaunchWebIntent.setOnClickListener(this);
        btnPlayStoreIntent.setOnClickListener(this);
        btnMapIntent.setOnClickListener(this);
        btnGalleryIntent.setOnClickListener(this);
        btnCameraIntent.setOnClickListener(this);
        imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCallIntent:
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:123123123"));
                startActivity(i);
                break;
            case R.id.btnSmsIntent:
                Intent i2 = new Intent(Intent.ACTION_SENDTO);
                i2.setData(Uri.parse("smsto:123123123"));
                i2.putExtra("sms_body", "Tell me why...?");
                startActivity(i2);
                break;
            case R.id.btnMailIntent:
                Intent i3 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "tai.nguyen@asiantech.vn", null));
                i3.putExtra(Intent.EXTRA_SUBJECT, "Subject Intent Filter");
                i3.putExtra(Intent.EXTRA_TEXT, "Body - Phan Van Hoai");
                startActivity(Intent.createChooser(i3, "Send email..."));
                break;
            case R.id.btnLaunchWebIntent:
                Intent i4 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vnexpress.net"));
                startActivity(i4);
                break;
            case R.id.btnPlayStoreIntent:
                Intent i5 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/"));
                startActivity(i5);
                break;
            case R.id.btnMapIntent:
                double latitude = 16.08, longitude = 108.22;
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
                break;
            case R.id.btnGalleryIntent:
                openImageChooser();
                break;
            case R.id.btnCameraIntent:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;
        }
    }

    /* Choose an image from Gallery */
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Set the image in ImageView
                    imgView.setImageURI(selectedImageUri);
                }
                if (requestCode == CAMERA_REQUEST) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    Log.i("capture", "Image Path : ");
                    imgView.setImageBitmap(photo);
                }
            }
        }
    }



    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

}


