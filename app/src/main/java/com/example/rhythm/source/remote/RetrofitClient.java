package com.example.rhythm.source.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitClient {

    private final static String BASE_URL = "https://api.spotify.com/v1/";
    private static Retrofit retrofit;

    public static WebService getWepService() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return  retrofit.create(WebService.class);
    }
}
