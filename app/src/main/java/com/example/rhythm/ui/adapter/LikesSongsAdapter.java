package com.example.rhythm.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.databinding.ItemLikedSongLayoutBinding;
import com.example.rhythm.source.local.roomDB.LikedSong;
import com.example.rhythm.ui.songPlayer.SongPlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class LikesSongsAdapter extends RecyclerView.Adapter<LikesSongsAdapter.viewHolder> {


    private List<LikedSong> likedSongsList;
    Context context;

    public LikesSongsAdapter(Context context) {
        this.context = context;
    }

    public void addSongToLikes(List<LikedSong> likedSongs) {
        this.likedSongsList = likedSongs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLikedSongLayoutBinding binding =
                ItemLikedSongLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        LikedSong likedSong = likedSongsList.get(position);

        holder.binding.songNameOrArtist.setText(likedSong.getSongName());
        Picasso.get().load(likedSong.getImageUrl()).into(holder.binding.songImage);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , SongPlayerActivity.class);
                intent.putExtra("songName" , likedSong.getSongName());
                intent.putExtra("imageUrl" , likedSong.getImageUrl());
                intent.putExtra("artistName" , likedSong.getArtistName());
                intent.putExtra("songurl" , likedSong.getPreviewURL());
                intent.putExtra("songid" , likedSong.getSongId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return likedSongsList == null ? 0 : likedSongsList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ItemLikedSongLayoutBinding binding;

        public viewHolder(@NonNull ItemLikedSongLayoutBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
