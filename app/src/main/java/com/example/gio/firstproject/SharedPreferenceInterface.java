package com.example.gio.firstproject;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Copyright by Gio.
 * Created on 4/12/2017.
 */
@SharedPref
public interface SharedPreferenceInterface {

    @DefaultInt(80)
    int sound();
    @DefaultInt(80)
    int brightness();
    @DefaultInt(R.id.rbNormal)
    int checkedRadioButtonId();

    long lastUpdate();
}
