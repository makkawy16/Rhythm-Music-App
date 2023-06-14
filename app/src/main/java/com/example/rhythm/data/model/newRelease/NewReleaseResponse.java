package com.example.rhythm.data.model.newRelease;

import com.google.gson.annotations.SerializedName;

public class NewReleaseResponse{

	@SerializedName("albums")
	private Albums albums;

	public Albums getAlbums(){
		return albums;
	}
}