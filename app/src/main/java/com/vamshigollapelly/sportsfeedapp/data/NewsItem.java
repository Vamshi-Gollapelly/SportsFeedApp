package com.vamshigollapelly.sportsfeedapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarks")
public class NewsItem {

    @PrimaryKey
    public int id;
    public String title;
    public String description;
    public String category;
    public int imageRes;

    public NewsItem(int id, String title, String description, String category, int imageRes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.imageRes = imageRes;
    }
}