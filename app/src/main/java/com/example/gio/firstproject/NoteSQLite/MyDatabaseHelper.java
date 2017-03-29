package com.example.gio.firstproject.NoteSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gio.firstproject.model.Note;

import java.util.ArrayList;

/**
 * Created by Gio on 3/17/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Db name
    private static final String DATABASE_NAME = "Note_Manager";

    // DB Version
    private static final int DATABASE_VERSION = 1;

    // DB Table
    private static final String TABLE = "Note";

    // Column
    private static final String COLUMN_NOTE_ID ="Note_Id";
    private static final String COLUMN_NOTE_TITLE ="Note_Title";
    private static final String COLUMN_NOTE_CONTENT = "Note_Content";
    private static final String COLUMN_NOTE_URI = "Note_Uri";
    private static final String COLUMN_NOTE_FAVOURITE = "Note_Favourite";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: table");
        // Query string
        String createQuery = "CREATE TABLE " + TABLE + "("
                + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NOTE_TITLE + " TEXT,"
                + COLUMN_NOTE_CONTENT + " TEXT,"
                + COLUMN_NOTE_URI + " TEXT,"
                + COLUMN_NOTE_FAVOURITE + " BOOLEAN )";

        // ExecuteSQL
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: table");

        //Drop old table if it's exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);

        // Recreate
        onCreate(db);
    }

    // Nếu trong bảng Note chưa có dữ liệu,
    // Trèn vào mặc định 2 bản ghi.
    public void createDefaultNotesIfNeed()  {
        int count = this.getNotesCount();
        if(count ==0 ) {
            Note note1 = new Note("First Note", "Content Note 1");
            Note note2 = new Note("Second Note", "Content Note 2");
            Note note3 = new Note("Third Note", "Content Note 3");
            Note note4 = new Note("abc Note", "Content");
            Note note5 = new Note("5th Note", "Content Note 5");
            Note note6 = new Note("6th Note", "Content Note 6");
            Note note7 = new Note("7th Note", "Content Note 7");
            this.addNote(note1);
            this.addNote(note2);
            this.addNote(note3);
            this.addNote(note4);
            this.addNote(note5);
            this.addNote(note6);
            this.addNote(note7);
        }
    }

    // Add Note
    public void addNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());
        values.put(COLUMN_NOTE_URI, note.getNoteImageUri());
        values.put(COLUMN_NOTE_FAVOURITE, note.isNoteFavourite());

        // Insert a record into Table
        db.insert(TABLE, null, values);

        // Close connect database.
        db.close();
    }

    // Get Note
    public Note getNote(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE, new String[] { COLUMN_NOTE_ID,
                        COLUMN_NOTE_TITLE, COLUMN_NOTE_CONTENT, COLUMN_NOTE_URI, COLUMN_NOTE_FAVOURITE }, COLUMN_NOTE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Note note = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Boolean.parseBoolean(cursor.getString(4)));

        // return note
        return note;
    }

    // Get all Note
    public ArrayList<Note> getAllNotes() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... ");

        ArrayList<Note> noteList = new ArrayList<Note>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Surf on cusor and Add into list.
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setNoteId(Integer.parseInt(cursor.getString(0)));
                note.setNoteTitle(cursor.getString(1));
                note.setNoteContent(cursor.getString(2));
                note.setNoteImageUri(cursor.getString(3));
                note.setNoteFavourite(Boolean.parseBoolean(cursor.getString(4)));

                // Add into list.
                noteList.add(note);
                Log.i(TAG, "getNote--- " + cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // return note list
        return noteList;
    }

    // Get Note count
    public int getNotesCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ..." );

        String countQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // Return count
        return count;
    }

    // Update Note
    public int updateNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());
        values.put(COLUMN_NOTE_URI, note.getNoteImageUri());
        values.put(COLUMN_NOTE_FAVOURITE, note.isNoteFavourite());
        // updating row
        return db.update(TABLE, values, COLUMN_NOTE_ID + " = ?", new String[]{String.valueOf(note.getNoteId())});
    }

    // Delete Note
    public void deleteNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getNoteTitle() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, COLUMN_NOTE_ID + " = ?", new String[] { String.valueOf(note.getNoteId()) });
        db.close();
    }
}
