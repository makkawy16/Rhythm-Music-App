package com.example.rhythm.ui.homePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.rhythm.R;
import com.example.rhythm.databinding.ActivityHomePageBinding;
import com.example.rhythm.ui.homePage.library.LibraryFragment;
import com.example.rhythm.utils.SpConnect;
import com.google.android.material.navigation.NavigationBarView;

public class HomePageActivity extends AppCompatActivity {
    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //getSupportActionBar().hide();

        binding.bottomNavigation.setItemIconTintList(null);


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
                        Toast.makeText(HomePageActivity.this, "generation", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
       /* SpConnect spConnect = new SpConnect();
        spConnect.inOnStart(this);*/
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerViewHome, fragment);
        fragmentTransaction.commit();
    }

}