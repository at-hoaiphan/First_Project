package com.example.gio.firstproject.viewpagers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.activities.ViewPagerActivity;
import com.example.gio.firstproject.model.Note;

/**
 * Created by Gio on 3/23/2017.
 */

public class ViewPagerFragment1DetailNote extends Fragment {

    private ViewPagerActivity viewPagerActivity;
    private Note note;
    private EditText edtTitle;
    private EditText edtContent;
    private Button btnAddEdit;
    private Button btnDeleteNote;
    private ImageView imgAvatar;

    public ViewPagerFragment1DetailNote() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_edit_note, container, false);

        edtTitle = (EditText) view.findViewById(R.id.edtNoteTitle);
        edtContent = (EditText) view.findViewById(R.id.edtNoteContent);
        btnAddEdit = (Button) view.findViewById(R.id.btnAddEdit);
        btnDeleteNote = (Button) view.findViewById(R.id.btnDelete);
        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);


//            note = getIntent().getBundleExtra("mNotes").getParcelable("note_item");
//            edtTitle.setText(note.getNoteTitle());
//            edtContent.setText(note.getNoteContent());
//            if (note.getNoteImageUri() != null) {
//                Picasso.with(viewPagerActivity).load(note.getNoteImageUri()).into(imgAvatar);
//            } else {
//                imgAvatar.setImageResource(R.drawable.img_nullavatar);
//            }
//            btnAddEdit.setText("Edit");
//            btnDeleteNote.setVisibility(View.VISIBLE);
//
//
//        myDatabaseHelper = new MyDatabaseHelper(this);
//        btnAddEdit.setOnClickListener(this);
//        btnDeleteNote.setOnClickListener(this);
//        imgAvatar.setOnClickListener(this);


        return view;
    }
}
