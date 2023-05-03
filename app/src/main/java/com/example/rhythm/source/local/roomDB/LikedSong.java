package com.example.rhythm.source.local.roomDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LikedSong {

    @PrimaryKey
    @NonNull
    String SongId;
    @ColumnInfo(index = true)  // for seraching by name make it faster
    String songName;
    String previewURL;
    String artistName;
    String imageUrl;
    boolean Liked ;

    public LikedSong() {
    }

    public LikedSong(String songId, String songName, String previewURL, String artistName, String imageUrl, boolean liked) {
        SongId = songId;
        this.songName = songName;
        this.previewURL = previewURL;
        this.artistName = artistName;
        this.imageUrl = imageUrl;
        Liked = liked;
    }

    public String getSongId() {
        return SongId;
    }

    public void setSongId(String songId) {
        SongId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isLiked() {
        return Liked;
    }

    public void setLiked(boolean liked) {
        Liked = liked;
    }
}
