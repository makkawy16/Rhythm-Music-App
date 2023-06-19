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

    @Query("select * from LikedSong where songName LIKE :songName ")
    List<LikedSong> getSearched(String songName);

    @Insert
    long AddSong(LikedSong likedSong);

    @Delete
    void deleteLikedSong(LikedSong likedSong);

}
