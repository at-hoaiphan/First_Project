package com.example.gio.firstproject.activities;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gio.firstproject.NameFragmentDialog;
import com.example.gio.firstproject.NameFragmentDialog_;
import com.example.gio.firstproject.OnSwipeTouchListener;
import com.example.gio.firstproject.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@OptionsMenu(R.menu.menu_toolbar)
@EActivity(R.layout.activity_ui_main_navigate)
public class UIActivity extends AppCompatActivity implements NameFragmentDialog.NameDialogFragmentListener {

    @ViewById(R.id.activity_ui)
    RelativeLayout relativeLayout;
    @ViewById(R.id.tvFagment)
    TextView tvCenter;
    @ViewById(R.id.drawer_layout_navigation)
    DrawerLayout drawerLayout;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.btnBack)
    Button btnBack;
    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @AfterViews
    void afterViews() {
        toolbar.setTitle("");
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
                Toast.makeText(UIActivity.this, "bottom", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Click(R.id.btnBack)
    void clickBtnBack() {
        finish();
    }

    @Click(R.id.fab)
    void clickBtnFab(View v) {
        Snackbar.make(v, "I'm a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UIActivity.this, "Snackbar Action", Toast.LENGTH_LONG).show();
            }
        }).show();
    }

    @OptionsItem(R.id.mnDialog)
    void selectDialogItem() {
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
    }

    @OptionsItem(R.id.mnFragmentDialog)
    void selectFragmentDialogItem() {
        FragmentManager fm = getFragmentManager();
        NameFragmentDialog editNameDialog = NameFragmentDialog_.builder().build();
        editNameDialog.show(fm, "fragment_edit_name");
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        tvCenter.setText("Hello " + inputText);
        Toast.makeText(this, "Hello, " + inputText, Toast.LENGTH_SHORT).show();
    }
}
