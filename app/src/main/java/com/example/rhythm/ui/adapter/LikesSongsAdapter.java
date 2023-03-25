package com.example.rhythm.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.databinding.ItemLikedSongLayoutBinding;


public class LikesSongsAdapter extends RecyclerView.Adapter<LikesSongsAdapter.viewHolder> {



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLikedSongLayoutBinding binding =
                ItemLikedSongLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ItemLikedSongLayoutBinding binding;

        public viewHolder(@NonNull ItemLikedSongLayoutBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
