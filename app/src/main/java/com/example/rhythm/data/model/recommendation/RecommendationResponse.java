package com.example.rhythm.data.model.recommendation;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RecommendationResponse{

	@SerializedName("RecommendationResponse")
	private List<RecommendationResponseItem> recommendationResponse;

	public List<RecommendationResponseItem> getRecommendationResponse(){
		return recommendationResponse;
	}
}