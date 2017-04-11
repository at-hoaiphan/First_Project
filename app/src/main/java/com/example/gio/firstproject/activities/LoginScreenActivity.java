package com.example.gio.firstproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

import java.util.Objects;

/**
 * Copyright by Gio.
 * Created on 3/7/2017.
 */
@EActivity(R.layout.activity_login_screen)
public class LoginScreenActivity extends AppCompatActivity {

    @ViewById(R.id.tvSignUp)
    TextView tvSignUp;

    @ViewById(R.id.btnLogin)
    Button btnLogin;

    @ViewById(R.id.edtName)
    EditText edtName;

    @ViewById(R.id.edtPassword)
    EditText edtPassword;

    @ViewById(R.id.imgBtnShow)
    ImageButton imgBtnShow;

    @Click(R.id.tvSignUp)
    void clickTvSignUp() {
        // Navigate to Register screen
        Intent i = new Intent(this, RegisterScreenActivity_.class);
        startActivity(i);
    }

    @Click(R.id.btnLogin)
    void clickBtnLogin() {
        // Get value of Username and Password
        String strName = edtName.getText().toString();
        String strPassword = edtPassword.getText().toString();
        if (!Objects.equals(strName, "") && !Objects.equals(strPassword, "")) {
            startActivity(new Intent(this, LogInSuccessActivity_.class));
        } else {
            Toast.makeText(LoginScreenActivity.this, "Please type your Username/Password!", Toast.LENGTH_SHORT).show();
        }
    }

    //Set event when handling see_password button
    @Touch(R.id.imgBtnShow)
    boolean touchImgBtnShow(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        return true;
    }
}

