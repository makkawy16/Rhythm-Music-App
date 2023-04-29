package com.example.rhythm.ui.homePage;

import static androidx.recyclerview.widget.RecyclerView.*;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.data.model.recommendation.RecommendationResponseItem;
import com.example.rhythm.databinding.FragmentHomeBinding;
import com.example.rhythm.source.remote.RetrofitClient;
import com.example.rhythm.ui.adapter.HomeSongAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    String artistsSelected;
    HomeSongAdapter homeSongAdapter;


    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentHomeBinding.bind(view);
        songRecycler();

   artistsSelected = getActivity().getIntent().getStringExtra("artistsNames");

        Log.d("sssssssssssssssssssss", "onViewCreated:  artists names" +artistsSelected);



        RetrofitClient.getRecommendationWebService()
                .firstRecommend("amr diab,cairokee")
                .enqueue(new Callback<List<RecommendationResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<RecommendationResponseItem>> call, Response<List<RecommendationResponseItem>> response) {
                        Log.d("sssssssssssssss", "onResponse: recommend " + response.body());
                        Log.d("sssssssssssssss", "onResponse: recommend " + response.body().get(1).getName());
                        homeSongAdapter.addRecommendedSongs(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<RecommendationResponseItem>> call, Throwable t) {
                        Log.d("sssssssss", "onFailure: recommend " + t.getLocalizedMessage());

                    }


                });


    }

    private void songRecycler(){
        homeSongAdapter = new HomeSongAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.forYouSongRecycler.setLayoutManager(linearLayoutManager);
        binding.forYouSongRecycler.setAdapter(homeSongAdapter);
    }

}