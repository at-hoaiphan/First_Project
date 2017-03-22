package com.example.gio.firstproject.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.model.Note;
import com.squareup.picasso.Picasso;

public class AddEditNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private Note note;
    private ImageView imgAvatar;
    private TextView tvHeaderAddEdit;
    private EditText edtTitle;
    private EditText edtContent;
    private Button btnAddEdit;
    private Button btnDeleteNote;
    private static final int SELECT_PICTURE = 7;
    private static int noteState = 0;
    private static final int NOTE_ADD = 11;
    private static final int NOTE_EDIT = 22;
    private MyDatabaseHelper myDatabaseHelper;
    private String noteUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);


        edtTitle = (EditText) findViewById(R.id.edtNoteTitle);
        edtContent = (EditText) findViewById(R.id.edtNoteContent);
        btnAddEdit = (Button) findViewById(R.id.btnAddEdit);
        btnDeleteNote = (Button) findViewById(R.id.btnDelete);
        imgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        tvHeaderAddEdit = (TextView) findViewById(R.id.tvHeaderAddEdit);


        noteState = NOTE_ADD;
        noteState = getIntent().getIntExtra("editNote", NOTE_ADD);
        if (noteState == NOTE_EDIT) {
            note = getIntent().getBundleExtra("mNotes").getParcelable("note_item");
            edtTitle.setText(note.getNoteTitle());
            edtContent.setText(note.getNoteContent());
            if (note.getNoteImageUri() != null) {
                Picasso.with(this).load(note.getNoteImageUri()).into(imgAvatar);
//                imgAvatar.setImageURI(Uri.parse(note.getNoteImageUri()));
            } else {
                imgAvatar.setImageResource(R.drawable.img_nullavatar);
            }
            btnAddEdit.setText("Edit");
            tvHeaderAddEdit.setText("Edit Note");
            btnDeleteNote.setVisibility(View.VISIBLE);
        }

        myDatabaseHelper = new MyDatabaseHelper(this);
        btnAddEdit.setOnClickListener(this);
        btnDeleteNote.setOnClickListener(this);
        imgAvatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddEdit:
                if (edtTitle.getText().toString().equals("") || edtContent.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter title & content", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    if (noteState == NOTE_ADD) {
                        Note newNote = new Note(edtTitle.getText().toString(), edtContent.getText().toString());
                        myDatabaseHelper.addNote(newNote);
                        Toast.makeText(getApplicationContext(), "Record has been added!", Toast.LENGTH_LONG).show();
                        refreshList();
                    } else if (noteState == NOTE_EDIT) {
                        note.setNoteTitle(edtTitle.getText().toString());
                        note.setNoteContent(edtContent.getText().toString());
                        note.setNoteImageUri(noteUri);
//                        note.setNoteImageUri("content://com.android.providers.media.documents/document/image%3A20");
                        myDatabaseHelper.updateNote(note);
                        Toast.makeText(getApplicationContext(), "Record has been editted!", Toast.LENGTH_LONG).show();
                        refreshList();
                    }
                }
                break;
            case R.id.btnDelete:
                // Ask before delete.
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert:");
                builder.setMessage("Delete this record?")
                        .setCancelable(false)
                        // Set event for Deleting
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Delete Note
                                myDatabaseHelper.deleteNote(note);
                                refreshList();
                                finish();
                            }
                        })
                        // Set event for Canceling
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.imgAvatar:
                openImageChooser();
                break;
        }
    }

    /* Choose an image from Gallery */
    public void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Gallery request
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            // Get the url from data
            Uri selectedImageUri = data.getData();
            noteUri = selectedImageUri.toString();
            if (null != selectedImageUri) {
                // Set the image in ImageView
                imgAvatar.setImageURI(selectedImageUri);
            }
        }
    }

    public void refreshList() {
        Intent data = new Intent();
        data.putExtra("needRefresh", true);
        this.setResult(RESULT_OK, data);
    }
}
