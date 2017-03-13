package com.example.gio.firstproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Gio on 3/10/2017.
 */

public class ListUser extends AppCompatActivity {
    private RecyclerView recyclerViewUsers;
    private UserAdapter mUserAdapter;
    private ArrayList<User> mUsers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        recyclerViewUsers = (RecyclerView) findViewById(R.id.recylerView);

        //create User data
        mUsers = new ArrayList<>();
        mUsers.add(new User(1, "at-hoaiphan", "Asiantech", "Intern", "BKĐN", 1));
        mUsers.add(new User(2, "at-nhanphan", "Asiantech", "Intern", "BKĐN", 0));
        mUsers.add(new User(3, "at-toannguyen", "Asiantech", "Intern", "ABC", 0));
        mUsers.add(new User(4, "at-haole", "Asiantech", "Intern", "ABC", 0));
        mUsers.add(new User(5, "at-haivo", "Asiantech", "Intern", "APtech", 0));

        mUserAdapter = new UserAdapter(this, mUsers);
        recyclerViewUsers.setAdapter(mUserAdapter);

        //RecyclerView scroll vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewUsers.setLayoutManager(linearLayoutManager);

        //Show list in grid style
        //        GridLayoutManager gridLayoutManager = new GridLayoutManager(ListUser.this, 2);
        //        recyclerViewUsers.setLayoutManager(gridLayoutManager);
    }
}
