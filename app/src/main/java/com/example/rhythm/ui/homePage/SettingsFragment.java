package com.example.rhythm.ui.homePage;

import static com.example.rhythm.ui.authentication.SignUpFragment.mAuth;

import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    String username;

    // FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public SettingsFragment() {
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
                    mAuth.signOut();
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
                                if (object != null) {
                                    String username = object.getString("name");
                                    if (!username.isEmpty())
                                        binding.userName.setText(username);


                                 /*   username = mAuth.getCurrentUser().getDisplayName();

                                    if(!username.isEmpty() ){
                                        binding.userName.setText(mAuth.getCurrentUser().getDisplayName());
                                    }*/


                                    String url = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                    if (!url.isEmpty())
                                        Picasso.get().load(url).into(binding.profilePic);
                                }
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
        } else {
            utils.alertDialog("Error", "No Internet Connection please try again", getContext());

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