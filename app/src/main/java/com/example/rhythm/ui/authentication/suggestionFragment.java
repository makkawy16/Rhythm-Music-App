package com.example.rhythm.ui.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.data.model.ArtistsResponse;
import com.example.rhythm.databinding.FragmentSuggestionBinding;
import com.example.rhythm.source.remote.RetrofitClient;
import com.example.rhythm.ui.homePage.HomePageActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class suggestionFragment extends Fragment {


    FragmentSuggestionBinding binding;



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
                        .getArtists("5abSRg0xN1NV3gLbuvX24M")
                                .enqueue(new Callback<ArtistsResponse>() {
                                    @Override
                                    public void onResponse(Call<ArtistsResponse> call, Response<ArtistsResponse> response) {
                                        Log.d("dddddddddd", "onResponse: " +response.body());
                                    }

                                    @Override
                                    public void onFailure(Call<ArtistsResponse> call, Throwable t) {
                                        Log.d("ddddddddd", "onFailure: " + t.getLocalizedMessage());
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
}