package com.example.rhythm.data.model.search;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalIds implements Serializable {

	@SerializedName("isrc")
	private String isrc;

	public String getIsrc(){
		return isrc;
	}
}