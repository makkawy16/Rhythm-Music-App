package com.example.rhythm.ui.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.data.model.artists.ArtistsItem;
import com.example.rhythm.data.model.artists.ArtistsResponse;
import com.example.rhythm.databinding.FragmentSuggestionBinding;
import com.example.rhythm.source.remote.RetrofitClient;
import com.example.rhythm.ui.adapter.SingerSuggestAdapter;
import com.example.rhythm.ui.homePage.HomePageActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class suggestionFragment extends Fragment {


    FragmentSuggestionBinding binding;
    SingerSuggestAdapter singerSuggestAdapter;
    List<ArtistsItem> artistList = new ArrayList<>();

    String artistsIDs = "2GVksDv9UpY60i4CvytrZK,5abSRg0xN1NV3gLbuvX24M";


    public suggestionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggestion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSuggestionBinding.bind(view);


        RetrofitClient.getWepService()
                .getArtists("2GVksDv9UpY60i4CvytrZK,5abSRg0xN1NV3gLbuvX24M,4U7K3Xm1CXe5FpBGYUcHUZ,4l7F7EEXy93Jr0uIITY7bN")
                .enqueue(new Callback<ArtistsResponse>() {
                    @Override
                    public void onResponse(Call<ArtistsResponse> call, Response<ArtistsResponse> response) {
                        Log.d("ssssssssssss", "onResponse: " +response.body());
                        Log.d("ssssssssssss", "onResponse: " +response.body().getArtists().get(0).getName());
                       // singerSuggestAdapter.addArtist(response.body().getArtists());
                        artistList.addAll(response.body().getArtists());
                        initialSuggestRecycler();
                    }

                    @Override
                    public void onFailure(Call<ArtistsResponse> call, Throwable t) {
                        Log.d("sssssssssss", "onFailure: " + t.getLocalizedMessage());
                    }
                });


        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomePageActivity.class));
                getActivity().finish();
            }
        });
    }

    private void initialSuggestRecycler() {

        singerSuggestAdapter = new SingerSuggestAdapter(artistList,getContext());
        binding.singerRecylcer.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.singerRecylcer.setAdapter(singerSuggestAdapter);


    }
}