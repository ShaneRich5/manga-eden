package com.indigo.manga.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shane on 5/26/2016.
 */
public class Chapter {
    @SerializedName("images")
    private List<String> pages;
}
