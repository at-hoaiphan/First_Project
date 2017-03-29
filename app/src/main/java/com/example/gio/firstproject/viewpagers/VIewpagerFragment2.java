package com.example.gio.firstproject.viewpagers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.model.Note;
import com.squareup.picasso.Picasso;

/**
 * Copyright by Gio.
 * Created on 3/23/2017.
 */

public class ViewpagerFragment2 extends Fragment {

    public ViewpagerFragment2() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_fragment_2, container, false);

        TextView tvTitleNote = (TextView) view.findViewById(R.id.tvTitle);
        ImageView imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);

        MyDatabaseHelper db = new MyDatabaseHelper(getContext());
        Note note = db.getNote(1);
        tvTitleNote.setText(note.getNoteTitle());
        Picasso.with(getContext()).load(note.getNoteImageUri()).into(imgAvatar);
        return view;
    }
}
