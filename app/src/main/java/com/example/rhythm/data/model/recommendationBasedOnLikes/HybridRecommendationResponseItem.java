package com.example.rhythm.data.model.recommendationBasedOnLikes;

import com.google.gson.annotations.SerializedName;

public class HybridRecommendationResponseItem {

	@SerializedName("artist")
	private String artist;

	@SerializedName("preview_url")
	private String previewUrl;

	@SerializedName("name")
	private String name;

	public String getArtist(){
		return artist;
	}

	public String getPreviewUrl(){
		return previewUrl;
	}

	public String getName(){
		return name;
	}
}