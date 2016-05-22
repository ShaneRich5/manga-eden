package com.indigo.manga.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.indigo.manga.R;
import com.indigo.manga.adapters.MangaAdapter;
import com.indigo.manga.network.VolleySingleton;
import com.indigo.manga.views.DividerItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.coordinator_main) CoordinatorLayout coordinatorMain;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_manga) RecyclerView recyclerManga;

    public static final String TAG = MainActivity.class.getSimpleName();
    private static String requestTag = "manga_list";
    private MangaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupRecyclerView();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                generateUrl(0, 25),
                response -> {
                    Log.d(TAG, response.toString());
//                    mAdapter.addMangaList();
                },
                error -> {
                    Log.d(TAG, error.getMessage());
                });

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    private void setupRecyclerView() {
        mAdapter = new MangaAdapter(new ArrayList<>());
        recyclerManga.setLayoutManager(new LinearLayoutManager(this));
        recyclerManga.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerManga.setAdapter(mAdapter);
    }

    private String generateUrl(int page, int amount) {
        return "https://www.mangaeden.com/api/list/0/?p=" + page + "&l=" + amount;
    }
}
