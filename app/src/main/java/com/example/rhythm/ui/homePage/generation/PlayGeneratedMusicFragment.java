package com.example.rhythm.ui.homePage.generation;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.rhythm.R;
import com.example.rhythm.data.model.generation.GeneratedMusicModel;
import com.example.rhythm.databinding.FragmentPlayGeneratedMusicBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class PlayGeneratedMusicFragment extends Fragment {

    FragmentPlayGeneratedMusicBinding binding;
    String midiDataString;
    MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    String savedSongName = "";
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private String userId = FirebaseAuth.getInstance().getUid();

    public PlayGeneratedMusicFragment() {
        // Required empty public constructor
    }

    public PlayGeneratedMusicFragment(String midiDataString) {
        this.midiDataString = midiDataString;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_generated_music, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentPlayGeneratedMusicBinding.bind(view);
        //midiDataString = ;
        byte[] midiData = hexStringToByteArray(midiDataString);
// Convert the MIDI data to a MIDI file
        ByteArrayInputStream inputStream = new ByteArrayInputStream(midiData);
        try {
            File midiFile = new File(getContext().getCacheDir(), "temp.mid");
            FileOutputStream outputStream = new FileOutputStream(midiFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ssssssssssssssssss", "onViewCreated:  byte array input  midifile   " + e.getLocalizedMessage());
        }


        mediaPlayer = new MediaPlayer();

        File midiFile = new File(getContext().getCacheDir(), "temp.mid");
        try {
            mediaPlayer.setDataSource(midiFile.getAbsolutePath());
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ssssssssssssssssss", "onViewCreated: file midifile   " + e.getLocalizedMessage());

        }

        getActivity().runOnUiThread(new Runnable() {
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





        binding.playPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()) {
                    binding.playPauseBtn.setImageResource(R.drawable.play_arrow);
                    mediaPlayer.pause();

                } else {
                    binding.playPauseBtn.setImageResource(R.drawable.ic_pause);
                    mediaPlayer.start();

                }


            }
        });


        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout,null);
                EditText savedTxt = view1.findViewById(R.id.savedtxt);

                AlertDialog alertDialog = new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Name The generated Song")
                        .setView(view1)
                        .setPositiveButton("save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                savedSongName = savedTxt.getText().toString();
                                savedTheGenerated(savedSongName);
                                Log.d("ssssssssssssssssss", "onClick: "  +savedSongName);
                                dialog.dismiss();

                            }
                        }).setNegativeButton("close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                alertDialog.show();
            }
        });

    }


    public static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] byteArray = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return byteArray;
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

    private void savedTheGenerated(String theName){

        GeneratedMusicModel generatedMusicModel = new GeneratedMusicModel(theName,midiDataString);
        databaseReference.child("generated saved").child(userId).child(theName)
                .setValue(generatedMusicModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Log.d("sssssssssssssss", "onComplete: save generation   success "   );
                        else
                            Log.d("sssssssssssssss", "fail: save generation   fail "   );

                    }
                });
    }

}