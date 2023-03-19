package com.example.rhythm.source.remote;

import com.example.rhythm.data.model.artists.ArtistsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    @GET("artists")
    Call<ArtistsResponse> getArtists(@Query("ids") String artistsID );

}
