package com.vamshigollapelly.sportsfeedapp.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NewsItem.class}, version = 1, exportSchema = false)
public abstract class BookmarkDatabase extends RoomDatabase {

    public abstract BookmarkDao bookmarkDao();
    private static BookmarkDatabase INSTANCE;

    public static BookmarkDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (BookmarkDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            BookmarkDatabase.class,
                            "bookmark_db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}