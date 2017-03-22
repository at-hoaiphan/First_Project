package com.example.gio.firstproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.model.Note;
import com.example.gio.firstproject.R;

public class AddEditNoteActivity extends AppCompatActivity {

    Note note;
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private int mode;
    private EditText edtTitle;
    private EditText edtContent;

    private boolean needRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        edtTitle = (EditText) findViewById(R.id.edtNoteTitle);
        edtContent = (EditText) findViewById(R.id.edtNoteContent);

        Intent intent = this.getIntent();
        note = (Note) intent.getSerializableExtra("note");
        if (note == null) {
            this.mode = MODE_CREATE;
        } else {
            this.mode = MODE_EDIT;
            edtTitle.setText(note.getNoteTitle());
            edtContent.setText(note.getNoteContent());
        }
    }

    // Click Save button.
    public void buttonSaveClicked(View view) {
        MyDatabaseHelper db = new MyDatabaseHelper(this);

        String title = this.edtTitle.getText().toString();
        String content = this.edtContent.getText().toString();

        if (title.equals("") || content.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter title & content", Toast.LENGTH_LONG).show();
            return;
        }

        if (mode == MODE_CREATE) {
            this.note = new Note(title, content);
            db.addNote(note);
        } else {
            this.note.setNoteTitle(title);
            this.note.setNoteContent(content);
            db.updateNote(note);
        }

        this.needRefresh = true;
        // Trở lại MainActivity.
        this.onBackPressed();
    }

    // Khi người dùng Click vào button Cancel.
    public void buttonCancelClicked(View view) {
        // Không làm gì, trở về MainActivity.
        this.onBackPressed();
    }

    // Khi Activity này hoàn thành,
    // có thể cần gửi phản hồi gì đó về cho Activity đã gọi nó.
    @Override
    public void finish() {
        // Chuẩn bị dữ liệu Intent.
        Intent data = new Intent();
        // Yêu cầu MainActivity refresh lại ListView hoặc không.
        data.putExtra("needRefresh", needRefresh);

        // Activity đã hoàn thành OK, trả về dữ liệu.
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
