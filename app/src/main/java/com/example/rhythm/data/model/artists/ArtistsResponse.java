package com.example.rhythm.data.model.artists;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ArtistsResponse{

	@SerializedName("artists")
	private List<ArtistsItem> artists;

	public List<ArtistsItem> getArtists(){
		return artists;
	}
}