package com.example.rhythm.ui.homePage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.navigation.NavAction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.data.model.search.ItemsItem;
import com.example.rhythm.data.model.search.SearchResponseItem;
import com.example.rhythm.data.model.search.Tracks;
import com.example.rhythm.databinding.FragmentSearchBinding;
import com.example.rhythm.source.remote.RetrofitClient;
import com.example.rhythm.ui.adapter.OnSearchItemCLick;
import com.example.rhythm.ui.adapter.SearchAdapter;
import com.example.rhythm.ui.homePage.library.LikesFragment;
import com.example.rhythm.ui.homePage.library.SongPlayerFragment;
import com.example.rhythm.ui.songPlayer.SongPlayerActivity;
import com.example.rhythm.viewModel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;
    SearchAdapter searchAdapter;
    List<ItemsItem> searchItems = new ArrayList<>();
    private SearchViewModel searchViewModel;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchViewModel = new SearchViewModel();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSearchBinding.bind(view);

        initRecycler();
        observe();

        binding.searchtxt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchItems.clear();
                //  getSearch(query);
                searchViewModel.getSearchItems(getContext(),query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchItems.clear();
                //   getSearch(newText);

                searchViewModel.getSearchItems(getContext(),newText);

                return true;
            }
        });


    }


    OnSearchItemCLick onsearchCLick = new OnSearchItemCLick() {


        @Override
        public void onItemSearchedCLiked(String songName, String url, String artistName, ItemsItem itemsItem, String songId) {
            Intent intent = new Intent(getActivity(), SongPlayerActivity.class);
            Bundle bundle = new Bundle();
            //bundle.putSerializable("songItem" , itemsItem);
            intent.putExtra("songName", songName);
            intent.putExtra("imageUrl", url);
            intent.putExtra("artistName", artistName);
            intent.putExtra("songurl", itemsItem.getPreviewUrl());
            intent.putExtra("songid", songId);

            //intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    private void getSearch(String searchText) {

        RetrofitClient.getWepService().search(searchText, "track", 15)
                .enqueue(new Callback<SearchResponseItem>() {
                    @Override
                    public void onResponse(Call<SearchResponseItem> call, Response<SearchResponseItem> response) {

                        if (response.body() != null) {
                           /* Log.d("sssssssssssss", "onResponse: search  " + response.body());
                            Log.d("sssssssssssss", "onResponse: search  " + response.body().getTracks().getItems().get(0).getName());*/


                            searchItems.addAll(response.body().getTracks().getItems());
                            initRecycler();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponseItem> call, Throwable t) {
                        Log.d("sssssssssssss", "onFailure: search  " + t.getLocalizedMessage());
                    }
                });

    }

    private void observe(){
        searchViewModel.searchLiveData
                .observe(getViewLifecycleOwner(), new Observer<SearchResponseItem>() {
                    @Override
                    public void onChanged(SearchResponseItem searchResponseItem) {
                        if (searchAdapter!=null)
                            searchAdapter.addSearchItems(searchResponseItem.getTracks().getItems());
                    }
                });
    }


    private void initRecycler() {
        searchAdapter = new SearchAdapter(searchItems, getContext(), onsearchCLick);
        binding.recyclerSearchResult.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerSearchResult.setAdapter(searchAdapter);

    }


}