package com.example.rhythm.ui.authentication;

import static com.example.rhythm.ui.authentication.AuthenticationActivity.userData;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.rhythm.R;
import com.example.rhythm.data.model.UserModel;
import com.example.rhythm.databinding.FragmentSignUpBinding;
import com.example.rhythm.ui.homePage.HomePageActivity;
import com.example.rhythm.utils.Utils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;


public class SignUpFragment extends Fragment {
    Utils utils;
    FragmentSignUpBinding binding;
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String gender;

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
        gender = checkGender();

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

                else if (binding.emailTxt.getText().toString().isEmpty() || binding.passwordTxt.getText().toString().isEmpty()
                        || binding.fullNameTxt.getText().toString().isEmpty() || binding.dateOfBirth.getText().toString().isEmpty())
                    utils.alertDialog("Error", "all fields required", getContext());

                else if (!binding.genderMale.isChecked() && !binding.genderFemale.isChecked())
                    utils.alertDialog("Error", "please select your gender", getContext());
                else
                    signUp();
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null && accessToken.isExpired() == false) {
            startActivity(new Intent(getActivity(), HomePageActivity.class));
            getActivity().finish();
        }

        binding.loginButtonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile"));
                //handleFacebookAccessToken(AccessToken.getCurrentAccessToken());

            }
        });


    }

    public static void handleFacebookAccessToken(AccessToken token) {
        Log.d("tttttttttt", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("ttttttttttt", "signInWithCredential:success");


                        } else
                            Log.d("ttttttttttt", "signInWithCredential:faild" + task.getException().getLocalizedMessage());

                    }
                });

    }


    private void signUp() {

        utils.waitnig("Wait..", "sign up", getContext());
        mAuth.createUserWithEmailAndPassword(binding.emailTxt.getText().toString(), binding.passwordTxt.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                            utils.mloadingBar.dismiss();
                            addUser();
                            Navigation.findNavController(getView()).navigate(R.id.action_signUpFragment_to_suggestionFragment);

                        } else
                            utils.alertDialog("Error", task.getException().getLocalizedMessage(), getContext());
                        utils.mloadingBar.dismiss();

                    }
                });

    }

    public void addUser() {
        gender = checkGender();
        UserModel userModel;

        userModel = new UserModel(binding.fullNameTxt.getText().toString(), binding.emailTxt.getText().toString()
                , binding.passwordTxt.getText().toString(), mAuth.getUid(), binding.dateOfBirth.getText().toString(), gender);

        databaseReference.child("users").child(mAuth.getUid()).setValue(userModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                            Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                        clear();

                    }
                });
    }


    private String checkGender() {
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (binding.genderMale.isChecked() || binding.genderFemale.isChecked()) {
                    switch (checkedId) {

                        case R.id.gender_male: {
                            // Toast.makeText(getContext(), "male", Toast.LENGTH_SHORT).show();
                            gender = "Male";

                        }
                        break;
                        case R.id.gender_female: {
                            //Toast.makeText(getContext(), "female", Toast.LENGTH_SHORT).show();
                            gender = "Female";
                        }

                        break;
                        default:
                            Toast.makeText(getContext(), "Select your Gender", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Select your Gender", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return gender;
    }

    private void clear() {
        binding.fullNameTxt.getText().clear();
        binding.emailTxt.getText().clear();
        binding.passwordTxt.getText().clear();
        binding.dateOfBirth.getText().clear();
        binding.genderFemale.clearFocus();
        binding.genderMale.clearFocus();
    }


}