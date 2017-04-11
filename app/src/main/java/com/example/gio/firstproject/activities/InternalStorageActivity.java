package com.example.gio.firstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio.firstproject.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Copyright by Gio.
 * Created on 3/20/2017.
 */
@EActivity(R.layout.activity_internal_storage)
public class InternalStorageActivity extends AppCompatActivity {

    @ViewById(R.id.btnSave)
    Button btnSaveToFile;

    @ViewById(R.id.btnReadFile)
    Button btnReadFile;

    @ViewById(R.id.tvOutput)
    TextView tvOutputText;

    @ViewById(R.id.edtInput)
    EditText edtInputText;

    // A simple name file without path
    private String simpleFileName = "note.txt";

    @Click(R.id.btnSave)
    void clickBtnSaveToFile() {
        saveData();
    }

    private void saveData() {
        String data = edtInputText.getText().toString();
        try {
            // Write file
            FileOutputStream outputStream = openFileOutput(simpleFileName, MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
            Toast.makeText(this, "File saved!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error!" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Click(R.id.btnReadFile)
    void clickBtnReadFile() {
        readFile();
    }

    private void readFile() {
        try {
            FileInputStream inputStream = openFileInput(simpleFileName);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str).append("\n");
            }
            tvOutputText.setText(stringBuilder.toString());
        } catch (Exception e) {
            Toast.makeText(this, "Error!" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
