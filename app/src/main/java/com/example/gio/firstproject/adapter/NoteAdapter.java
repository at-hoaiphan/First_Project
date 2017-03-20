package com.example.gio.firstproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private ArrayList<Note> notes;
    private LayoutInflater inflater;

    //Hàm tạo của custom
    public NoteAdapter(Context context, ArrayList<Note> notes){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.notes = notes;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note mNote = notes.get(position);
        holder.tvTitle.setText(mNote.getNoteTitle());
        Log.d("adapterNote", "onBindViewHolder: "+mNote.getNoteTitle());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface MyOnClickListener {
        void onClick(int id);
    }
}
