package com.example.rhythm.ui.homePage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.rhythm.R;
import com.example.rhythm.databinding.ActivityHomePageBinding;
import com.example.rhythm.ui.authentication.AuthenticationActivity;
import com.example.rhythm.ui.homePage.generation.GenerationFragment;
import com.example.rhythm.ui.homePage.library.LibraryFragment;
import com.example.rhythm.utils.SpConnect;
import com.facebook.CallbackManager;
import com.google.android.material.navigation.NavigationBarView;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationHandler;
import com.spotify.sdk.android.auth.AuthorizationResponse;
import com.spotify.sdk.android.auth.app.SpotifyAuthHandler;

public class HomePageActivity extends AppCompatActivity {
    ActivityHomePageBinding binding;
    private static final int REQUEST_CODE = 1337;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //getSupportActionBar().hide();

        binding.bottomNavigation.setItemIconTintList(null);
        callbackManager = CallbackManager.Factory.create();



        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.home_nav:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.search_nav:
                        replaceFragment(new SearchFragment());
                        break;
                    case R.id.settings_nav:
                        replaceFragment(new SettingsFragment());
                        break;
                    case R.id.library_nav:
                        replaceFragment(new LibraryFragment());
                        break;
                    case R.id.generation_nav:
                       // Toast.makeText(HomePageActivity.this, "generation", Toast.LENGTH_SHORT).show();
                        replaceFragment(new GenerationFragment());
                        break;
                }

                return true;
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, data);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    Log.d("sssssssssss", "onActivityResult: home access token " + response.getAccessToken());
                   AuthenticationActivity.accessToken = response.getAccessToken();
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    Log.d("sssssssssss", "onActivityResult: home error" + response.getError());
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
        else{
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, data);

            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    Log.d("sssssssssss", "onActivityResult: home access token " + response.getAccessToken());
                    AuthenticationActivity.accessToken = response.getAccessToken();
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    Log.d("sssssssssss", "onActivityResult: home error" + response.getError());
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        SpConnect spConnect = new SpConnect();
        spConnect.inOnStart(this);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerViewHome, fragment);
        fragmentTransaction.commit();
    }

}