package com.example.gio.firstproject.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.UserAdapter;
import com.example.gio.firstproject.model.Header;
import com.example.gio.firstproject.model.ItemUser;
import com.example.gio.firstproject.model.ListItem;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Created by Gio on 3/10/2017.
 */

public class ListUserActivity extends AppCompatActivity implements UserAdapter.MyOnClickListener, UserAdapter.IsOnFavouriteListener {
    private UserAdapter mUserAdapter;
    private ArrayList<ListItem> mListItems;
    private LinearLayoutManager linearLayoutManager;
    private android.os.Handler mHandler;
    private RelativeLayout rlProgress;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more item_user.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        mHandler = new android.os.Handler();
        RecyclerView recyclerViewUsers = (RecyclerView) findViewById(R.id.recylerView);
        rlProgress = (RelativeLayout) findViewById(R.id.rlProgress);

        //create ItemUser data
        mListItems = getListItems();
        mUserAdapter = new UserAdapter(this, mListItems, this);

        recyclerViewUsers.setAdapter(mUserAdapter);
        mUserAdapter.setMyOnClickListener(new UserAdapter.MyOnClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(ListUserActivity.this, DetailItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("user_item", (Parcelable) mListItems.get(id));
                intent.putExtra("index", id);
                intent.putExtra("mListItems", bundle);
                startActivityForResult(intent, 1);
            }
        });

        //RecyclerView scroll vertical
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewUsers.setLayoutManager(linearLayoutManager);

        recyclerViewUsers.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisibleItem == mListItems.size() - 1) {
//                    prgLoading.setVisibility(View.VISIBLE);
                    rlProgress.setVisibility(View.VISIBLE);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("loading item ", "run: ");
                            int nextItem = mListItems.size();
                            int endItem = nextItem + 20;
                            for (int i = nextItem; i < endItem; i++) {
                                mListItems.add(getItemById(i));
                            }
                            mUserAdapter.notifyItemInserted(mListItems.size());
//                            prgLoading.setVisibility(View.GONE);
                            rlProgress.setVisibility(View.GONE);
                        }
                    }, 1000);
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more item_user.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private ArrayList<ListItem> getListItems() {
        ArrayList<ListItem> listItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            boolean isFavourite = false;
            if (i % 2 == 0) {
                isFavourite = true;
            }
            if (i % 3 == 0) {
                listItems.add(new Header("Header" + i));
            } else {
                listItems.add(new ItemUser(i, "at-hoaiphan", "Asiantech", "Intern", "BKĐN", isFavourite));
            }
        }
        return listItems;
    }

    private ListItem getItemById(int position) {
        ListItem item;
        boolean isFavourite = false;
        if (position % 4 == 0) {
            isFavourite = true;
        }
        if (position % 3 == 0) {
            item = new Header("new Header");
        } else {
            item = new ItemUser(position, "at-hoaiphan", "Asiantech", "Intern", "BKĐN", isFavourite);
        }
        return item;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean checkFavourite = data.getBooleanExtra("isFavourite", false);
                int index = data.getIntExtra("index", -1);
                if (index != -1) {
                    ItemUser user = (ItemUser) mListItems.get(index);
                    user.setIsFavourite(checkFavourite);
                    mListItems.set(index, mListItems.get(index));
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
     * See https://g.co/AppIndexing/AndroidStudio for more item_user.
     */
    private Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ItemUser Page") // TODO: Define a title for the content shown.
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
        // See https://g.co/AppIndexing/AndroidStudio for more item_user.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more item_user.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onFavouriteClick() {
        mUserAdapter.notifyDataSetChanged();
    }
}
