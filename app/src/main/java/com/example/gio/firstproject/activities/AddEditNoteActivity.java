package com.example.gio.firstproject.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_add_edit_note)
public class AddEditNoteActivity extends AppCompatActivity {
    @ViewById(R.id.edtNoteTitle)
    EditText edtTitle;

    @ViewById(R.id.edtNoteContent)
    EditText edtContent;

    @ViewById(R.id.btnAddEdit)
    Button btnAddEdit;

    @ViewById(R.id.btnDelete)
    Button btnDeleteNote;

    @ViewById(R.id.imgAvatar)
    ImageView imgAvatar;

    @ViewById(R.id.tvHeaderAddEdit)
    TextView tvHeaderAddEdit;


    @Extra
    Note note;
    private static final int SELECT_PICTURE = 7;
    @Extra
    int noteState;
    private static final int NOTE_ADD = 11;
    private static final int NOTE_EDIT = 22;
    private MyDatabaseHelper myDatabaseHelper;
    private String noteUri;

    @AfterViews
    void afterViews() {
//        noteState = NOTE_ADD;
//        noteState = getIntent().getIntExtra("editNote", NOTE_ADD);
        if (noteState == NOTE_EDIT) {
//            note = getIntent().getBundleExtra("mNotes").getParcelable("note_item");
            assert note != null;
            edtTitle.setText(note.getNoteTitle());
            edtContent.setText(note.getNoteContent());
            noteUri = note.getNoteImageUri();
            if (note.getNoteImageUri() != null) {
                Picasso.with(this).load(note.getNoteImageUri()).into(imgAvatar);
            } else {
                imgAvatar.setImageResource(R.drawable.img_nullavatar);
            }
            btnAddEdit.setText("Edit");
            tvHeaderAddEdit.setText("Edit Note");
            btnDeleteNote.setVisibility(View.VISIBLE);
        }

        myDatabaseHelper = new MyDatabaseHelper(this);
    }

    @Click(R.id.btnAddEdit)
    void clickBtnAddEdit() {
        if (edtTitle.getText().toString().equals("") || edtContent.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter title & content", Toast.LENGTH_LONG).show();
        }
        else {
            if (noteState == NOTE_ADD) {
                if (noteUri != null) {
                    Note newNote = new Note(edtTitle.getText().toString(), edtContent.getText().toString(), noteUri);
                    myDatabaseHelper.addNote(newNote);
                } else {
                    Note newNote = new Note(edtTitle.getText().toString(), edtContent.getText().toString());
                    myDatabaseHelper.addNote(newNote);
                }
                Toast.makeText(getApplicationContext(), "Record has been added!", Toast.LENGTH_LONG).show();
                refreshList();
            } else if (noteState == NOTE_EDIT) {
                note.setNoteTitle(edtTitle.getText().toString());
                note.setNoteContent(edtContent.getText().toString());
                note.setNoteImageUri(noteUri);
                myDatabaseHelper.updateNote(note);
                Toast.makeText(getApplicationContext(), "Record has been editted!", Toast.LENGTH_LONG).show();
                refreshList();
            }
        }
    }

    @Click(R.id.btnDelete)
    void clickBtnDelte() {
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
    }

    @Click(R.id.imgAvatar)
    void clickImgAvater() {
        openImageChooser();
    }

    /* Choose an image from Gallery */
    public void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @OnActivityResult(SELECT_PICTURE)
    void onActivityResult(int resultCode, Intent data) {
        // Gallery request
        if (resultCode == RESULT_OK) {
            // Get the url from data
            Uri selectedImageUri = data.getData();
            noteUri = selectedImageUri.toString();
            // Set the image in ImageView
            imgAvatar.setImageURI(selectedImageUri);
        }
    }

    public void refreshList() {
        Intent data = new Intent();
        data.putExtra("needRefresh", true);
        Log.d("", "refreshList: ");
        this.setResult(RESULT_OK, data);
    }
}