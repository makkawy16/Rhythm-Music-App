package com.example.rhythm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rhythm.databinding.FragmentSettingsBinding;
import com.example.rhythm.databinding.ItemSettingsLayoutBinding;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder> {


    List<String> settingItemName;
    Context context;


    public SettingsAdapter(List<String> settingItemName , Context context) {
        this.settingItemName = settingItemName;
        this.context = context;
    }

    public void addItem(List<String> settingItemName) {
        this.settingItemName = settingItemName;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSettingsLayoutBinding binding =
                ItemSettingsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SettingsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        String itemName = settingItemName.get(position);
        holder.binding.itemSettingName.setText(itemName);

    }

    @Override
    public int getItemCount() {
        return settingItemName != null ? settingItemName.size() : 0;
    }

    static class SettingsViewHolder extends RecyclerView.ViewHolder {

        ItemSettingsLayoutBinding binding;

        public SettingsViewHolder(@NonNull ItemSettingsLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
