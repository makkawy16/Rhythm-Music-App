package com.example.rhythm.source.local.roomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LikedSongsDAO {

    @Query("select * from LikedSong")
    List<LikedSong> getAllLikedSongs();

    @Insert
    long AddSong(LikedSong likedSong);

    @Delete
    void deleteLikedSong(LikedSong likedSong);

}
