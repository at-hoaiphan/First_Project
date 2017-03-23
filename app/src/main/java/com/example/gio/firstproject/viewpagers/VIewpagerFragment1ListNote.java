package com.example.gio.firstproject.viewpagers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.activities.AddEditNoteActivity;
import com.example.gio.firstproject.activities.ViewPagerActivity;
import com.example.gio.firstproject.adapter.NoteAdapter;
import com.example.gio.firstproject.model.Note;

import java.util.ArrayList;

/**
 * Created by Gio on 3/23/2017.
 */

public class ViewpagerFragment1ListNote extends Fragment {

    private ViewPagerActivity viewPagerActivity;
    private RecyclerView recyclerViewPagerListNote;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NoteAdapter noteAdapter;


    private static final int NOTE_EDIT = 22;

    public ViewpagerFragment1ListNote() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item, container, false);

        recyclerViewPagerListNote = (RecyclerView) view.findViewById(R.id.recylerView);

        MyDatabaseHelper db = new MyDatabaseHelper(viewPagerActivity);
        db.createDefaultNotesIfNeed();

        mNotes.addAll(db.getAllNotes());
        noteAdapter = new NoteAdapter(viewPagerActivity, mNotes);
        recyclerViewPagerListNote.setAdapter(noteAdapter);

        //RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(viewPagerActivity, LinearLayoutManager.VERTICAL, false);
        recyclerViewPagerListNote.setLayoutManager(linearLayoutManager);

        // Set onClick item in listNote fragment
        noteAdapter.setNoteOnClickListener(new NoteAdapter.NoteOnClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(viewPagerActivity, AddEditNoteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("note_item", mNotes.get(id));
                intent.putExtra("mNotes", bundle);
                intent.putExtra("editNote", NOTE_EDIT);
                startActivityForResult(intent, 1);
            }
        });

        return view;
    }

    //  Phương thức này được gọi sau khi Fragment được ghép vào Activity.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ViewPagerActivity) {
            this.viewPagerActivity = (ViewPagerActivity) context;
        }
    }
}
