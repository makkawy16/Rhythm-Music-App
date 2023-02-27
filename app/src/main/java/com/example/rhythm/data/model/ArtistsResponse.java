package com.example.rhythm.data.model;

import java.util.List;

public class ArtistsResponse{
	private List<ImagesItem> images;
	private Followers followers;
	private List<String> genres;
	private int popularity;
	private String name;
	private String href;
	private String id;
	private String type;
	private ExternalUrls externalUrls;
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