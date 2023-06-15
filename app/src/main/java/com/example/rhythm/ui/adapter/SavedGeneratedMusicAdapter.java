package com.example.rhythm.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.data.model.generation.GeneratedMusicModel;
import com.example.rhythm.databinding.ItemLikedSongLayoutBinding;

import java.util.List;

public class SavedGeneratedMusicAdapter extends RecyclerView.Adapter<SavedGeneratedMusicAdapter.viewHolder> {

    List<GeneratedMusicModel> generatedMusicModelList;

    public void addGeneratedMusic(List<GeneratedMusicModel> generatedMusicModelList) {
        this.generatedMusicModelList = generatedMusicModelList;
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
        GeneratedMusicModel generatedMusicModel = generatedMusicModelList.get(position);

        holder.binding.songNameOrArtist.setText(generatedMusicModel.getMusicName());


    }

    @Override
    public int getItemCount() {
        return generatedMusicModelList == null ? 0 : generatedMusicModelList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ItemLikedSongLayoutBinding binding;

        public viewHolder(@NonNull ItemLikedSongLayoutBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
