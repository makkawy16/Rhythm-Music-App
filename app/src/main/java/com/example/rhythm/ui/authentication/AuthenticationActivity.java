package com.example.rhythm.ui.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rhythm.R;
import com.example.rhythm.databinding.ActivityAuthenticationBinding;


public class AuthenticationActivity extends AppCompatActivity {

    ActivityAuthenticationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

    }
}