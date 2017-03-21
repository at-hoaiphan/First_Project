package com.example.gio.firstproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.model.Note;

import java.util.ArrayList;

/**
 * Created by Gio on 1/4/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder>{
    private ArrayList<Note> notes = new ArrayList<>();
    private Context mContext;
    private NoteOnClickListener mNoteOnClickListener;

    //Hàm tạo của custom
    public NoteAdapter(Context context, ArrayList<Note> notes){
        this.notes = notes;
        this.mContext =context;
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
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
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
