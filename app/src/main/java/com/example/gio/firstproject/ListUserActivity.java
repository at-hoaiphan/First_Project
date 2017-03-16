package com.example.gio.firstproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Created by Gio on 3/10/2017.
 */

public class ListUserActivity extends AppCompatActivity implements UserAdapter.MyOnClickListener {
    private View view;
    private RecyclerView recyclerViewUsers;
    private UserAdapter mUserAdapter;
    private ArrayList<User> mUsers;

    private LinearLayoutManager linearLayoutManager;
    private RelativeLayout bottomLayout;

    android.os.Handler mHandler;
    ProgressBar prgLoading;
    RelativeLayout rlProgress;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public ListUserActivity() {
    }
    // TODO: 3/14/2017 method LinearLayout


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        mHandler = new android.os.Handler();
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recylerView);
        prgLoading = (ProgressBar) findViewById(R.id.more_progress);
        rlProgress = (RelativeLayout) findViewById(R.id.rlProgress);

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


        recyclerViewUsers.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisibleItem == mUsers.size() - 1) {
//                    prgLoading.setVisibility(View.VISIBLE);
                    rlProgress.setVisibility(View.VISIBLE);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("131", "run: ");
                            int nextItem = mUsers.size();
                            int endItem = nextItem + 20;
                            for (int i = nextItem + 1; i < endItem; i++) {
                                mUsers.add(new User(i, "pvhoai", "Asiantech", "Intern", "BKĐN", true));
                            }
                            mUserAdapter.notifyItemInserted(mUsers.size());
//                            prgLoading.setVisibility(View.GONE);
                            rlProgress.setVisibility(View.GONE);
                        }
                    }, 3000);
                }

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    @Override
    public void onClick(int id) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ListUser Page") // TODO: Define a title for the content shown.
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
}
