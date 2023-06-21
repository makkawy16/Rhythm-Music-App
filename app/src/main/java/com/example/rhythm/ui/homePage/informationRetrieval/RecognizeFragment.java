package com.example.rhythm.ui.homePage.informationRetrieval;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.databinding.FragmentRecognizeBinding;

public class RecognizeFragment extends Fragment {

    FragmentRecognizeBinding binding;

    public RecognizeFragment() {
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
        return inflater.inflate(R.layout.fragment_recognize, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentRecognizeBinding.bind(view);

        AnimationDrawable animationDrawable = (AnimationDrawable) binding.animation.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        binding.progressLoading.setVisibility(View.VISIBLE);

        binding.cannotfound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.recognizedSongName.setText("Sorry... We Can not Found It In Our Data");
                binding.progressLoading.setVisibility(View.INVISIBLE);

            }
        });

        binding.roma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.recognizedSongName.setText("Cairokee - Roma كايروكي - روما");
                binding.progressLoading.setVisibility(View.INVISIBLE);

            }
        });

        binding.rolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.recognizedSongName.setText("Adele - Rolling in the Deep ");
                binding.progressLoading.setVisibility(View.INVISIBLE);

            }
        });

    }

    public void recognize(){


    }
}