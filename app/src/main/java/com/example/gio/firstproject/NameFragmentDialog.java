package com.example.gio.firstproject;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Copyright by Gio.
 * Created on 3/30/2017.
 */

public class NameFragmentDialog extends DialogFragment {
    private EditText edtFragment;

    public NameFragmentDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create Dialog Interface from xml
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        edtFragment = (EditText) view.findViewById(R.id.edtFragment);
        Button btnDone = (Button) view.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameDialogFragmentListener activity = (NameDialogFragmentListener) getActivity();
                activity.onFinishEditDialog(edtFragment.getText().toString());
                dismiss();
            }
        });

//        // Show keypad when user open dialog
//        edtFragment.requestFocus();
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        edtFragment.setOnEditorActionListener(this);

        return view;
    }

    public interface NameDialogFragmentListener {
        void onFinishEditDialog(String inputText);
    }
}
