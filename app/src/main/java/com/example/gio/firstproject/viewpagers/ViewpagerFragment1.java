package com.example.gio.firstproject.viewpagers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.activities.AddEditNoteActivity_;
import com.example.gio.firstproject.activities.ViewPagerActivity;
import com.example.gio.firstproject.adapter.NoteAdapter;
import com.example.gio.firstproject.model.Note;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Copyright by Gio.
 * Created on 3/23/2017.
 */
@EFragment(R.layout.list_item)
public class ViewpagerFragment1 extends Fragment {

    @ViewById(R.id.recylerView)
    RecyclerView recyclerViewPagerListNote;


    private ViewPagerActivity mViewPagerActivity;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NoteAdapter noteAdapter;

    private static final int NOTE_EDIT = 22;

    public ViewpagerFragment1() {
    }

    @AfterViews
    void afterViews() {
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
                Intent intent = new Intent(mViewPagerActivity, AddEditNoteActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("note_item", mNotes.get(id));
                intent.putExtra("mNotes", bundle);
                intent.putExtra("editNote", NOTE_EDIT);
                startActivityForResult(intent, 1);
//                AddEditNoteActivity_.intent(getContext()).note(mNotes.get(id)).noteState(NOTE_EDIT).start();
            }
        });
    }

    //  This function is called after Fragment has attached to Activity.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ViewPagerActivity) {
            this.mViewPagerActivity = (ViewPagerActivity) context;
        }
    }

    @OnActivityResult(1)
    void onActivityResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            if (needRefresh) {
                this.mNotes.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(mViewPagerActivity);
                ArrayList<Note> list = db.getAllNotes();
                this.mNotes.addAll(list);
                // Notify data set Changed.
                noteAdapter.notifyDataSetChanged();
            }
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                boolean needRefresh = data.getBooleanExtra("needRefresh", true);
//                if (needRefresh) {
//                    this.mNotes.clear();
//                    MyDatabaseHelper db = new MyDatabaseHelper(mViewPagerActivity);
//                    ArrayList<Note> list = db.getAllNotes();
//                    this.mNotes.addAll(list);
//                    // Notify data set Changed.
//                    noteAdapter.notifyDataSetChanged();
//                }
//            }
//        }
//    }
}
