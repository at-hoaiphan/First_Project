package com.example.gio.firstproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Gio on 3/17/2017.
 */

public class Note implements Serializable, Parcelable {
    private int noteId;
    private String noteTitle;
    private String noteContent;

    public Note() {
    }

    public Note(String noteTitle, String noteContent) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }


    protected Note(Parcel in) {
        noteId = in.readInt();
        noteTitle = in.readString();
        noteContent = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public int getNoteId() {

        return noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public Note(int noteId, String noteTitle, String noteContent) {

        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(noteId);
        dest.writeString(noteTitle);
        dest.writeString(noteContent);
    }
}
