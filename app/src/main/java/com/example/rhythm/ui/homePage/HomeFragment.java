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
import com.example.rhythm.data.model.newRelease.ItemsItemNewRelease;
import com.example.rhythm.data.model.newRelease.NewReleaseResponse;
import com.example.rhythm.data.model.recommendation.RecommendationResponseItem;
import com.example.rhythm.databinding.FragmentHomeBinding;
import com.example.rhythm.source.remote.RetrofitClient;
import com.example.rhythm.ui.adapter.HomeSongAdapter;
import com.example.rhythm.ui.adapter.NewReleaseAdapter;
import com.example.rhythm.ui.authentication.AuthenticationActivity;
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
    HomeRecommendationViewModel recommendationViewModel;

    List<ItemsItemNewRelease> itemsItemNewReleaseList = new ArrayList<>();
    NewReleaseAdapter newReleaseAdapter;


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
        recommendationViewModel.getRecommendedSongs(getContext(), selectedFromShared);

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

        // if (!AuthenticationActivity.accessToken.)
        showNewRelease();

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

    public void showNewRelease() {

        RetrofitClient.getNewReleasesWebService().getNewRelease()
                .enqueue(new Callback<NewReleaseResponse>() {
                    @Override
                    public void onResponse(Call<NewReleaseResponse> call, Response<NewReleaseResponse> response) {
                        Log.d("ssssssssssssss", "onResponse: new release " + response.body());

                        if (response.body() != null)
                            itemsItemNewReleaseList.addAll(response.body().getAlbums().getItems());
                        newReleaseAdapter.addNewReleaseToList(itemsItemNewReleaseList);
                    }

                    @Override
                    public void onFailure(Call<NewReleaseResponse> call, Throwable t) {
                        Log.d("ssssssssssssssss", "onFailure:  new release" + t.getLocalizedMessage());
                    }
                });
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
        newReleaseAdapter = new NewReleaseAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        binding.forYouSongRecycler.setLayoutManager(linearLayoutManager);
        binding.forYouSongRecycler.setAdapter(homeSongAdapterRecomm);

        binding.newReleaseSongRecycler.setLayoutManager(linearLayoutManager2);
        binding.newReleaseSongRecycler.setAdapter(newReleaseAdapter);
    }


}