package com.example.rhythm.data.model.artists;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ArtistsItem{

	@SerializedName("images")
	private List<ImagesItem> images;

	@SerializedName("followers")
	private Followers followers;

	@SerializedName("genres")
	private List<String> genres;

	@SerializedName("popularity")
	private int popularity;

	@SerializedName("name")
	private String name;

	@SerializedName("href")
	private String href;

	@SerializedName("id")
	private String id;

	@SerializedName("type")
	private String type;

	@SerializedName("external_urls")
	private ExternalUrls externalUrls;

	@SerializedName("uri")
	private String uri;

	public List<ImagesItem> getImages(){
		return images;
	}

	public Followers getFollowers(){
		return followers;
	}

	public List<String> getGenres(){
		return genres;
	}

	public int getPopularity(){
		return popularity;
	}

	public String getName(){
		return name;
	}

	public String getHref(){
		return href;
	}

	public String getId(){
		return id;
	}

	public String getType(){
		return type;
	}

	public ExternalUrls getExternalUrls(){
		return externalUrls;
	}

	public String getUri(){
		return uri;
	}
}