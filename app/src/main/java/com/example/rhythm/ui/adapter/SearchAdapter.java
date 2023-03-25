package com.example.rhythm.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavAction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.R;
import com.example.rhythm.data.model.search.ItemsItem;
import com.example.rhythm.databinding.ItemLikedSongLayoutBinding;
import com.example.rhythm.ui.homePage.library.LikesFragment;
import com.example.rhythm.ui.homePage.library.SongPlayerFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.searchViewHolder> {

    List<ItemsItem> tracksItems;
    Context context;
    OnSearchItemCLick searchItemCLick;

    public SearchAdapter(List<ItemsItem> tracksItems, Context context, OnSearchItemCLick searchItemCLick) {
        this.tracksItems = tracksItems;
        this.context = context;
        this.searchItemCLick = searchItemCLick;
    }

    @NonNull
    @Override
    public searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemLikedSongLayoutBinding binding = ItemLikedSongLayoutBinding.inflate(LayoutInflater.from(
                parent.getContext()), parent, false);

        return new searchViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return tracksItems != null ? tracksItems.size() : 0;
    }

    @Override
    public void onBindViewHolder(@NonNull searchViewHolder holder, int position) {

        ItemsItem searchItem = tracksItems.get(position);

        holder.binding.songNameOrArtist.setText(searchItem.getName());

        Picasso.get().load(searchItem.getAlbum().getImages().get(2).getUrl()).into(holder.binding.songImage);
        /*holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + searchItem.getName(), Toast.LENGTH_SHORT).show();

            }
        });*/

        holder.binding.itemSong.setOnClickListener(v -> {
            searchItemCLick.onItemSearchedCLiked(searchItem.getName(),searchItem.getAlbum().getImages().get(0).getUrl() , searchItem.getArtists().get(0).getName());
        });

    }


    class searchViewHolder extends RecyclerView.ViewHolder {
        ItemLikedSongLayoutBinding binding;

        public searchViewHolder(ItemLikedSongLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}




