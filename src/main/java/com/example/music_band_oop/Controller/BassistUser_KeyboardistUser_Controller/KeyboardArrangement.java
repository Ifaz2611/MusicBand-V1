package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class KeyboardArrangement {
    private String event;
    private String song;
    private int tempo;
    private String key;
    private String synthPatch;
    private boolean reviewed;
    private String patchNotes;
    private String midiReference;
    private String sheetMusic;

    public KeyboardArrangement(String event, String song, int tempo, String key, String synthPatch, boolean reviewed, String patchNotes, String midiReference, String sheetMusic) {
        this.event = event;
        this.song = song;
        this.tempo = tempo;
        this.key = key;
        this.synthPatch = synthPatch;
        this.reviewed = reviewed;
        this.patchNotes = patchNotes;
        this.midiReference = midiReference;
        this.sheetMusic = sheetMusic;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSynthPatch() {
        return synthPatch;
    }

    public void setSynthPatch(String synthPatch) {
        this.synthPatch = synthPatch;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public String getPatchNotes() {
        return patchNotes;
    }

    public void setPatchNotes(String patchNotes) {
        this.patchNotes = patchNotes;
    }

    public String getMidiReference() {
        return midiReference;
    }

    public void setMidiReference(String midiReference) {
        this.midiReference = midiReference;
    }

    public String getSheetMusic() {
        return sheetMusic;
    }

    public void setSheetMusic(String sheetMusic) {
        this.sheetMusic = sheetMusic;
    }
}
