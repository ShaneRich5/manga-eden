package com.indigo.manga.activities;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.indigo.manga.R;
import com.indigo.manga.adapters.MangaAdapter;
import com.indigo.manga.listeners.EndlessRecyclerOnScrollListener;
import com.indigo.manga.model.Manga;
import com.indigo.manga.network.VolleySingleton;
import com.indigo.manga.utils.Constants;
import com.indigo.manga.views.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.coordinator_main) CoordinatorLayout coordinatorMain;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_manga) RecyclerView recyclerManga;

    public static final String TAG = MainActivity.class.getSimpleName();
    private static String requestTag = "manga_list";
    private MangaAdapter mAdapter;
    private int pagesLoaded = 0;

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
                    try {
                        Type listType = new TypeToken<ArrayList<Manga>>() {}.getType();
                        List<Manga> mangaList = new Gson().fromJson(response.getJSONArray("manga").toString(), listType);
                        mAdapter.addMangaList(mangaList);
                        ++pagesLoaded;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.d(TAG, error.getMessage());
                });

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mAdapter = new MangaAdapter(new ArrayList<>(), this, v -> {
            int position = recyclerManga.getChildLayoutPosition(v);
            Manga manga = mAdapter.getManga(position);
            startActivity(new Intent(MainActivity.this, DetailsActivity.class)
                    .putExtra(Manga.class.getName(), manga));
        });

        recyclerManga.setLayoutManager(linearLayoutManager);
        recyclerManga.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerManga.setAdapter(mAdapter);
        recyclerManga.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                        generateUrl(currentPage, 25),
                        response -> {
                            try {
                                Type listType = new TypeToken<ArrayList<Manga>>() {}.getType();
                                List<Manga> mangaList = new Gson().fromJson(response.getJSONArray("manga").toString(), listType);
                                mAdapter.addMangaList(mangaList);
                                ++pagesLoaded;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> {
                            Log.d(TAG, error.getMessage());
                        });

                VolleySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
            }
        });
    }

    private String generateUrl(int page, int amount) {
        return Constants.MANGA_URL + "?p=" + page + "&l=" + amount;
    }
}
