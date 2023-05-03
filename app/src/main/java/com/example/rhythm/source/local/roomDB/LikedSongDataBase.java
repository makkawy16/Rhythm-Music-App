package com.example.rhythm.source.local.roomDB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities =  {LikedSong.class} , version =  1)
public abstract class LikedSongDataBase extends RoomDatabase {


    public abstract LikedSongsDAO getLikedSongDao();

    private static LikedSongDataBase likedSongDataBase;

    public static LikedSongDataBase getLikedSongDataBase(Context context) {
        if (likedSongDataBase == null)
            likedSongDataBase = Room.databaseBuilder(context, LikedSongDataBase.class, "LikedSongDB")
                    .allowMainThreadQueries()
                    .build();

        return likedSongDataBase;
    }
}
