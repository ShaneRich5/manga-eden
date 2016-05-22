package com.indigo.manga.adapters;

import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.indigo.manga.model.Manga;

import java.util.List;

/**
 * Created by Shane on 5/22/2016.
 */
public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {
    private List<Manga> mMangaList;

    @Override
    public MangaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MangaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Manga manga, int position);
    }

    public final class MangaViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        public MangaViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
