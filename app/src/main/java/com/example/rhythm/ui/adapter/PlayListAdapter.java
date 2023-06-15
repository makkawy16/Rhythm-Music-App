package com.example.rhythm.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.databinding.ItemLikedSongLayoutBinding;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.viewHolderPlayList> {


    //fadel al list w al model aly htb2a list type meno

    @NonNull
    @Override
    public viewHolderPlayList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemLikedSongLayoutBinding binding =
                ItemLikedSongLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new viewHolderPlayList(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderPlayList holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class viewHolderPlayList extends RecyclerView.ViewHolder {


        ItemLikedSongLayoutBinding binding;

        public viewHolderPlayList(@NonNull ItemLikedSongLayoutBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
