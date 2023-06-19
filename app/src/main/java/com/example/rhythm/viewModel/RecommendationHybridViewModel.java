package com.example.rhythm.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rhythm.data.model.recommendation.RecommendationResponseItem;
import com.example.rhythm.data.model.recommendationBasedOnLikes.HybridRecommendationResponse;
import com.example.rhythm.data.model.recommendationBasedOnLikes.HybridRecommendationResponseItem;
import com.example.rhythm.source.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendationHybridViewModel {

    private MutableLiveData<List<HybridRecommendationResponseItem>> _hybridRecommendationLiveData = new MutableLiveData<>();

    public LiveData<List<HybridRecommendationResponseItem>> hybridRecommendationLiveData = _hybridRecommendationLiveData;


    private MutableLiveData<String> _messageLiveData = new MutableLiveData<>();
    public LiveData<String> messageLiveData = _messageLiveData;


    public void getHybridRecommendation(String songName) {
        RetrofitClient.getHybridRecommendationWebService()
                .getHybridRecommendation(songName)
                .enqueue(new Callback<List<HybridRecommendationResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<HybridRecommendationResponseItem>> call, Response<List<HybridRecommendationResponseItem>> response) {
                        Log.d("ssssssssssssssss", "onResponse: hybrid view model" + response.body().get(2).getName());

                    }

                    @Override
                    public void onFailure(Call<List<HybridRecommendationResponseItem>> call, Throwable t) {
                        Log.d("sssssssssssssss", "onFailure: hybrid " + t.getLocalizedMessage());

                    }

                });
    }


}
