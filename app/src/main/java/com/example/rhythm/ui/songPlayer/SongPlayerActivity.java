package com.example.rhythm.ui.songPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.example.rhythm.R;
import com.example.rhythm.data.model.search.ItemsItem;
import com.example.rhythm.databinding.ActivitySongPlayerBinding;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class SongPlayerActivity extends AppCompatActivity {

    ActivitySongPlayerBinding binding;
    String songName;
    String artistName;
    String imageUrl;
    String songUrl;
    MediaPlayer mediaPlayer = new MediaPlayer();
    boolean songPlaying = false;
    private Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySongPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            binding.backgroundImage.setRenderEffect(RenderEffect.createBlurEffect(80, 80, Shader.TileMode.MIRROR));
        }


        songName = getIntent().getStringExtra("songName");
        artistName = getIntent().getStringExtra("artistName");
        imageUrl = getIntent().getStringExtra("imageUrl");
        songUrl = getIntent().getStringExtra("songurl");

        binding.artistName.setText(artistName);
        binding.songName.setText(songName);
        Picasso.get().load(imageUrl).into(binding.backgroundImage);
        Picasso.get().load(imageUrl).into(binding.songimage);


        try {
            //   mediaPlayer.setDataSource(getContext(), Uri.parse("spotify:track:1IhGkxXcW4vFBR9dHP5To9"));
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            // mediaPlayer.setDataSource("https://p.scdn.co/mp3-preview/f25788dadeab8a25fc970ccca716c1f9ebc2dd72?cid=f0ab2ded1b8a482188532d1f3441d07c");
            //  mediaPlayer.setDataSource("https://open.spotify.com/track/1IhGkxXcW4vFBR9dHP5To9?autoplay=true");
            // mediaPlayer.setDataSource(getContext() , Uri.parse("spotify://track/1IhGkxXcW4vFBR9dHP5To9"));

            mediaPlayer.setDataSource(songUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
            songPlaying = true;
            binding.seekBar.setMax(mediaPlayer.getDuration()/1000);

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ssssssssssss", "onViewCreated:  media player  " + e.getLocalizedMessage());
        }

        binding.playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songPlaying) {
                    binding.playPause.setImageResource(R.drawable.play_arrow);
                    mediaPlayer.pause();
                    songPlaying = false;

                } else {
                    binding.playPause.setImageResource(R.drawable.ic_pause);
                    mediaPlayer.start();
                    songPlaying = true;
                }
            }
        });

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress*1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SongPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null)
                {
                    int currentPosition = mediaPlayer.getCurrentPosition()/1000;
                    binding.seekBar.setProgress(currentPosition);
                    binding.durationPlayed.setText(formattedTime(currentPosition));

                }
                handler.postDelayed(this , 1); //oneSecond
            }
        });


    }


    private String formattedTime(int currentPosition) {

        String totalout = "";
        String totalNew = "";
        String seconds = String.valueOf(currentPosition %60);
        String minutes = String.valueOf(currentPosition/60);
        totalout = minutes + ":" + seconds;
        totalNew = minutes + ":" + "0" + seconds;
        if(seconds.length() ==1 ){
            return totalNew;
        }
        else {
            return totalout;
        }
    }
}