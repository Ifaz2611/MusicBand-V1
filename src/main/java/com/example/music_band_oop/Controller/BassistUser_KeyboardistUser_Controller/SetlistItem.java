package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

public class SetlistItem {
    private String eventName;
    private String songTitle;
    private int tempo;
    private String key;
    private String notesForBassist;

    public SetlistItem(String eventName, String songTitle, int tempo, String key, String notesForBassist) {
        this.eventName = eventName;
        this.songTitle = songTitle;
        this.tempo = tempo;
        this.key = key;
        this.notesForBassist = notesForBassist;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
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

    public String getNotesForBassist() {
        return notesForBassist;
    }

    public void setNotesForBassist(String notesForBassist) {
        this.notesForBassist = notesForBassist;
    }
}
