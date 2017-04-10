package com.example.gio.firstproject.activities;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio.firstproject.NameFragmentDialog;
import com.example.gio.firstproject.OnSwipeTouchListener;
import com.example.gio.firstproject.R;

public class UIActivity extends AppCompatActivity implements NameFragmentDialog.NameDialogFragmentListener, View.OnTouchListener {

    private TextView tvTitle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_main_navigate);

        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_ui);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tvFagment);
        toolbar.setTitle("");

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                relativeLayout.setBackgroundColor(Color.parseColor("#BDB0C3F6"));
                Snackbar.make(v, "I'm a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(UIActivity.this, "Snackbar Action", Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });
//        setSupportActionBar(toolbar);


        // onTouch Event

        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {

            }

            public void onSwipeRight() {
                drawerLayout.openDrawer(GravityCompat.START);
            }

            public void onSwipeLeft() {
                Toast.makeText(UIActivity.this, "left", Toast.LENGTH_LONG).show();
            }

            public void onSwipeBottom() {
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(UIActivity.this, "bottom", Toast.LENGTH_LONG).show();
            }
        });
    }

    //inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Bắt sự kiện cho các item Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnDialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Dialog");
                builder.setMessage("Lạc trôi")
                        .setCancelable(false)
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.mnFragmentDialog:
                showEditDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        NameFragmentDialog editNameDialog = new NameFragmentDialog();
        editNameDialog.show(fm, "fragment_edit_name");
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        tvTitle.setText("Hello " + inputText);
        Toast.makeText(this, "Hello, " + inputText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
