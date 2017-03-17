package com.example.gio.firstproject.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gio.firstproject.NoteSQLite.MyDatabaseHelper;
import com.example.gio.firstproject.model.Note;
import com.example.gio.firstproject.R;

import java.util.ArrayList;

public class SQLiteActivity extends AppCompatActivity {

    private ListView listView;

    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;


    private static final int MY_REQUEST_CODE = 1000;

    private final ArrayList<Note> notes = new ArrayList<>();
    private ArrayAdapter<Note> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_activity_main);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultNotesIfNeed();

        ArrayList<Note> list = db.getAllNotes();
        this.notes.addAll(list);

        // Định nghĩa một Adapter.
        // 1 - Context
        // 2 - Layout cho các dòng.
        // 3 - ID của TextView mà dữ liệu sẽ được ghi vào
        // 4 - Danh sách dữ liệu.

        this.listViewAdapter = new ArrayAdapter<>(this, R.layout.list_note, R.id.tvTite, this.notes);

        // Đăng ký Adapter cho ListView.
        this.listView.setAdapter(this.listViewAdapter);

        // Đăng ký Context menu cho ListView.
        registerForContextMenu(this.listView);
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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Note selectedNote = (Note) this.listView.getItemAtPosition(info.position);

        if (item.getItemId() == MENU_ITEM_VIEW) {
            Toast.makeText(getApplicationContext(), selectedNote.getNoteContent(), Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == MENU_ITEM_CREATE) {
            Intent intent = new Intent(this, AddEditNoteActivity.class);

            // Start AddEditNoteActivity, có phản hồi.
            this.startActivityForResult(intent, MY_REQUEST_CODE);
        } else if (item.getItemId() == MENU_ITEM_EDIT) {
            Intent intent = new Intent(this, AddEditNoteActivity.class);
            intent.putExtra("note", selectedNote);

            // Start AddEditNoteActivity, có phản hồi.
            this.startActivityForResult(intent, MY_REQUEST_CODE);
        } else if (item.getItemId() == MENU_ITEM_DELETE) {
            // Hỏi trước khi xóa.
            new AlertDialog.Builder(this)
                    .setMessage(selectedNote.getNoteTitle() + ". Are you sure you want to delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteNote(selectedNote);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            return false;
        }
        return true;
    }

    // Người dùng đồng ý xóa một Note.
    private void deleteNote(Note note) {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteNote(note);
        this.notes.remove(note);
        // Refresh ListView.
        this.listViewAdapter.notifyDataSetChanged();
    }

    // Khi AddEditNoteActivity hoàn thành, nó gửi phản hồi lại.
    // (Nếu bạn đã start nó bằng cách sử dụng startActivityForResult()  )
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            // Refresh ListView
            if (needRefresh) {
                this.notes.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(this);
                ArrayList<Note> list = db.getAllNotes();
                this.notes.addAll(list);
                // Thông báo dữ liệu thay đổi (Để refresh ListView).
                this.listViewAdapter.notifyDataSetChanged();
            }
        }
    }
}
