package com.vamshigollapelly.sportsfeedapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewsItem item);

    @Delete
    void delete(NewsItem item);

    @Query("SELECT * FROM bookmarks")
    List<NewsItem> getAll();

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)")
    boolean isBookmarked(int id);
}