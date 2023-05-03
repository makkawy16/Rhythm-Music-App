package com.example.rhythm.ui.homePage.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.databinding.FragmentLikesBinding;
import com.example.rhythm.source.local.roomDB.LikedSong;
import com.example.rhythm.source.local.roomDB.LikedSongDataBase;
import com.example.rhythm.ui.adapter.LikesSongsAdapter;

import java.util.List;


public class LikesFragment extends Fragment {


    FragmentLikesBinding binding;
    LikesSongsAdapter likesSongsAdapter;


    public LikesFragment() {
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
        return inflater.inflate(R.layout.fragment_likes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentLikesBinding.bind(view);
        initRecycler();
        fetchSongs();
    }

    private void fetchSongs() {

        List<LikedSong> likedSongs =
                LikedSongDataBase.getLikedSongDataBase(getContext())
                        .getLikedSongDao()
                        .getAllLikedSongs();
        if (likedSongs!=null)
            likesSongsAdapter.addSongToLikes(likedSongs);
    }


    private void initRecycler() {

        likesSongsAdapter = new LikesSongsAdapter();
        binding.likedSongsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.likedSongsRecycler.setAdapter(likesSongsAdapter);

    }

}