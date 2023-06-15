package com.example.rhythm.data.model.generation;

public class GeneratedMusicModel {

    String musicName;
    String midiData;

    public GeneratedMusicModel(String musicName, String midiData) {
        this.musicName = musicName;
        this.midiData = midiData;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMidiData() {
        return midiData;
    }

    public void setMidiData(String midiData) {
        this.midiData = midiData;
    }
}
