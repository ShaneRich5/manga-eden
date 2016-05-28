package com.indigo.manga.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.indigo.manga.R;
import com.indigo.manga.model.Manga;
import com.indigo.manga.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.*;

/**
 *
 */
public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {
    private final Context mContext;
    private List<Manga> mMangaList;
    private OnClickListener mOnItemClickListener;

    public MangaAdapter(List<Manga> mangaList, Context context, OnClickListener listener) {
        mMangaList = mangaList;
        mContext = context;
        mOnItemClickListener = listener;
    }

    public void addMangaList(List<Manga> mangaList) {
        mMangaList.addAll(mangaList);
        notifyDataSetChanged();
    }

    public Manga getManga(int position) {
        return mMangaList.get(position);
    }

    public void setListener(OnClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public MangaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_manga, parent, false);
        itemView.setOnClickListener(mOnItemClickListener);
        return new MangaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MangaViewHolder holder, int position) {
        Log.i("MangaAdapter", mMangaList.get(position).toString());
        Manga currentManga = mMangaList.get(position);
        holder.mTextViewTitle.setText(currentManga.getTitle());
        Glide.with(mContext)
                .load(Constants.IMAGE_URL + currentManga.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mImageViewThumbnail);
    }

    @Override
    public int getItemCount() {
        return mMangaList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Manga manga, int position);
    }

    public final class MangaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title) TextView mTextViewTitle;
        @BindView(R.id.image_thumbnail) ImageView mImageViewThumbnail;

        public MangaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
