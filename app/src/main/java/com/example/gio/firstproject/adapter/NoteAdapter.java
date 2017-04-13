package com.example.gio.firstproject.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.model.Note;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Copyright by Gio.
 * Created on 1/4/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private ArrayList<Note> mNotes = new ArrayList<>();
    private Context mContext;
    private NoteOnClickListener mNoteOnClickListener;

    //Hàm tạo của custom
    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.mNotes = notes;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(mNotes.get(position).getNoteTitle());
        holder.tvContent.setText((mNotes.get(position).getNoteContent()));

        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        String imageUri = mNotes.get(position).getNoteImageUri();
        if (java.util.Objects.equals(imageUri, "") || imageUri == null) {
            holder.imgNote.setImageResource(R.drawable.img_nullavatar);
        } else {
            Picasso.with(mContext).load(new File(imageUri))
                    .placeholder(R.drawable.ic_setting)
                    .error(R.drawable.ic_lock)
                    .into(holder.imgNote);
//            holder.imgNote.setImageDrawable(Drawable.createFromPath(imageUri));
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        ImageView imgNote;
        ImageButton isFavourite;

        MyViewHolder(final View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            imgNote = (ImageView) itemView.findViewById(R.id.imgAvatar);
            isFavourite = (ImageButton) itemView.findViewById(R.id.imgBtnFavourite);
//            isFavourite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Note note = (Note) mNotes.get(getAdapterPosition());
//                    if (note.is()) {
//                        imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
//                        user.setIsFavourite(false);
//                        Log.d(TAG, "ItemUser set " + user.getIsFavourite());
//                    } else {
//                        imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_stargold);
//                        user.setIsFavourite(true);
//                    }
//                    mIsOnFavouriteListener.onFavouriteClick();
//                    Log.d(TAG, "click Item_Detail1 " + user.getId() + user.getName() + user.getCompany() + user.getMajor() + user.getAbout() + user.getIsFavourite());
//                }
//            });
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
        return mNotes.size();
    }

    public interface NoteOnClickListener {
        void onClick(int id);
    }

    public void setNoteOnClickListener(NoteOnClickListener mNoteOnClickListener) {
        this.mNoteOnClickListener = mNoteOnClickListener;
    }
}
