package com.example.rhythm.utils;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class SpConnect {

    private static final String CLIENT_ID = "b8d75817c809492db50ab2c04b70eef3";
    private static final String REDIRECT_URI = "https://oauth.pstmn.io/v1/browser-callback";
    private static final int REQUEST_CODE = 1337;
    private SpotifyAppRemote mSpotifyAppRemote;



    public void inOnStart(Context context){

        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(context, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("sssssssss", "Connected! Yay!");
                       // Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();

                        // Now you can start interacting with App Remote
                        connected(context);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("ssssssssss", "" + throwable.getLocalizedMessage());

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });




        }
    private void connected(Context context) {
        // Then we will write some more code here.
        // mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");
            AuthorizationRequest.Builder builder =
                    new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);

            builder.setScopes(new String[]{"streaming"});
            AuthorizationRequest request = builder.build();

            AuthorizationClient.openLoginActivity((Activity) context, REQUEST_CODE, request);
            mSpotifyAppRemote.getConnectApi();
    }


}
