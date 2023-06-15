package com.example.rhythm.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rhythm.data.model.newRelease.ItemsItemNewRelease;
import com.example.rhythm.data.model.newRelease.NewReleaseResponse;
import com.example.rhythm.data.model.recommendation.RecommendationResponseItem;
import com.example.rhythm.source.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewReleaseViewModel {

    private MutableLiveData<List<ItemsItemNewRelease>> _newReleaseLiveData = new MutableLiveData<>();

    public LiveData<List<ItemsItemNewRelease>> newReleaseLiveData = _newReleaseLiveData;

    private MutableLiveData<String> _messageLiveData = new MutableLiveData<>();
    public LiveData<String> messageLiveData = _messageLiveData;

    public void showNewRelease() {

        RetrofitClient.getNewReleasesWebService().getNewRelease()
                .enqueue(new Callback<NewReleaseResponse>() {
                    @Override
                    public void onResponse(Call<NewReleaseResponse> call, Response<NewReleaseResponse> response) {
                        Log.d("ssssssssssssss", "onResponse: new release " + response.body());

                        if (response.body() != null)
                          /*  itemsItemNewReleaseList.addAll(response.body().getAlbums().getItems());
                        newReleaseAdapter.addNewReleaseToList(itemsItemNewReleaseList);*/
                        _newReleaseLiveData.setValue(response.body().getAlbums().getItems());
                    }

                    @Override
                    public void onFailure(Call<NewReleaseResponse> call, Throwable t) {
                        Log.d("ssssssssssssssss", "onFailure:  new release" + t.getLocalizedMessage());
                    }
                });
    }

}
