package com.example.rhythm.ui.homePage.library;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.data.model.search.ItemsItem;
import com.example.rhythm.databinding.FragmentSongPlayerBinding;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class SongPlayerFragment extends Fragment  {

    FragmentSongPlayerBinding binding;
    String songName;
    String imageUrl;
    String artistName;
    MediaPlayer mediaPlayer = new MediaPlayer();
    ItemsItem itemsItem;

   // private SpotifyPlayer mPlayer;

    public SongPlayerFragment(String songName, String imageUrl, String artistName, ItemsItem itemsItem) {
        this.songName = songName;
        this.imageUrl = imageUrl;
        this.artistName = artistName;
        this.itemsItem = itemsItem;
    }

    public SongPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentSongPlayerBinding
                .bind(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            binding.backgroundImage.setRenderEffect(RenderEffect.createBlurEffect(80, 80, Shader.TileMode.MIRROR));
        }

        binding.songName.setText(songName);
        Picasso.get().load(imageUrl).into(binding.backgroundImage);
        Picasso.get().load(imageUrl).into(binding.songimage);
        binding.artistName.setText(artistName);


        /*mediaPlayer = MediaPlayer.create(getContext(), Uri.parse("spotify:track:1IhGkxXcW4vFBR9dHP5To9"));
        mediaPlayer.stop();
        mediaPlayer.release();// mediaPlayer.prepare();
        mediaPlayer.start();*/


        try {
         //   mediaPlayer.setDataSource(getContext(), Uri.parse("spotify:track:1IhGkxXcW4vFBR9dHP5To9"));
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
           // mediaPlayer.setDataSource("https://p.scdn.co/mp3-preview/f25788dadeab8a25fc970ccca716c1f9ebc2dd72?cid=f0ab2ded1b8a482188532d1f3441d07c");
          //  mediaPlayer.setDataSource("https://open.spotify.com/track/1IhGkxXcW4vFBR9dHP5To9?autoplay=true");
           // mediaPlayer.setDataSource(getContext() , Uri.parse("spotify://track/1IhGkxXcW4vFBR9dHP5To9"));

            mediaPlayer.setDataSource(itemsItem.getPreviewUrl());
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ssssssssssss", "onViewCreated:  media player  " + e.getLocalizedMessage());
        }


    }
}