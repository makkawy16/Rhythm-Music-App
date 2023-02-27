package com.example.rhythm.ui.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.rhythm.R;
import com.example.rhythm.databinding.ActivityAuthenticationBinding;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;


public class AuthenticationActivity extends AppCompatActivity {

    ActivityAuthenticationBinding binding;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // getSupportActionBar().hide();

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                       // Navigation.findNavController(new SignUpFragment().getView()).navigate(R.id.action_signUpFragment_to_suggestionFragment);
                        Log.d("ssssssssss", "onSuccess: Activity " + loginResult.toString());

                        Fragment fragment = new suggestionFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainerView , fragment).commit();

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.d("ssssssssssss", "onCancel: ");

                    }

                    @Override
                    public void onError(FacebookException e) {
                        Log.d("sssssssssssssssss", "onError 1: " + e.getLocalizedMessage());
                        Log.d("sssssssssssssssss", "onError 2: " + e.getMessage());
                        Log.d("sssssssssssssssss", "onError 3: " + e.getCause());
                        Log.d("sssssssssssssssss", "onError 4: " + e.fillInStackTrace().getLocalizedMessage());
                        Log.d("sssssssssssssssss", "onError 5: " + e.getStackTrace());
                        // App code
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}