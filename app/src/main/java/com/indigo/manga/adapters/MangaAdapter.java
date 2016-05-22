package com.indigo.manga.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.indigo.manga.R;
import com.indigo.manga.model.Manga;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shane on 5/22/2016.
 */
public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {
    private List<Manga> mMangaList;
    private OnItemClickListener mOnItemClickListener;

    public MangaAdapter(List<Manga> mangaList, OnItemClickListener listener) {
        mMangaList = mangaList;
        mOnItemClickListener = listener;
    }

    public MangaAdapter(List<Manga> mangaList) {
        this(mangaList, null);
    }

    public void addMangaList(List<Manga> mangaList) {
        mMangaList.addAll(mangaList);
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public MangaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MangaViewHolder holder, int position) {
        Manga currentManga = mMangaList.get(position);

    }

    @Override
    public int getItemCount() {
        return mMangaList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Manga manga, int position);
    }

    public final class MangaViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        @BindView(R.id.text_title) TextView mTextViewTitle;
        @BindView(R.id.image_thumbnail) ImageView mImageViewThumbnail;

        public MangaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) mOnItemClickListener.onItemClick(v, mMangaList.get(getLayoutPosition()), getLayoutPosition());
        }
    }
}
