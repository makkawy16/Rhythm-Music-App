package com.example.rhythm.data.model.search;

import com.google.gson.annotations.SerializedName;

public class ExternalUrls{

	@SerializedName("spotify")
	private String spotify;

	public String getSpotify(){
		return spotify;
	}
}