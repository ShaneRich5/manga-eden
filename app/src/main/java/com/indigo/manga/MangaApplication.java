package com.indigo.manga;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Shane on 5/22/2016.
 */
public class MangaApplication extends Application {

    public static final String TAG = MangaApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static MangaApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MangaApplication getInstance() {
        return sInstance;
    }

}
