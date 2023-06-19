package com.example.rhythm.ui.homePage.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.data.model.generation.GeneratedMusicModel;
import com.example.rhythm.databinding.FragmentGeneratedSavedBinding;
import com.example.rhythm.ui.adapter.SavedGeneratedMusicAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GeneratedSavedFragment extends Fragment {


    FragmentGeneratedSavedBinding binding;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    List<GeneratedMusicModel> generatedMusicModelList = new ArrayList<>();
    private String userId = FirebaseAuth.getInstance().getUid();

    SavedGeneratedMusicAdapter savedGeneratedMusicAdapter;


    public GeneratedSavedFragment() {
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
        return inflater.inflate(R.layout.fragment_generated_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentGeneratedSavedBinding.bind(view);
        initRecycler();
        databaseReference.child("generated saved").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                    generatedMusicModelList.add(snapshot1.getValue(GeneratedMusicModel.class));

                savedGeneratedMusicAdapter.addGeneratedMusic(generatedMusicModelList,getContext());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void initRecycler() {
        savedGeneratedMusicAdapter = new SavedGeneratedMusicAdapter();
        binding.generatedSavedRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.generatedSavedRecycler.setAdapter(savedGeneratedMusicAdapter);
    }
}