package com.example.tiktok;

import com.google.gson.annotations.SerializedName;

public class VideoItem {
    @SerializedName("_id")
    public String id;
    @SerializedName("feedurl")
    public String videoUrl;
    @SerializedName("nickname")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public int likes;
    @SerializedName("avatar")//头像
    public String avatarUrl;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                "feedurl=" + videoUrl +
                ", name='" + name + '\'' +
                ", desc=" + description +
                ", likecount=" + likes +
                ", avatar=" + avatarUrl +
                '}';
    }
}
