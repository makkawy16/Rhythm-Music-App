package com.example.rhythm.ui.homePage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
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
import com.example.rhythm.ui.adapter.SearchAdapter;

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


    public SearchFragment() {
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSearchBinding.bind(view);

        binding.searchtxt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchItems.clear();
                getSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchItems.clear();
                getSearch(newText);

                return true;
            }
        });

    }


    private void getSearch(String searchText) {

        RetrofitClient.getWepService().search(searchText, "track", 15)
                .enqueue(new Callback<SearchResponseItem>() {
                    @Override
                    public void onResponse(Call<SearchResponseItem> call, Response<SearchResponseItem> response) {

                        if (response.body() != null) {
                            Log.d("sssssssssssss", "onResponse: search  " + response.body());
                            Log.d("sssssssssssss", "onResponse: search  " + response.body().getTracks().getItems().get(0).getName());


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


    private void initRecycler() {
        searchAdapter = new SearchAdapter(searchItems, getContext());
        binding.recyclerSearchResult.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerSearchResult.setAdapter(searchAdapter);

    }


}