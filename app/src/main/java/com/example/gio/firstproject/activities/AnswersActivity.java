package com.example.gio.firstproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.adapter.AnswersAdapter;
import com.example.gio.firstproject.model.Item;
import com.example.gio.firstproject.retrofit_client.ApiUtils;
import com.example.gio.firstproject.retrofit_client.SOAnswersResponse;
import com.example.gio.firstproject.retrofit_client.SOService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Copyright by Gio.
 * Created on 4/5/2017.
 */
@EActivity(R.layout.activity_answer_api)
public class AnswersActivity extends AppCompatActivity{
    @ViewById(R.id.rv_answers)
    RecyclerView mRecyclerView;

    private AnswersAdapter mAdapter;
    private SOService mService;

    @AfterViews
    void afterViews() {
        mService = ApiUtils.getSOService();
        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {
            @Override
            public void onPostClick(long id) {
                Toast.makeText(AnswersActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        loadAnswers();
    }
    public void loadAnswers() {
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                }else {
//                    int statusCode  = response.code();
                    Log.d("MainActivity", "posts didn't load from API: ");
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
//                showErrorMessage();
                Log.d("MainActivity", "error loading from API");

            }
        });
    }


}
