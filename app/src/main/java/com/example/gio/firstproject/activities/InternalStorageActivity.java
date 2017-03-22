package com.example.gio.firstproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio.firstproject.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by Gio on 3/20/2017.
 */

public class InternalStorageActivity extends AppCompatActivity {

    private Button btnSaveToFile;
    private Button btnReadFile;
    private TextView tvOutputText;
    private EditText edtInputText;

    // A simple name file without path
    private String simpleFileName = "note.txt";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        btnSaveToFile = (Button) findViewById(R.id.btnSave);
        btnReadFile = (Button) findViewById(R.id.btnReadFile);
        edtInputText = (EditText) findViewById(R.id.edtInput);
        tvOutputText = (TextView) findViewById(R.id.tvOutput);
        btnSaveToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    saveData();
            }
        });
        btnReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
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
