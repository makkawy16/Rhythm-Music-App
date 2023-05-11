package com.example.rhythm.ui.authentication;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.rhythm.R;
import com.example.rhythm.data.model.artists.ArtistsItem;
import com.example.rhythm.data.model.artists.ArtistsResponse;
import com.example.rhythm.databinding.FragmentSuggestionBinding;
import com.example.rhythm.source.remote.RetrofitClient;
import com.example.rhythm.ui.adapter.OnSingerItemClicked;
import com.example.rhythm.ui.adapter.SingerSuggestAdapter;
import com.example.rhythm.ui.homePage.HomePageActivity;
import com.example.rhythm.utils.Utils;
import com.example.rhythm.viewModel.SuggestArtistModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class suggestionFragment extends Fragment {


    FragmentSuggestionBinding binding;
    SingerSuggestAdapter singerSuggestAdapter;
    List<ArtistsItem> artistList = new ArrayList<>();
    OnSingerItemClicked singerItemClicked;
    List<String> singerNames = new ArrayList<>();
    Utils utils = new Utils();
    String selectedNames = "";

    SuggestArtistModel suggestArtistModel ;

    String artistsIDs = "2GVksDv9UpY60i4CvytrZK,5abSRg0xN1NV3gLbuvX24M,4U7K3Xm1CXe5FpBGYUcHUZ,0qZ24TkLCHoE3ajCzGItJ1,6bb9VI1PpPTEmdgcgjTppX,7yBuSjd5Z3w7acodk51evR,56chSp36PsMhpQvUn1kdR3,4o6vIkdmHiEXZOesrJj3KO,5rNrRYsRVaRJDQhA1PEC6t,5jii08sWD8V92EdOofQo52,6IW026WCYU8L1WF79dfwss,2H6XYL9D5Z3ErkxCD0gmD6,4dpARuHxo51G3z768sgnrY,5D2ui1KD49TfyCDb35zf5V,41g2nSmocqVLuYnmndxefu,5rCq30EbJ3DfZPKybGZj8F,4l7F7EEXy93Jr0uIITY7bN,4cGfgRmpFc9zgZMfuSXhqy,6qqNVTkY8uBg9cP3Jd7DAH";


    public suggestionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        suggestArtistModel = new SuggestArtistModel();
        suggestArtistModel.fetchArtists(getContext());


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


        singerItemClicked = new OnSingerItemClicked() {
            @Override
            public void onSingerClicked(String artistName) {

                if (!singerNames.contains(artistName))
                    singerNames.add(artistName);
                selectedNames =  artistName+ ","+selectedNames;
                SharedPreferences shared = requireActivity().
                        getSharedPreferences( "myShared" , Context.MODE_PRIVATE);
                SharedPreferences.Editor editor =
                        shared. edit();
                editor. putString(  "names" ,  selectedNames);
                editor.apply(); // or
            }
        };
       // showArtists();
        observe();
        initialSuggestRecycler();





        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (singerNames.size() < 3)
                    utils.alertDialog("Something Missed", "You Should Select at least Three Artists ", getContext());
                else {
                    Intent intent = new Intent(getActivity(), HomePageActivity.class);
                    intent.putExtra("artistsNames" , selectedNames);
                    startActivity(intent);
                    getActivity().finish();

                }
            }
        });
    }

    private void observe() {

        suggestArtistModel.artistLiveData
                .observe(getViewLifecycleOwner(), new Observer<ArtistsResponse>() {
                    @Override
                    public void onChanged(ArtistsResponse artistsResponse) {
                        if(singerSuggestAdapter!=null)
                        singerSuggestAdapter.addArtist(artistsResponse.getArtists());
                    }
                });

    }

    private void initialSuggestRecycler() {

        singerSuggestAdapter = new SingerSuggestAdapter(artistList, getContext(), singerItemClicked);
        binding.singerRecylcer.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.singerRecylcer.setAdapter(singerSuggestAdapter);


    }

/*
    private void showArtists() {
        RetrofitClient.getWepService()
                .getArtists(artistsIDs)
                .enqueue(new Callback<ArtistsResponse>() {
                    @Override
                    public void onResponse(Call<ArtistsResponse> call, Response<ArtistsResponse> response) {
                        Log.d("ssssssssssss", "onResponse:  singer " + response.body());
                        if (response.isSuccessful())
                        {
                            if(response.body() != null)
                                artistList.addAll(response.body().getArtists());
                            initialSuggestRecycler();

                        }
                    }

                    @Override
                    public void onFailure(Call<ArtistsResponse> call, Throwable t) {
                        Log.d("sssssssssss", "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }
*/
}