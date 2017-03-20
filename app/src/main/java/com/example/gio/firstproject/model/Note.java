package com.example.gio.firstproject.model;

import java.io.Serializable;

/**
 * Created by Gio on 3/17/2017.
 */

public class Note implements Serializable {
    private int noteId;
    private String noteTitle;
    private String noteContent;

    public Note() {
    }

    public Note(String noteTitle, String noteContent) {

        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }


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
}
