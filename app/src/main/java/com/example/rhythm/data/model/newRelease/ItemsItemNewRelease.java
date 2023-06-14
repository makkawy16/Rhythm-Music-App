package com.example.rhythm.data.model.newRelease;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ItemsItemNewRelease {

	@SerializedName("images")
	private List<ImagesItem> images;

	@SerializedName("available_markets")
	private List<String> availableMarkets;

	@SerializedName("release_date_precision")
	private String releaseDatePrecision;

	@SerializedName("type")
	private String type;

	@SerializedName("uri")
	private String uri;

	@SerializedName("total_tracks")
	private int totalTracks;

	@SerializedName("artists")
	private List<ArtistsItem> artists;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("name")
	private String name;

	@SerializedName("album_type")
	private String albumType;

	@SerializedName("href")
	private String href;

	@SerializedName("id")
	private String id;

	@SerializedName("external_urls")
	private ExternalUrls externalUrls;

	public List<ImagesItem> getImages(){
		return images;
	}

	public List<String> getAvailableMarkets(){
		return availableMarkets;
	}

	public String getReleaseDatePrecision(){
		return releaseDatePrecision;
	}

	public String getType(){
		return type;
	}

	public String getUri(){
		return uri;
	}

	public int getTotalTracks(){
		return totalTracks;
	}

	public List<ArtistsItem> getArtists(){
		return artists;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String getName(){
		return name;
	}

	public String getAlbumType(){
		return albumType;
	}

	public String getHref(){
		return href;
	}

	public String getId(){
		return id;
	}

	public ExternalUrls getExternalUrls(){
		return externalUrls;
	}
}