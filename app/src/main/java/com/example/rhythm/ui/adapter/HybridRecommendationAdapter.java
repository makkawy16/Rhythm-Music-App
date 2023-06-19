package com.example.rhythm.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.data.model.recommendationBasedOnLikes.HybridRecommendationResponseItem;
import com.example.rhythm.databinding.ItemHomeSongBinding;

import java.util.List;

public class HybridRecommendationAdapter extends RecyclerView.Adapter<HybridRecommendationAdapter.viewHolder> {


    List<HybridRecommendationResponseItem> hybridRecommendationResponseItemList;

    public void addHybridToList(List<HybridRecommendationResponseItem> hybridRecommendationResponseItemList) {
        this.hybridRecommendationResponseItemList = hybridRecommendationResponseItemList;
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

        HybridRecommendationResponseItem item = hybridRecommendationResponseItemList.get(position);

     holder.binding.artistNameTxt.setText(item.getArtist());
     holder.binding.songName.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return hybridRecommendationResponseItemList == null ? 0 : hybridRecommendationResponseItemList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {
        ItemHomeSongBinding binding;

        public viewHolder(@NonNull ItemHomeSongBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
