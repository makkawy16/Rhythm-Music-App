package com.example.rhythm.source.remote;

import com.example.rhythm.ui.authentication.AuthenticationActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitClient {

    private final static String BASE_URL = "https://api.spotify.com/v1/";
    private final static String BASE_URL3 = "https://api.spotify.com/v1/browse/";
    private final static String BASE_URL2 = "https://web-production-d8ff.up.railway.app/";
    private static Retrofit retrofit;
    private static Retrofit retrofit2;
    private static Retrofit retrofit3;

    public static WebService getWepService() {
        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Add authorization header with access token
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + AuthenticationActivity.accessToken);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return retrofit.create(WebService.class);
    }

    public static WebService getRecommendationWebService() {
        if (retrofit2 == null) {

            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit2.create(WebService.class);
    }



    public static WebService getNewReleasesWebService() {
        if (retrofit3 == null) {
            OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Add authorization header with access token
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + AuthenticationActivity.accessToken);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            }).build();

            retrofit3 = new Retrofit.Builder()
                    .baseUrl(BASE_URL3)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return retrofit3.create(WebService.class);
    }

}
