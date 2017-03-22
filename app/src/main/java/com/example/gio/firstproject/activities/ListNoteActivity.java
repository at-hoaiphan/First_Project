package com.example.gio.firstproject.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.NoteAdapter;
import com.example.gio.firstproject.model.Note;

import java.util.ArrayList;

public class ListNoteActivity extends AppCompatActivity implements NoteAdapter.NoteOnClickListener, View.OnClickListener {

    private static final int MY_REQUEST_CODE = 1000;
    private static final int NOTE_ADD = 11;
    private static final int NOTE_EDIT = 22;

    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_note);

        // Get ListView object from xml
        RecyclerView rlListItem = (RecyclerView) findViewById(R.id.rlListNote);
        ImageButton imgBtnAddNote = (ImageButton) findViewById(R.id.imgBtnAddNote);
        imgBtnAddNote.setOnClickListener(this);
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultNotesIfNeed();

        mNotes.addAll(db.getAllNotes());
        noteAdapter = new NoteAdapter(this, mNotes);
        rlListItem.setAdapter(noteAdapter);

        noteAdapter.setNoteOnClickListener(new NoteAdapter.NoteOnClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(ListNoteActivity.this, AddEditNoteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("note_item", mNotes.get(id));
                intent.putExtra("mNotes", bundle);
                intent.putExtra("editNote", NOTE_EDIT);
                Log.d("adapter-", "onClick: " + mNotes.get(id));
                startActivityForResult(intent, 1);
            }
        });

        //RecyclerView scroll vertical
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rlListItem.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean needRefresh = data.getBooleanExtra("needRefresh", true);
                if (needRefresh) {
                    this.mNotes.clear();
                    MyDatabaseHelper db = new MyDatabaseHelper(this);
                    ArrayList<Note> list = db.getAllNotes();
                    this.mNotes.addAll(list);
                    // Thông báo dữ liệu thay đổi.
                    noteAdapter.notifyDataSetChanged();
                }
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
        Intent intent = new Intent(this, AddEditNoteActivity.class);
        startActivityForResult(intent, 1);
    }
}
