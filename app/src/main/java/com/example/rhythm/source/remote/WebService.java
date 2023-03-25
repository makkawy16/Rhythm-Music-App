package com.example.rhythm.source.remote;

import com.example.rhythm.data.model.artists.ArtistsResponse;
import com.example.rhythm.data.model.search.SearchResponseItem;
import com.example.rhythm.data.model.search.Tracks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    @GET("artists")
    Call<ArtistsResponse> getArtists(@Query("ids") String artistsID );

    @GET("search")
    Call<SearchResponseItem> search(@Query("q") String name , @Query("type")  String type , @Query("limit") int limit);

}
