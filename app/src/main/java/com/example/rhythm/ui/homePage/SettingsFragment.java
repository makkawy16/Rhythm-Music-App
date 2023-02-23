package com.example.rhythm.ui.homePage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rhythm.R;
import com.example.rhythm.databinding.FragmentSettingsBinding;
import com.example.rhythm.ui.adapter.SettingsAdapter;
import com.example.rhythm.ui.authentication.AuthenticationActivity;
import com.example.rhythm.utils.Utils;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    SettingsAdapter settingsAdapter;
    List<String> itemName = new ArrayList<>();
    Utils utils = new Utils();

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
        binding = FragmentSettingsBinding.bind(view);

        settingsItems();

        initSettingsRecycler();


        if (utils.isInternetConnected(getContext())) {

            binding.loginButtonFacebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().logOut();
                    startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                    getActivity().finish();
                }
            });


            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code

                            try {
                                String username = object.getString("name");
                                binding.userName.setText(username);

                                String url = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                Picasso.get().load(url).into(binding.profilePic);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d("sssssssssss", "fail: " + e.getLocalizedMessage());
                            }

                        }

                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,picture.type(large)");
            request.setParameters(parameters);
            request.executeAsync();
        }
        else {
            utils.alertDialog("Error" , "No Internet Connection please try again" , getContext());
        }

    }

    private void settingsItems() {
        itemName.add("Account");
        itemName.add("Data Saver");
        itemName.add("Languages");
        itemName.add("Playback");
        itemName.add("Privacy & Social");
        itemName.add("Audio Quality");
        itemName.add("Notification");
    }

    SettingsAdapter.ItemClickListener onItemClicked = new SettingsAdapter.ItemClickListener() {
        @Override
        public void onItemCLiked(String itemName) {
            Toast.makeText(getContext(), itemName + " Clicked", Toast.LENGTH_SHORT).show();
        }
    };

    private void initSettingsRecycler() {
        settingsAdapter = new SettingsAdapter(itemName, getContext(), onItemClicked);
        binding.settingsRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.settingsRecycler.setAdapter(settingsAdapter);

    }


}