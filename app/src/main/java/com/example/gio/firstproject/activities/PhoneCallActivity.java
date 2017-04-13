package com.example.gio.firstproject.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 3/15/2017.
 */
@EActivity(R.layout.activity_phone_call_screen)
public class PhoneCallActivity extends AppCompatActivity {
    @ViewById(R.id.btn0)
    Button btn0;
    @ViewById(R.id.btn1)
    Button btn1;
    @ViewById(R.id.btn2)
    Button btn2;
    @ViewById(R.id.btn3)
    Button btn3;
    @ViewById(R.id.btn4)
    Button btn4;
    @ViewById(R.id.btn5)
    Button btn5;
    @ViewById(R.id.btn6)
    Button btn6;
    @ViewById(R.id.btn7)
    Button btn7;
    @ViewById(R.id.btn8)
    Button btn8;
    @ViewById(R.id.btn9)
    Button btn9;
    @ViewById(R.id.btnAsterisk)
    Button btnAsterisk;
    @ViewById(R.id.btnHash)
    Button btnHash;
    @ViewById(R.id.edtNumber)
    EditText edtNumber;
    @ViewById(R.id.imgBtnBackspace)
    ImageButton imgBtnBackspace;
    @ViewById(R.id.imgBtnCall)
    ImageButton imgBtnCall;

    private String number;

    @AfterViews
    void afterViews() {
        //get data from Medial Application
        Intent intent = getIntent();
        number = intent.getDataString();
        if (number != null) {
            edtNumber.setText(number);
        } else {
            number = "";
        }
    }

    @Click(R.id.btn0)
    void clickBtn0() {
        number += btn0.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btn1)
    void clickBtn1() {
        number += btn1.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btn2)
    void clickBtn2() {
        number += btn2.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btn3)
    void clickBtn3() {
        number += btn3.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btn4)
    void clickBtn4() {
        number += btn4.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btn5)
    void clickBtn5() {
        number += btn5.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btn6)
    void clickBtn6() {
        number += btn6.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btn7)
    void clickBtn7() {
        number += btn7.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btn8)
    void clickBtn8() {
        number += btn8.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btn9)
    void clickBtn9() {
        number += btn9.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btnAsterisk)
    void clickBtnAsterisk() {
        number += btnAsterisk.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.btnHash)
    void clickBtnHash() {
        number += btnHash.getText();
        edtNumber.setText(number);
    }

    @Click(R.id.imgBtnBackspace)
    void clickImgBtnBackSpace() {
        if (number.length() > 0) {
            number = number.substring(0, number.length() - 1);
            edtNumber.setText(number);
            edtNumber.setSelection(edtNumber.getText().length());
        }
    }

    @Click(R.id.imgBtnCall)
    void clickImgBtnCall() {
        Toast.makeText(PhoneCallActivity.this, number, Toast.LENGTH_SHORT).show();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));

        if (ActivityCompat.checkSelfPermission(PhoneCallActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    //Permit for higher android 5.0
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},0);
            return;
        }
        startActivity(callIntent);
    }
}
