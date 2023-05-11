package com.example.rhythm.viewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rhythm.data.model.artists.ArtistsResponse;
import com.example.rhythm.data.model.search.SearchResponseItem;
import com.example.rhythm.source.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel {

    private MutableLiveData<SearchResponseItem> _searchLiveData = new MutableLiveData<>();

    public LiveData<SearchResponseItem> searchLiveData = _searchLiveData;

    private MutableLiveData<String> _messageLiveData = new MutableLiveData<>();
    public LiveData<String> messageLiveData = _messageLiveData;

    public void getSearchItems(Context context, String searchText) {


        RetrofitClient.getWepService().search(searchText, "track", 15)
                .enqueue(new Callback<SearchResponseItem>() {
                    @Override
                    public void onResponse(Call<SearchResponseItem> call, Response<SearchResponseItem> response) {

                        if (response.body() != null) {
                            Log.d("sssssssssssss", "onResponse: search  " + response.body());
                           // Log.d("sssssssssssss", "onResponse: search  " + response.body().getTracks().getItems().get(0).getName());

                            _searchLiveData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponseItem> call, Throwable t) {
                        Log.d("sssssssssssss", "onFailure: search  " + t.getLocalizedMessage());
                        _messageLiveData.setValue(t.getLocalizedMessage());

                    }
                });


    }


}
