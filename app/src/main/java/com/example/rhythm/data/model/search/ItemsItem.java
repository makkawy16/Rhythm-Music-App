package com.example.rhythm.data.model.search;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ItemsItem implements Serializable {

	@SerializedName("disc_number")
	private int discNumber;

	@SerializedName("album")
	private Album album;

	@SerializedName("available_markets")
	private List<String> availableMarkets;

	@SerializedName("type")
	private String type;

	@SerializedName("external_ids")
	private ExternalIds externalIds;

	@SerializedName("uri")
	private String uri;

	@SerializedName("duration_ms")
	private int durationMs;

	@SerializedName("explicit")
	private boolean explicit;

	@SerializedName("artists")
	private List<ArtistsItem> artists;

	@SerializedName("preview_url")
	private String previewUrl;

	@SerializedName("popularity")
	private int popularity;

	@SerializedName("name")
	private String name;

	@SerializedName("track_number")
	private int trackNumber;

	@SerializedName("href")
	private String href;

	@SerializedName("id")
	private String id;

	@SerializedName("is_local")
	private boolean isLocal;

	@SerializedName("external_urls")
	private ExternalUrls externalUrls;

	public int getDiscNumber(){
		return discNumber;
	}

	public Album getAlbum(){
		return album;
	}

	public List<String> getAvailableMarkets(){
		return availableMarkets;
	}

	public String getType(){
		return type;
	}

	public ExternalIds getExternalIds(){
		return externalIds;
	}

	public String getUri(){
		return uri;
	}

	public int getDurationMs(){
		return durationMs;
	}

	public boolean isExplicit(){
		return explicit;
	}

	public List<ArtistsItem> getArtists(){
		return artists;
	}

	public String getPreviewUrl(){
		return previewUrl;
	}

	public int getPopularity(){
		return popularity;
	}

	public String getName(){
		return name;
	}

	public int getTrackNumber(){
		return trackNumber;
	}

	public String getHref(){
		return href;
	}

	public String getId(){
		return id;
	}

	public boolean isIsLocal(){
		return isLocal;
	}

	public ExternalUrls getExternalUrls(){
		return externalUrls;
	}
}