package com.example.rhythm.ui.songPlayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.rhythm.R;
import com.example.rhythm.databinding.ActivitySongPlayerBinding;
import com.example.rhythm.source.local.roomDB.LikedSong;
import com.example.rhythm.source.local.roomDB.LikedSongDataBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class SongPlayerActivity extends AppCompatActivity {

    ActivitySongPlayerBinding binding;
    String songName;
    String artistName;
    String imageUrl;
    String songUrl;
    String songId;
    MediaPlayer mediaPlayer = new MediaPlayer();
    boolean songPlaying = false;
    private Handler handler = new Handler();


    String userId = FirebaseAuth.getInstance().getUid();

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySongPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            binding.backgroundImage.setRenderEffect(RenderEffect.createBlurEffect(80, 80, Shader.TileMode.MIRROR));
        }




        Log.d("sssssssssssss", "onCreate: user id player activity   " + userId);


        songName = getIntent().getStringExtra("songName");
        artistName = getIntent().getStringExtra("artistName");
        imageUrl = getIntent().getStringExtra("imageUrl");
        songUrl = getIntent().getStringExtra("songurl");
        songId = getIntent().getStringExtra("songid");


        binding.artistName.setText(artistName);
        binding.songName.setText(songName);
        Picasso.get().load(imageUrl).into(binding.backgroundImage);
        Picasso.get().load(imageUrl).into(binding.songimage);

        if (songsExist())
            binding.likeBtn.setImageResource(R.drawable.ic_red_heart_liked);


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
            binding.seekBar.setMax(mediaPlayer.getDuration() / 1000);

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
                mediaPlayer.seekTo(progress * 1000);
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
                if (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    binding.seekBar.setProgress(currentPosition);
                    binding.durationPlayed.setText(formattedTime(currentPosition));

                }
                handler.postDelayed(this, 1); //oneSecond
            }
        });


        binding.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (bitmap1.sameAs(bitmap2)) {
                    binding.likeBtn.setImageResource(R.drawable.ic_red_heart_liked);
                    addSongInLikes();
                } else {
                    binding.likeBtn.setImageResource(R.drawable.ic_like);
                    deleteSong();
                }*/
                if (!songsExist()){
                    binding.likeBtn.setImageResource(R.drawable.ic_red_heart_liked);
                    addSongInLikes();
                }
                else{
                    binding.likeBtn.setImageResource(R.drawable.ic_like);
                    deleteSong();
                }




            }
        });

    }


    private String formattedTime(int currentPosition) {

        String totalout = "";
        String totalNew = "";
        String seconds = String.valueOf(currentPosition % 60);
        String minutes = String.valueOf(currentPosition / 60);
        totalout = minutes + ":" + seconds;
        totalNew = minutes + ":" + "0" + seconds;
        if (seconds.length() == 1) {
            return totalNew;
        } else {
            return totalout;
        }
    }

    private void addSongInLikes() {
        LikedSong likedSong = new LikedSong(songId, songName, songUrl, artistName, imageUrl, true);

        long num = LikedSongDataBase.getLikedSongDataBase(getApplicationContext())
                .getLikedSongDao()
                .AddSong(new LikedSong(songId, songName, songUrl, artistName, imageUrl, true));

        if (num != -1)
            Toast.makeText(this, songName + " Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();


        database.child("liked songs").child(userId).setValue(likedSong)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(SongPlayerActivity.this, "Added online", Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void deleteSong() {
        LikedSong likedSong = new LikedSong(songId, songName, songUrl, artistName, imageUrl, true);

        LikedSongDataBase.getLikedSongDataBase(this)
                .getLikedSongDao().deleteLikedSong(likedSong);

        database.child("liked songs").child(userId).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(SongPlayerActivity.this, "deleted online", Toast.LENGTH_LONG).show();

                    }
                });
    }

    private boolean songsExist() {
int count = 0;
        List<LikedSong> likedSongs =
                LikedSongDataBase.getLikedSongDataBase(this)
                        .getLikedSongDao()
                        .getAllLikedSongs();
        if (likedSongs!=null)
        {
            for (LikedSong liked: likedSongs) {
         while (count!= likedSongs.size()-1)
                if (likedSongs.get(count).isLiked() )
                    return true;
                count++;
            }
            return false;
        }
        return false;
    }

}