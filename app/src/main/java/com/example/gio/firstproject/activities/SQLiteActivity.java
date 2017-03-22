package com.example.gio.firstproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.NoteAdapter;
import com.example.gio.firstproject.model.Note;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class SQLiteActivity extends AppCompatActivity implements NoteAdapter.MyOnClickListener{

    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;


    private static final int MY_REQUEST_CODE = 1000;

    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NoteAdapter noteAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_activity_main);

        // Get ListView object from xml
        RecyclerView rlListItem = (RecyclerView) findViewById(R.id.rlListNote);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultNotesIfNeed();

        mNotes.addAll(db.getAllNotes());
        Log.d("bla", "onCreate: " + mNotes.get(2).getNoteTitle());
        noteAdapter = new NoteAdapter(this, mNotes);
        // Đăng ký Adapter cho RecyclerView.
        rlListItem.setAdapter(noteAdapter);
        //RecyclerView scroll vertical
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rlListItem.setLayoutManager(linearLayoutManager);

        // Đăng ký Context menu cho ListView.
//        registerForContextMenu(this.rlListItem);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Select The Action");

        // groupId, itemId, order, title
        menu.add(0, MENU_ITEM_VIEW, 0, "View Note");
        menu.add(0, MENU_ITEM_CREATE, 1, "Create Note");
        menu.add(0, MENU_ITEM_EDIT, 2, "Edit Note");
        menu.add(0, MENU_ITEM_DELETE, 4, "Delete Note");
    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo
//                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//
////        final Note selectedNote = (Note) this.listView.getItemAtPosition(info.position);
////        final Note selectedNote = rlListItem.get
//
//        if (item.getItemId() == MENU_ITEM_VIEW) {
//            Toast.makeText(getApplicationContext(), selectedNote.getNoteContent(), Toast.LENGTH_LONG).show();
//        } else if (item.getItemId() == MENU_ITEM_CREATE) {
//            Intent intent = new Intent(this, AddEditNoteActivity.class);
//
//            // Start AddEditNoteActivity, có phản hồi.
//            this.startActivityForResult(intent, MY_REQUEST_CODE);
//        } else if (item.getItemId() == MENU_ITEM_EDIT) {
//            Intent intent = new Intent(this, AddEditNoteActivity.class);
//            intent.putExtra("note", selectedNote);
//
//            // Start AddEditNoteActivity, có phản hồi.
//            this.startActivityForResult(intent, MY_REQUEST_CODE);
//        } else if (item.getItemId() == MENU_ITEM_DELETE) {
//            // Hỏi trước khi xóa.
//            new AlertDialog.Builder(this)
//                    .setMessage(selectedNote.getNoteTitle() + ". Are you sure you want to delete?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            deleteNote(selectedNote);
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();
//        } else {
//            return false;
//        }
//        return true;
//    }

    // Người dùng đồng ý xóa một Note.
    private void deleteNote(Note note) {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteNote(note);
        this.mNotes.remove(note);
        // Refresh ListView.
        this.noteAdapter.notifyDataSetChanged();
    }

    // Khi AddEditNoteActivity hoàn thành, nó gửi phản hồi lại.
    // (Nếu bạn đã start nó bằng cách sử dụng startActivityForResult()  )
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            // Refresh ListView
            if (needRefresh) {
                this.mNotes.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(this);
                ArrayList<Note> list = db.getAllNotes();
                this.mNotes.addAll(list);
                // Thông báo dữ liệu thay đổi (Để refresh ListView).
                this.noteAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SQLite Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onClick(int id) {

    }
}
