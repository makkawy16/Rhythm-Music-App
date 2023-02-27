package com.example.rhythm.source.remote;

import com.example.rhythm.data.model.ArtistsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

    @GET("artists/{ids}")
    Call<ArtistsResponse> getArtists(@Path("ids") String artistsID);
}
