package com.example.rhythm.ui.adapter;

import com.example.rhythm.data.model.search.ItemsItem;

public interface OnSearchItemCLick {
    void onItemSearchedCLiked(String songName, String url , String artistName , ItemsItem itemsItem , String songId);

}
