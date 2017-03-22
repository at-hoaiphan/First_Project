package com.example.gio.firstproject.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.model.Note;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gio on 1/4/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private ArrayList<Note> notes = new ArrayList<>();
    private Context mContext;
    private NoteOnClickListener mNoteOnClickListener;

    //Hàm tạo của custom
    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.notes = notes;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(notes.get(position).getNoteTitle());
        holder.tvContent.setText((notes.get(position).getNoteContent()));

        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        String imageUri = notes.get(position).getNoteImageUri();
        if (imageUri.isEmpty()) {
            holder.imgNote.setImageResource(R.drawable.img_nullavatar);
        } else {
            Log.d("load image-", "onBindViewHolder: " + imageUri);
//            Picasso.with(mContext).load(imageUri).into(holder.imgNote);
            Picasso.with(mContext).load(imageUri).placeholder(R.drawable.ic_setting).error(R.drawable.ic_lock).into(holder.imgNote);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        ImageView imgNote;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            imgNote = (ImageView) itemView.findViewById(R.id.imgAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNoteOnClickListener.onClick(getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public interface NoteOnClickListener {
        void onClick(int id);
    }

    public void setNoteOnClickListener(NoteOnClickListener mNoteOnClickListener) {
        this.mNoteOnClickListener = mNoteOnClickListener;
    }
}
