package com.example.gio.firstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 3/8/2017.
 */
@EActivity(R.layout.activity_register_screen)
public class RegisterScreenActivity extends AppCompatActivity {

    @ViewById(R.id.tvHadUsername)
    TextView tvHadUsername;

    @ViewById(R.id.edtPassword)
    EditText edtPassword;

    @ViewById(R.id.imgBtnShow)
    ImageButton imgBtnShow;

    //Back to Login page
    @Click(R.id.tvHadUsername)
    void clickTvHadUsername() {
        finish();
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
