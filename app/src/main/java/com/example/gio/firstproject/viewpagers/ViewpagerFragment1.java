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

public class ViewpagerFragment1 extends Fragment {

    private ViewPagerActivity mViewPagerActivity;
    private RecyclerView recyclerViewPagerListNote;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NoteAdapter noteAdapter;

    private static final int NOTE_EDIT = 22;

    public ViewpagerFragment1() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item, container, false);

        recyclerViewPagerListNote = (RecyclerView) view.findViewById(R.id.recylerView);

        MyDatabaseHelper db = new MyDatabaseHelper(mViewPagerActivity);
        db.createDefaultNotesIfNeed();

        mNotes.addAll(db.getAllNotes());
        noteAdapter = new NoteAdapter(mViewPagerActivity, mNotes);

        //RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mViewPagerActivity, LinearLayoutManager.VERTICAL, false);
        recyclerViewPagerListNote.setLayoutManager(linearLayoutManager);
        recyclerViewPagerListNote.setAdapter(noteAdapter);

        // Set onClick item in listNote fragment
        noteAdapter.setNoteOnClickListener(new NoteAdapter.NoteOnClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(mViewPagerActivity, AddEditNoteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("note_item", mNotes.get(id));
                intent.putExtra("mNotes", bundle);
                intent.putExtra("editNote", NOTE_EDIT);

                // Reload (need to be optimized)
                mViewPagerActivity.finish();
                startActivity(new Intent(mViewPagerActivity, ViewPagerActivity.class));

                startActivityForResult(intent, 1);
            }
        });

        return view;
    }

    //  This function is called after Fragment has attached to Activity.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ViewPagerActivity) {
            this.mViewPagerActivity = (ViewPagerActivity) context;
        }
    }
}
