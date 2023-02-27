package com.example.rhythm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.data.model.ArtistsResponse;
import com.example.rhythm.databinding.ItemSingerSuggestLayoutBinding;

import java.util.List;

public class SingerSuggestAdapter extends RecyclerView.Adapter<SingerSuggestAdapter.viewHolder> {

    List<ArtistsResponse> artistsList;
    Context context;

    public SingerSuggestAdapter(List<ArtistsResponse> artistsList, Context context) {
        this.artistsList = artistsList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemSingerSuggestLayoutBinding binding =
                ItemSingerSuggestLayoutBinding.inflate(LayoutInflater.from(parent.getContext()) , parent,false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ArtistsResponse artists = artistsList.get(position);
        holder.binding.artistname.setText(artists.getName());


    }

    @Override
    public int getItemCount() {
        return artistsList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ItemSingerSuggestLayoutBinding binding;

        public viewHolder(@NonNull ItemSingerSuggestLayoutBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
