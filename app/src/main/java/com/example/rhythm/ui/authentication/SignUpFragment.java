package com.example.rhythm.ui.authentication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.databinding.FragmentSignUpBinding;
import com.example.rhythm.utils.Utils;


public class SignUpFragment extends Fragment {

    Utils utils;
    FragmentSignUpBinding binding;

    public SignUpFragment() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSignUpBinding.bind(view);
        utils = new Utils();

        binding.signInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);
            }
        });

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!utils.isInternetConnected(getContext()))
                    utils.alertDialog("Error", "No internet Connection", getContext());

                if (binding.emailTxt.getText().toString().isEmpty() || binding.passwordTxt.getText().toString().isEmpty()
                ||binding.fullNameTxt.getText().toString().isEmpty() || binding.dateOfBirth.getText().toString().isEmpty())
                    utils.alertDialog("Error", "all fields required", getContext());

                if(!binding.genderMale.isChecked() && !binding.genderFemale.isChecked())
                    utils.alertDialog("Error", "please select your gender", getContext());

            }
        });

    }


}