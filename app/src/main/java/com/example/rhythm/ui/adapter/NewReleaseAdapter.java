package com.example.rhythm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.data.model.newRelease.ItemsItemNewRelease;
import com.example.rhythm.databinding.ItemHomeSongBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewReleaseAdapter extends RecyclerView.Adapter<NewReleaseAdapter.viewHolderNewRelease> {

    List<ItemsItemNewRelease> itemsItemNewReleaseList;
    Context context;

    public NewReleaseAdapter(Context context) {
        this.context = context;
    }

    public void addNewReleaseToList(List<ItemsItemNewRelease> itemsItemNewReleaseList) {
        this.itemsItemNewReleaseList = itemsItemNewReleaseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolderNewRelease onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemHomeSongBinding binding =
                ItemHomeSongBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolderNewRelease(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderNewRelease holder, int position) {
        ItemsItemNewRelease itemNewRelease = itemsItemNewReleaseList.get(position);

        holder.binding.songName.setText(itemNewRelease.getName());
        holder.binding.artistNameTxt.setText(itemNewRelease.getArtists().get(0).getName());
        Picasso.get().load(itemNewRelease.getImages().get(1).getUrl()).into(holder.binding.songImage);
    }

    @Override
    public int getItemCount() {
        return itemsItemNewReleaseList == null ? 0 : itemsItemNewReleaseList.size();
    }

    static class viewHolderNewRelease extends RecyclerView.ViewHolder {
        ItemHomeSongBinding binding;

        public viewHolderNewRelease(@NonNull ItemHomeSongBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
