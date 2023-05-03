package com.example.rhythm.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.data.model.recommendation.RecommendationResponseItem;
import com.example.rhythm.databinding.ItemHomeSongBinding;
import com.example.rhythm.ui.songPlayer.SongPlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeSongAdapter extends RecyclerView.Adapter<HomeSongAdapter.viewHolder> {

    private List<RecommendationResponseItem> recommendationResponseItemsList;
    Context context;

    public HomeSongAdapter(Context context) {
        this.context = context;
    }

    public void addRecommendedSongs(List<RecommendationResponseItem> recommendationResponseItemsList) {
        this.recommendationResponseItemsList = recommendationResponseItemsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeSongBinding binding =
                ItemHomeSongBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        RecommendationResponseItem responseItem = recommendationResponseItemsList.get(position);

        holder.binding.songName.setText(responseItem.getName());
        Picasso.get().load(responseItem.getImageUrl()).into(holder.binding.songImage);
        holder.binding.artistNameTxt.setText(responseItem.getArtist());

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , SongPlayerActivity.class);
                intent.putExtra("songName" , responseItem.getName());
                intent.putExtra("imageUrl" , responseItem.getImageUrl());
                intent.putExtra("artistName" , responseItem.getArtist());
                intent.putExtra("songurl" , responseItem.getPreviewUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendationResponseItemsList == null ? 0 : recommendationResponseItemsList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ItemHomeSongBinding binding;

        public viewHolder(@NonNull ItemHomeSongBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
