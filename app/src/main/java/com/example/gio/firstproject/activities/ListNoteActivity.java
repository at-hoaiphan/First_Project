package com.example.gio.firstproject.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.NoteAdapter;
import com.example.gio.firstproject.model.Note;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.list_note)
public class ListNoteActivity extends AppCompatActivity implements NoteAdapter.NoteOnClickListener, View.OnClickListener {

    @ViewById(R.id.rlListNote)
    RecyclerView rlListItem;

    @ViewById(R.id.imgBtnAddNote)
    ImageButton imgBtnAddNote;

    private static final int NOTE_ADD = 11;
    private static final int NOTE_EDIT = 22;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NoteAdapter noteAdapter;

    @AfterViews
    void afterViews() {
        // Get ListView object from xml
        imgBtnAddNote.setOnClickListener(this);
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultNotesIfNeed();

        mNotes.addAll(db.getAllNotes());
        noteAdapter = new NoteAdapter(this, mNotes);
        rlListItem.setAdapter(noteAdapter);

        noteAdapter.setNoteOnClickListener(new NoteAdapter.NoteOnClickListener() {
            @Override
            public void onClick(int id) {
//                Intent intent = new Intent(ListNoteActivity.this, AddEditNoteActivity_.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("note_item", mNotes.get(id));
//                intent.putExtra("mNotes", bundle);
//                intent.putExtra("editNote", NOTE_EDIT);
//                startActivityForResult(intent, 1);
                AddEditNoteActivity_.intent(ListNoteActivity.this).note(mNotes.get(id)).noteState(NOTE_EDIT).start();

            }
        });

        //RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rlListItem.setLayoutManager(linearLayoutManager);
    }

    @OnActivityResult(1)
    void onResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            if (needRefresh) {
                this.mNotes.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(this);
                ArrayList<Note> list = db.getAllNotes();
                this.mNotes.addAll(list);
                // Notify data set Changed.
                noteAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onClick(int id) {

    }

    @Override
    public void onClick(View v) {
        // Ask before add.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message:");
        builder.setMessage("Add a new record?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        System.exit(0);
                        navigateAddNoteActivity();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void navigateAddNoteActivity() {
        AddEditNoteActivity_.intent(ListNoteActivity.this).noteState(NOTE_ADD).start();
    }
}
