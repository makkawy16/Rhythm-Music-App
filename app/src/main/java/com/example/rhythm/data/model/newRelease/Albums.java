package com.example.rhythm.data.model.newRelease;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Albums{

	@SerializedName("next")
	private String next;

	@SerializedName("total")
	private int total;

	@SerializedName("offset")
	private int offset;

	@SerializedName("previous")
	private Object previous;

	@SerializedName("limit")
	private int limit;

	@SerializedName("href")
	private String href;

	@SerializedName("items")
	private List<ItemsItemNewRelease> items;

	public String getNext(){
		return next;
	}

	public int getTotal(){
		return total;
	}

	public int getOffset(){
		return offset;
	}

	public Object getPrevious(){
		return previous;
	}

	public int getLimit(){
		return limit;
	}

	public String getHref(){
		return href;
	}

	public List<ItemsItemNewRelease> getItems(){
		return items;
	}
}