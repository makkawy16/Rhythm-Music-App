package com.example.rhythm.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.databinding.ItemHomeSongBinding;

public class HomeSongAdapter extends RecyclerView.Adapter<HomeSongAdapter.viewHolder> {


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ItemHomeSongBinding binding;

        public viewHolder(@NonNull ItemHomeSongBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
