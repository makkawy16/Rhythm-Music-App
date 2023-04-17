package com.example.rhythm.data.model.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalUrls implements Serializable {

	@SerializedName("spotify")
	private String spotify;

	public String getSpotify(){
		return spotify;
	}
}