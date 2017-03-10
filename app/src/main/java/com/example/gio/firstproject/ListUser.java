package com.example.gio.firstproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gio on 3/10/2017.
 */

public class ListUser extends AppCompatActivity {
    private RecyclerView recyclerViewUsers;
    private UserAdapter mUserAdapter;
    private List<User> mUsers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        recyclerViewUsers = (RecyclerView) findViewById(R.id.recylerView);

        //create User data
        mUsers = new ArrayList<>();
        mUsers.add(new User("at-hoaiphan", "Asiantech", "Intern"));
        mUsers.add(new User("at-nhanphan", "Asiantech", "Intern"));
        mUsers.add(new User("at-toannguyen", "Asiantech", "Intern"));
        mUsers.add(new User("at-haole", "Asiantech", "Intern"));
        mUsers.add(new User("at-haivo", "Asiantech", "Intern"));

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
