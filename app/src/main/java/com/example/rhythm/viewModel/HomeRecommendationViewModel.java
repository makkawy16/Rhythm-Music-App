package com.example.rhythm.viewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rhythm.data.model.recommendation.RecommendationResponseItem;
import com.example.rhythm.source.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRecommendationViewModel {
    private MutableLiveData<List<RecommendationResponseItem>> _recommendationLiveData = new MutableLiveData<>();

    public LiveData<List<RecommendationResponseItem>> RecommendationLiveData = _recommendationLiveData;

    private MutableLiveData<String> _messageLiveData = new MutableLiveData<>();
    public LiveData<String> messageLiveData = _messageLiveData;


    public void getRecommendedSongs(Context context , String selectedFromShared){

        RetrofitClient.getRecommendationWebService()
                .firstRecommend(selectedFromShared)
                .enqueue(new Callback<List<RecommendationResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<RecommendationResponseItem>> call, Response<List<RecommendationResponseItem>> response) {
                        if (response.body() != null) {
                            Log.d("sssssssssssssss", "onResponse: recommend " + response.body().size());
                            //Log.d("sssssssssssssss", "onResponse: recommend " + response.body().get(1).getName());

                            _recommendationLiveData.setValue(response.body());

                            /// feh moshkla  ben al list w al response
                        }

                    }

                    @Override
                    public void onFailure(Call<List<RecommendationResponseItem>> call, Throwable t) {
                        Log.d("sssssssss", "onFailure: recommend " + t.getLocalizedMessage());

                    }


                });

    }



}
