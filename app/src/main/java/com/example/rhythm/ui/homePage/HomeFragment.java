package com.example.rhythm.ui.homePage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.example.rhythm.viewModel.HomeRecommendationViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    String artistsSelected;
    HomeSongAdapter homeSongAdapterRecomm;
    HomeSongAdapter homeSongAdapterNew;
    List<RecommendationResponseItem> recommendationResponseItemList = new ArrayList<>();
    String selectedFromShared;
    HomeRecommendationViewModel recommendationViewModel ;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences shared = getActivity().
                getSharedPreferences("myShared",
                        Context.MODE_PRIVATE);
        selectedFromShared = shared.getString("names", "no value here");
        recommendationViewModel = new HomeRecommendationViewModel();
        recommendationViewModel.getRecommendedSongs(getContext(),selectedFromShared);

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

        Log.d("sssssssssssssssssssss", "onViewCreated:  artists names   shared  " + selectedFromShared);


        observe();

        /*RetrofitClient.getRecommendationWebService()
                .firstRecommend(selectedFromShared)
                .enqueue(new Callback<List<RecommendationResponseItem>>() {
                    @Override
                    public void onResponse(Call<List<RecommendationResponseItem>> call, Response<List<RecommendationResponseItem>> response) {
                       Log.d("sssssssssssssss", "onResponse: recommend " + response.body());
                        Log.d("sssssssssssssss", "onResponse: recommend " + response.body().get(1).getName());

                        recommendationResponseItemList.addAll(response.body());

                        homeSongAdapterRecomm.addRecommendedSongs(recommendationResponseItemList);
                    }

                    @Override
                    public void onFailure(Call<List<RecommendationResponseItem>> call, Throwable t) {
                        Log.d("sssssssss", "onFailure: recommend " + t.getLocalizedMessage());

                    }


                });*/


    }

    private void observe() {
        recommendationViewModel.RecommendationLiveData
                .observe(getViewLifecycleOwner(), new Observer<List<RecommendationResponseItem>>() {
                    @Override
                    public void onChanged(List<RecommendationResponseItem> recommendationResponseItems) {
                        homeSongAdapterRecomm.addRecommendedSongs(recommendationResponseItems);
                    }
                });
    }

    private void songRecycler() {
        homeSongAdapterRecomm = new HomeSongAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.forYouSongRecycler.setLayoutManager(linearLayoutManager);
        binding.forYouSongRecycler.setAdapter(homeSongAdapterRecomm);
    }

}