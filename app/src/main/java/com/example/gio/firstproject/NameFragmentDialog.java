package com.example.gio.firstproject;

import android.app.DialogFragment;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Copyright by Gio.
 * Created on 3/30/2017.
 */
@EFragment(R.layout.fragment_dialog)
public class NameFragmentDialog extends DialogFragment {
    @ViewById(R.id.edtFragment)
    EditText edtFragment;
    @ViewById(R.id.btnDone)
    Button btnDone;

    public NameFragmentDialog() {
    }

    @Click(R.id.btnDone)
    void clickBtnDone() {
        NameDialogFragmentListener activity = (NameDialogFragmentListener) getActivity();
                activity.onFinishEditDialog(edtFragment.getText().toString());
        dismiss();
    }

//        // Show keypad when user open dialog
//        edtFragment.requestFocus();
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        edtFragment.setOnEditorActionListener(this);

    public interface NameDialogFragmentListener {
        void onFinishEditDialog(String inputText);
    }
}
