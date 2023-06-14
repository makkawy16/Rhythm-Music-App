package com.example.rhythm.source.remote;

import com.example.rhythm.data.model.artists.ArtistsResponse;
import com.example.rhythm.data.model.newRelease.ItemsItemNewRelease;
import com.example.rhythm.data.model.newRelease.NewReleaseResponse;
import com.example.rhythm.data.model.recommendation.RecommendationResponseItem;
import com.example.rhythm.data.model.search.SearchResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

    @GET("artists")
    Call<ArtistsResponse> getArtists(@Query("ids") String artistsID );

    @GET("search")
    Call<SearchResponseItem> search(@Query("q") String name , @Query("type")  String type , @Query("limit") int limit);

    @GET("recommendations")
    Call<List<RecommendationResponseItem>> firstRecommend(@Query("artist") String artistNames);

    @GET("new-releases")
    Call<NewReleaseResponse> getNewRelease();


}
