package com.example.gio.firstproject.viewpagers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.activities.AddEditNoteActivity_;
import com.example.gio.firstproject.adapter.NoteAdapter;
import com.example.gio.firstproject.model.Note;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Copyright by Gio.
 * Created on 3/24/2017.
 */
@EFragment(R.layout.item_note)
public class ViewPagerFragment3Item extends Fragment {
    @ViewById(R.id.imgAvatar)
    ImageView imgAvatar;
    @ViewById(R.id.tvTitle)
    TextView tvTitle;
    @ViewById(R.id.tvContent)
    TextView tvContent;

    private ArrayList<Note> mNotes = new ArrayList<>();

    private static final int NOTE_EDIT = 22;
    private int finalPosition;

    public ViewPagerFragment3Item() {
    }

    @AfterViews
    void afterViews() {
        int position = 0;

        if (getArguments() != null) {
            position = getArguments().getInt("positionFrag");
        }

        // Replace item on fragment
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getContext());
        mNotes.addAll(myDatabaseHelper.getAllNotes());
        Note note = mNotes.get(position);
        if (note.getNoteImageUri() != null) {
            Picasso.with(getContext()).load(new File(note.getNoteImageUri()))
                    .placeholder(R.drawable.ic_setting)
                    .error(R.drawable.ic_lock)
                    .into(imgAvatar);
        } else {
            imgAvatar.setImageResource(R.drawable.img_nullavatar);
        }
        tvTitle.setText(note.getNoteTitle());
        tvContent.setText(note.getNoteContent());

        finalPosition = position;
        this.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddEditNoteActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("note_item", mNotes.get(finalPosition));
                intent.putExtra("mNotes", bundle);
                intent.putExtra("editNote", NOTE_EDIT);
                startActivityForResult(intent, 1);
//                AddEditNoteActivity_.intent(getContext()).note(mNotes.get(finalPosition)).noteState(NOTE_EDIT).start();
            }
        });
    }

    @OnActivityResult(1)
    void onActivityResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            if (needRefresh) {
                this.mNotes.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(getContext());
                ArrayList<Note> list = db.getAllNotes();
                this.mNotes.addAll(list);
                NoteAdapter mAdapter = new NoteAdapter(getContext(), mNotes);
                mAdapter.notifyDataSetChanged();
                Log.d("Refresh", "onActivityResult: ------- fragment3Item");
            }
        }
    }

    public ViewPagerFragment3Item getPosition(int position) {
        ViewPagerFragment3Item fragment = new ViewPagerFragment3Item_();
        Bundle bundle = new Bundle();
        bundle.putInt("positionFrag", position);
        fragment.setArguments(bundle);
        return fragment;
    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.item_note, container, false);
//
//        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
//        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
//        tvContent = (TextView) view.findViewById(R.id.tvContent);
//        int position = 0;
//
//        if(getArguments()!=null){
//            position = getArguments().getInt("positionFrag");
//        }
//
//        // Replace item on fragment
//        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getContext());
//        mNotes.addAll(myDatabaseHelper.getAllNotes());
//        note = mNotes.get(position);
//        if (note.getNoteImageUri() != null) {
//            Picasso.with(getContext()).load(note.getNoteImageUri()).into(imgAvatar);
//        } else {
//            imgAvatar.setImageResource(R.drawable.img_nullavatar);
//        }
//        tvTitle.setText(note.getNoteTitle());
//        tvContent.setText(note.getNoteContent());
//
//        final int finalPosition = position;
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(getContext(), AddEditNoteActivity.class);
////                Bundle bundle = new Bundle();
////                bundle.putParcelable("note_item", mNotes.get(finalPosition));
////                intent.putExtra("mNotes", bundle);
////                intent.putExtra("editNote", NOTE_EDIT);
////                startActivityForResult(intent, 1);
//                    AddEditNoteActivity_.intent(getContext()).note(mNotes.get(finalPosition)).noteState(NOTE_EDIT).start();
//            }
//        });
//
//        return view;
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                boolean needRefresh = data.getBooleanExtra("needRefresh", true);
//                if (needRefresh) {
//                    this.mNotes.clear();
//                    MyDatabaseHelper db = new MyDatabaseHelper(getContext());
//                    ArrayList<Note> list = db.getAllNotes();
//                    this.mNotes.addAll(list);
//                    // Notify data set Changed.
////                    mAdapter = new ViewpagerInner3Adapter(getChildFragmentManager(), getContext());
//                    NoteAdapter mAdapter = new NoteAdapter(getContext(), mNotes);
//                    mAdapter.notifyDataSetChanged();
//                    Log.d("Refresh", "onActivityResult: ------- fragment3Item");
//                }
//            }
//        }
//    }
}
