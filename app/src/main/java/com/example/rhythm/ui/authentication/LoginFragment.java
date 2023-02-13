package com.example.rhythm.ui.authentication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rhythm.R;
import com.example.rhythm.databinding.FragmentLoginBinding;
import com.example.rhythm.ui.homePage.HomePageActivity;
import com.example.rhythm.utils.Utils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    private Utils utils;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    CallbackManager callbackManager;
    boolean bool = false;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentLoginBinding.bind(view);
        utils = new Utils();

        binding.signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null && accessToken.isExpired() == false) {
            startActivity(new Intent(getActivity(), HomePageActivity.class));
            getActivity().finish();
        }

        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!utils.isInternetConnected(getContext()))
                    utils.alertDialog("Error", "No internet Connection", getContext());

                else if (binding.emailTxt.getText().toString().isEmpty() || binding.passwordTxt.getText().toString().isEmpty())
                    utils.alertDialog("Error", "all fields required", getContext());
                else
                    login();

            }
        });



/*
        binding.loginButtonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile"));

                if (facebookLogin(view))
                    startActivity(new Intent(getActivity(), HomePageActivity.class));

            }
        });*/


    }

   /* private boolean facebookLogin(View view) {

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        bool = true;
                        startActivity(new Intent(getActivity(), HomePageActivity.class));
                        getActivity().finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        bool = false;
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        bool = false;
                        // App code
                    }
                });
        return bool;
    }*/

    private void login() {
        utils.waitnig("Wait..", "login ", getContext());

        mAuth.signInWithEmailAndPassword(binding.emailTxt.getText().toString(), binding.passwordTxt.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            utils.mloadingBar.dismiss();
                            Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                            startActivity(new Intent(getActivity(), HomePageActivity.class));

                        } else
                            utils.alertDialog("Error", task.getException().getLocalizedMessage(), getContext());
                        utils.mloadingBar.dismiss();
                    }
                });

    }


}