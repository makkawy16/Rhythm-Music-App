package com.example.rhythm.data.model.recommendationBasedOnLikes;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HybridRecommendationResponse {

	@SerializedName("RecommendationLikesResponse")
	private List<HybridRecommendationResponseItem> recommendationLikesResponse;

	public List<HybridRecommendationResponseItem> getRecommendationLikesResponse(){
		return recommendationLikesResponse;
	}
}