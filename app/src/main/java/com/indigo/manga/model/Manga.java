package com.indigo.manga.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shane on 5/22/2016.
 */
public class Manga implements Parcelable {
    @SerializedName("i")
    private String id;
    @SerializedName("a")
    private String alias;
    @SerializedName("im")
    private String image;
    @SerializedName("t")
    private String title;
    @SerializedName("c") 
    private List<String> categories;
    @SerializedName("ld")
    private String lastChapterDate;
    @SerializedName("s")
    private int status;
    @SerializedName("description")
    private String description;
    @SerializedName("author")
    private String author;

    public Manga() {
        // required for GSON
    }

    public Manga(Parcel in) {
        id = in.readString();
        alias = in.readString();
        image = in.readString();
        title = in.readString();
        lastChapterDate = in.readString();
        status = in.readInt();
        in.readStringList(categories);
        description = in.readString();
        author = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getLastChapterDate() {
        return lastChapterDate;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Alias: " + alias + "\n" +
                "Status: " + status + "\n" +
                "Categories: " + TextUtils.join(",", categories);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(alias);
        dest.writeString(image);
        dest.writeString(title);
        dest.writeString(lastChapterDate);
        dest.writeInt(status);
        dest.writeStringList(categories);
        dest.writeString(description);
        dest.writeString(author);
    }

    public static final Creator<Manga> CREATOR
            = new Creator<Manga>() {
        @Override
        public Manga createFromParcel(Parcel source) {
            return new Manga(source);
        }

        @Override
        public Manga[] newArray(int size) {
            return new Manga[size];
        }
    };
}
