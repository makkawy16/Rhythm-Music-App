package com.example.rhythm.ui.homePage.library;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.databinding.FragmentSongPlayerBinding;
import com.squareup.picasso.Picasso;

public class SongPlayerFragment extends Fragment {

    FragmentSongPlayerBinding binding;
    String songName;
    String imageUrl;
    String artistName;

    public SongPlayerFragment(String songName, String imageUrl, String artistName) {
        this.songName = songName;
        this.imageUrl = imageUrl;
        this.artistName = artistName;
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
            binding.backgroundImage.setRenderEffect(RenderEffect.createBlurEffect(80,80, Shader.TileMode.MIRROR));
        }

        binding.songName.setText(songName);
        Picasso.get().load(imageUrl).into(binding.backgroundImage);
        Picasso.get().load(imageUrl).into(binding.songimage);
        binding.artistName.setText(artistName);

    }
}