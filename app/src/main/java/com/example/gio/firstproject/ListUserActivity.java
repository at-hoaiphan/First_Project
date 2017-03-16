package com.example.gio.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Gio on 3/10/2017.
 */

public class ListUserActivity extends AppCompatActivity {
    private View view;
    private RecyclerView recyclerViewUsers;
    private UserAdapter mUserAdapter;
    private ArrayList<User> mUsers;

    private LinearLayoutManager linearLayoutManager;
    private RelativeLayout bottomLayout;

    //variable for scroll listener
    private boolean userScrolled = true;
    int pastVisibleItem,totalItemCount;
    // TODO: 3/14/2017 method LinearLayout


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        recyclerViewUsers = (RecyclerView) findViewById(R.id.recylerView);

        //create User data
        mUsers = new ArrayList<>();
        mUsers.add(new User(1, "at-hoaiphan", "Asiantech", "Intern", "BKĐN", true));
        mUsers.add(new User(2, "at-nhanphan", "Asiantech", "Intern", "BKĐN", false));
        mUsers.add(new User(3, "at-toannguyen", "Asiantech", "Intern", "ABC", false));
        mUsers.add(new User(4, "at-haole", "Asiantech", "Intern", "ABC", false));
        mUsers.add(new User(5, "at-haivo", "Asiantech", "Intern", "APtech", false));
        mUsers.add(new User(6, "at-hoaiphan", "Asiantech", "Intern", "BKĐN", true));
        mUsers.add(new User(7, "at-nhanphan", "Asiantech", "Intern", "BKĐN", false));
        mUsers.add(new User(8, "at-toannguyen", "Asiantech", "Intern", "ABC", false));
        mUsers.add(new User(9, "at-haole", "Asiantech", "Intern", "ABC", false));
        mUsers.add(new User(10, "at-haivo", "Asiantech", "Intern", "APtech", false));
        mUsers.add(new User(11, "at-hoaiphan", "Asiantech", "Intern", "BKĐN", true));
        mUsers.add(new User(12, "at-nhanphan", "Asiantech", "Intern", "BKĐN", false));
        mUsers.add(new User(13, "at-toannguyen", "Asiantech", "Intern", "ABC", false));
        mUsers.add(new User(14, "at-haole", "Asiantech", "Intern", "ABC", false));
        mUsers.add(new User(15, "at-haivo", "Asiantech", "Intern", "APtech", false));
//        for (int i =0; i<10; i++) {
//            mUsers.add(new User(i, "pvhoai "+i,"Asiantech", "Intern", "BKĐN", i%4==0));
//        }

        mUserAdapter = new UserAdapter(this, mUsers, new UserAdapter.IsOnFavouriteListener() {
            @Override
            public void onFavouriteClick() {
                mUserAdapter.notifyDataSetChanged();
            }
        });
        recyclerViewUsers.setAdapter(mUserAdapter);
        mUserAdapter.setMyOnClickListener(new UserAdapter.MyOnClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(ListUserActivity.this, DetailItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("user_item", mUsers.get(id));
                intent.putExtra("index mUsers", id);
                intent.putExtra("mUsers", bundle);
//                    User user = mUsers.get(getAdapterPosition());
//                    Toast.makeText(mContext, user.getName(), Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, 1);
            }
        });

        //RecyclerView scroll vertical
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewUsers.setLayoutManager(linearLayoutManager);

        //Show list in grid style
        //        GridLayoutManager gridLayoutManager = new GridLayoutManager(ListUserActivity.this, 2);
        //        recyclerViewUsers.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean checkFavourite = data.getBooleanExtra("select isFavourite", false);
                int index = data.getIntExtra("index", -1);
                Log.d("rrytujyt", "index " + index);
                if (index != -1) {
                    mUsers.get(index).setIsFavourite(checkFavourite);
                    mUsers.set(index, mUsers.get(index));
                    mUserAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
