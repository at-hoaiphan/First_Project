package com.example.gio.firstproject.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gio.firstproject.R;

/**
 * Created by Gio on 3/15/2017.
 */

public class PhoneCallActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAsterisk, btnHash;
    EditText edtNumber;
    ImageButton imgBtnBackspace, imgBtnCall;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_call_screen);


        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        edtNumber = (EditText) findViewById(R.id.edtNumber);
        edtNumber.setOnClickListener(this);
        imgBtnBackspace = (ImageButton) findViewById(R.id.imgBtnBackspace);
        imgBtnBackspace.setOnClickListener(this);
        imgBtnCall = (ImageButton) findViewById(R.id.imgBtnCall);
        imgBtnCall.setOnClickListener(this);

        //get data from Medial Application
        Intent intent = getIntent();
        number = intent.getDataString();
        // TODO: 3/15/2017 trim data-string: tel:123123  --> 123123
//        //trim Number string
//        number = number.substring(4, number.length() - 1);
        if (number != null) {
            edtNumber.setText(number);
        }
        else {
            number = "";
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                number += btn0.getText();
                edtNumber.setText(number);
                break;
            case R.id.btn1:
                number += btn1.getText();
                edtNumber.setText(number);
                break;
            case R.id.btn2:
                number += btn2.getText();
                edtNumber.setText(number);
                break;
            case R.id.btn3:
                number += btn3.getText();
                edtNumber.setText(number);
                break;
            case R.id.btn4:
                number += btn4.getText();
                edtNumber.setText(number);
                break;
            case R.id.btn5:
                number += btn5.getText();
                edtNumber.setText(number);
                break;
            case R.id.btn6:
                number += btn6.getText();
                edtNumber.setText(number);
                break;
            case R.id.btn7:
                number += btn7.getText();
                edtNumber.setText(number);
                break;
            case R.id.btn8:
                number += btn8.getText();
                edtNumber.setText(number);
                break;
            case R.id.btn9:
                number += btn9.getText();
                edtNumber.setText(number);
                break;
            case R.id.btnAsterisk:
                number += btnAsterisk.getText();
                edtNumber.setText(number);
                break;
            case R.id.btnHash:
                number += btnHash.getText();
                edtNumber.setText(number);
                break;
            case R.id.imgBtnBackspace:
                if (number.length() > 0) {
                    number = number.substring(0, number.length() - 1);
                    edtNumber.setText(number);
                    edtNumber.setSelection(edtNumber.getText().length());
                }
                // TODO: 3/15/2017 set limit onClick button when edtNumber == null
                break;
            case R.id.imgBtnCall:
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
                break;
        }
    }
}
