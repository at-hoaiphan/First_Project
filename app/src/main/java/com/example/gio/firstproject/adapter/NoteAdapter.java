package com.example.gio.firstproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gio.firstproject.model.Note;
import com.example.gio.firstproject.R;

import java.util.ArrayList;

/**
 * Created by Gio on 1/4/2017.
 */

public class NoteAdapter extends BaseAdapter{
    ArrayList<Note> notes;
    LayoutInflater inflater;

    //Hàm tạo của custom
    public NoteAdapter(Context context, ArrayList<Note> notes){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.notes = notes;

    }

    //trả về sô lượng phần tử được hiển thị trong listView
    @Override
    public int getCount() {
        return notes.size();
    }

    //Trả về đối tượng được lấy theo vị trí
    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    //Hiển thị giao diện của ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Lấy ra đối tượng cần hiển thị ở vị trí thứ position
        Note item = notes.get(position);
        //Khai báo các component
        TextView tvTitle, tvContent;
        //Khởi tạo View
        if (convertView==null){
            convertView = inflater.inflate(R.layout.list_note,parent,false);
        }

        tvTitle = (TextView) convertView.findViewById(R.id.tvTite);
        tvTitle.setText(item.getNoteTitle());
        return convertView;
    }
}
