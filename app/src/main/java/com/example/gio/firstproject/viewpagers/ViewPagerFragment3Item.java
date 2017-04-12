package com.example.gio.firstproject.viewpagers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.activities.AddEditNoteActivity_;
import com.example.gio.firstproject.adapter.NoteAdapter;
import com.example.gio.firstproject.model.Note;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Gio on 3/24/2017.
 */

public class ViewPagerFragment3Item extends Fragment implements View.OnClickListener{

    private static final String TAG ="ViewPagerFragment3Item" ;
    private ImageView imgAvatar;
    private TextView tvTitle;
    private TextView tvContent;
    private Note note;
    private NoteAdapter mAdapter;
    private ArrayList<Note> mNotes = new ArrayList<>();

    private static final int NOTE_EDIT = 22;

    public ViewPagerFragment3Item() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_note, container, false);

        imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        int position = 0;

        if(getArguments()!=null){
            position = getArguments().getInt("positionFrag");
            Log.d(TAG, "onCreateView: id"+position);
        }

        // Replace item on fragment
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getContext());
        mNotes.addAll(myDatabaseHelper.getAllNotes());
        note = mNotes.get(position);
        if (note.getNoteImageUri() != null) {
            Picasso.with(getContext()).load(note.getNoteImageUri()).into(imgAvatar);
        } else {
            imgAvatar.setImageResource(R.drawable.img_nullavatar);
        }
        tvTitle.setText(note.getNoteTitle());
        tvContent.setText(note.getNoteContent());

        final int finalPosition = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), AddEditNoteActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("note_item", mNotes.get(finalPosition));
//                intent.putExtra("mNotes", bundle);
//                intent.putExtra("editNote", NOTE_EDIT);
//                startActivityForResult(intent, 1);
                    AddEditNoteActivity_.intent(getContext()).note(mNotes.get(finalPosition)).noteState(NOTE_EDIT).start();
            }
        });

        return view;
    }

    public ViewPagerFragment3Item getPosition(int position) {
        ViewPagerFragment3Item fragment = new ViewPagerFragment3Item();
        Bundle bundle = new Bundle();
        bundle.putInt("positionFrag", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean needRefresh = data.getBooleanExtra("needRefresh", true);
                if (needRefresh) {
                    this.mNotes.clear();
                    MyDatabaseHelper db = new MyDatabaseHelper(getContext());
                    ArrayList<Note> list = db.getAllNotes();
                    this.mNotes.addAll(list);
                    // Notify data set Changed.
//                    mAdapter = new ViewpagerInner3Adapter(getChildFragmentManager(), getContext());
                    mAdapter = new NoteAdapter(getContext(), mNotes);
                    mAdapter.notifyDataSetChanged();
                    Log.d("Refresh", "onActivityResult: ------- fragment3Item");
                }
            }
        }
    }
}
