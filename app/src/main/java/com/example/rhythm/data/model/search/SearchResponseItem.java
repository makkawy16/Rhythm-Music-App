package com.example.rhythm.data.model.search;

import com.google.gson.annotations.SerializedName;

public class SearchResponseItem{

	@SerializedName("tracks")
	private Tracks tracks;

	public Tracks getTracks(){
		return tracks;
	}
}