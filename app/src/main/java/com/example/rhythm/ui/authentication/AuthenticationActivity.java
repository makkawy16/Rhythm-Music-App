package com.example.rhythm.ui.authentication;

import static com.example.rhythm.ui.authentication.SignUpFragment.handleFacebookAccessToken;
import static com.example.rhythm.ui.authentication.SignUpFragment.mAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.rhythm.R;
import com.example.rhythm.data.model.UserModel;
import com.example.rhythm.databinding.ActivityAuthenticationBinding;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AuthenticationActivity extends AppCompatActivity {

    ActivityAuthenticationBinding binding;
    CallbackManager callbackManager;
    public static FirebaseUser userData;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // getSupportActionBar().hide();

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        // Navigation.findNavController(new SignUpFragment().getView()).navigate(R.id.action_signUpFragment_to_suggestionFragment);
                        Log.d("ssssssssss", "onSuccess: Activity " + loginResult.toString());

                        handleFacebookAccessToken(loginResult.getAccessToken());

                        Fragment fragment = new suggestionFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).commit();

                        userData = mAuth.getCurrentUser();
                        addUSerData();


                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.d("ssssssssssss", "onCancel: ");

                    }

                    @Override
                    public void onError(FacebookException e) {
                        Log.d("sssssssssssssssss", "onError 1: " + e.getLocalizedMessage());
                        Log.d("sssssssssssssssss", "onError 2: " + e.getMessage());
                        Log.d("sssssssssssssssss", "onError 3: " + e.getCause());
                        Log.d("sssssssssssssssss", "onError 4: " + e.fillInStackTrace().getLocalizedMessage());
                        Log.d("sssssssssssssssss", "onError 5: " + e.getStackTrace());
                        // App code
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addUSerData() {
        String name = userData.getDisplayName();
        String email = userData.getEmail();
        String id = userData.getUid();
        String phoneNumber = userData.getPhoneNumber();
        UserModel userModel = new UserModel(name, email, id, phoneNumber);

        databaseReference.child("users").child(mAuth.getUid()).setValue(userModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                            Toast.makeText(AuthenticationActivity.this, "Added", Toast.LENGTH_SHORT).show();


                    }
                });

    }
}