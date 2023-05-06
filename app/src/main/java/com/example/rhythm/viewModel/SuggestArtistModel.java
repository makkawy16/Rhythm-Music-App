package com.example.rhythm.viewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rhythm.data.model.artists.ArtistsResponse;
import com.example.rhythm.source.remote.RetrofitClient;
import com.example.rhythm.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestArtistModel {

    Utils utils;

    public SuggestArtistModel() {
        utils = new Utils();
    }

    String artistsIDs = "2GVksDv9UpY60i4CvytrZK,5abSRg0xN1NV3gLbuvX24M,4U7K3Xm1CXe5FpBGYUcHUZ,0qZ24TkLCHoE3ajCzGItJ1,6bb9VI1PpPTEmdgcgjTppX,7yBuSjd5Z3w7acodk51evR,56chSp36PsMhpQvUn1kdR3,4o6vIkdmHiEXZOesrJj3KO,5rNrRYsRVaRJDQhA1PEC6t,5jii08sWD8V92EdOofQo52,6IW026WCYU8L1WF79dfwss,2H6XYL9D5Z3ErkxCD0gmD6,4dpARuHxo51G3z768sgnrY,5D2ui1KD49TfyCDb35zf5V,41g2nSmocqVLuYnmndxefu,5rCq30EbJ3DfZPKybGZj8F,4l7F7EEXy93Jr0uIITY7bN,4cGfgRmpFc9zgZMfuSXhqy,6qqNVTkY8uBg9cP3Jd7DAH";


    private MutableLiveData<ArtistsResponse> _artistLiveData = new MutableLiveData<>();

    public LiveData<ArtistsResponse> artistLiveData = _artistLiveData;

    private MutableLiveData<String> _messageLiveData = new MutableLiveData<>();
    public LiveData<String> messageLiveData = _messageLiveData;

    public void fetchArtists(Context context) {

        utils.waitnig("Wait","Loding..." , context);

        RetrofitClient.getWepService().getArtists(artistsIDs)
                .enqueue(new Callback<ArtistsResponse>() {
                    @Override
                    public void onResponse(Call<ArtistsResponse> call, Response<ArtistsResponse> response) {
                        Log.d("sssssssssssssssssss", "onResponse: srtist view model   " + response.body());
                        if (response.isSuccessful())
                            _artistLiveData.setValue(response.body());
                       utils.mloadingBar.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ArtistsResponse> call, Throwable t) {
                        Log.d("ssssssssssssss", "onFailure: " + t.getLocalizedMessage());
                        _messageLiveData.setValue(t.getLocalizedMessage());
                       utils.mloadingBar.dismiss();
                    }
                });

    }

}
