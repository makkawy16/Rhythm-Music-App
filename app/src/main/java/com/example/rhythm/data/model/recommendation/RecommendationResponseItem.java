package com.example.rhythm.data.model.recommendation;

import com.google.gson.annotations.SerializedName;

public class RecommendationResponseItem{

	@SerializedName("artist")
	private String artist;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("album")
	private String album;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("preview_url")
	private String previewUrl;

	@SerializedName("track_id")
	private String trackId;

	@SerializedName("popularity")
	private int popularity;

	@SerializedName("name")
	private String name;

	public String getArtist(){
		return artist;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String getAlbum(){
		return album;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public String getPreviewUrl(){
		return previewUrl;
	}

	public String getTrackId(){
		return trackId;
	}

	public int getPopularity(){
		return popularity;
	}

	public String getName(){
		return name;
	}
}