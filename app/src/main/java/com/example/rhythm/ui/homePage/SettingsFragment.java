package com.example.rhythm.ui.homePage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.databinding.FragmentSettingsBinding;
import com.example.rhythm.ui.adapter.SettingsAdapter;

import java.util.ArrayList;
import java.util.List;


public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    SettingsAdapter settingsAdapter;
    List<String>itemName = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=FragmentSettingsBinding.bind(view);

        settingsItems();

        initSettingsRecycler();



    }

    private void settingsItems(){
       itemName.add("Account");
        itemName.add("Data Saver");
        itemName.add("Languages");
        itemName.add("Playback");
        itemName.add("Privacy & Social");
        itemName.add("Audio Quality");
        itemName.add("Notification");
      //  settingsAdapter.addItem(itemName);
    }

    private void initSettingsRecycler(){
        settingsAdapter = new SettingsAdapter(itemName , getContext());
        binding.settingsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.settingsRecycler.setAdapter(settingsAdapter);

    }
}