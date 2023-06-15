package com.example.rhythm.ui.homePage.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rhythm.R;
import com.example.rhythm.databinding.FragmentLibraryBinding;
import com.example.rhythm.ui.authentication.suggestionFragment;


public class LibraryFragment extends Fragment {



    public LibraryFragment() {
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
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentLibraryBinding binding =
                FragmentLibraryBinding.bind(view);

        binding.albumsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "albums clicked", Toast.LENGTH_SHORT).show();
            }
        });

        binding.artistsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "artists clicked", Toast.LENGTH_SHORT).show();

            }
        });
        binding.playlistsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "playlists clicked", Toast.LENGTH_SHORT).show();
                Fragment fragment = new PlayListsFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerViewHome, fragment).commit();

            }
        });

        binding.LikesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Navigation.findNavController(view).navigate(R.id.action_libraryFragment_to_likesFragment);
                Fragment fragment = new LikesFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerViewHome, fragment).commit();
            }
        });

        binding.generatedBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new GeneratedSavedFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerViewHome, fragment).commit();
            }
        });



    }
}