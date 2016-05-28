package com.indigo.manga.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.indigo.manga.R;
import com.indigo.manga.model.Manga;
import com.indigo.manga.network.VolleySingleton;
import com.indigo.manga.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {

    public static final String TAG = DetailsActivity.class.getName();

    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.fab_favourite) FloatingActionButton fabFavourite;
    @BindView(R.id.image_banner) ImageView imageViewBanner;
    @BindView(R.id.recycler_chapters) RecyclerView recyclerChapter;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private Manga mManga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (null != extras) mManga = (Manga) extras.get(Manga.class.getName());

        initializeToolbar();
        requestMangaDetails();
    }

    private void requestMangaDetails() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                Constants.MANGA_DETAILS + mManga.getId(),
                response -> {
                    Log.i(TAG, response.toString());
                },
                error -> {
                    Log.i(TAG, error.getMessage());
                });
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        try {
            //noinspection ConstantConditions
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        toolbar.setTitle((mManga.getAlias().isEmpty()) ? mManga.getTitle() : mManga.getAlias());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Manga.class.getName(), mManga);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mManga = (Manga) savedInstanceState.get(Manga.class.getName());
        super.onRestoreInstanceState(savedInstanceState);
    }

    @OnClick(R.id.fab_favourite)
    public void toggleFavourite(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
